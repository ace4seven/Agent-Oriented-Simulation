package controller

import OSPABA.ISimDelegate
import OSPABA.SimState
import OSPABA.Simulation
import aba.entities.*
import aba.simulation.BusHockeySimulation
import helper.*
import helper.Formatter
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.scene.chart.XYChart
import model.*
import java.util.*

/** Author: Bc. Juraj Macak **/

class AppController: CoreController(), ISimDelegate {

    init {
        simulationCore.registerDelegate(this)

        simulationCore.onReplicationDidFinish {
            Platform.runLater(Runnable {
                if (simulationCore.isRunning) {
                    finishReplicationUpdateGUI(it as BusHockeySimulation)

                    updateGraphs(it)
                    updateProgress(it)
                }
            })
        }
    }

    private fun finishReplicationUpdateGUI(core: BusHockeySimulation) {
        val replicationNumb = core.currentReplication() + 1

        globalStatisticsDatasource.clear()
        if (replicationNumb > 2) {
            globalStatisticsDatasource.add(StatisticCell.makeCell(StatName.globalNumberOfReplications, "${replicationNumb}"))

            var vehicleCost = 0
            core.agentBus()!!.vehicles.forEach {
                vehicleCost += it.type.price()
            }

            globalStatisticsDatasource.add(StatisticCell.makeCell(StatName.globalCompanyExpenses, Formatter.currencyFormatter(vehicleCost)))

            globalStatisticsDatasource.add(StatisticCell.makeCellConfidental(StatName.globallPassengersCount, core.averageNumberOfPassengers!!))
            globalStatisticsDatasource.add(StatisticCell.makeCellConfidental(StatName.globalMicrobusProfit, core.averageMicrobusProfit))
            globalStatisticsDatasource.add(StatisticCell.makeCellConfidental(StatName.globalMissHockey, core.averageNoOnTime!!, true))
            globalStatisticsDatasource.add(StatisticCell.makeCellConfidental(StatName.globalPassengerWaiting, core.averageWaitingTimeStat!!))

            core.averageWaitingBusStopStat.forEach {
                if (it.key != "STATION") {
                    globalStatisticsDatasource.add(StatisticCell.makeCellConfidental("${StatName.globalWaitingOnBusStop}: ${Formatter.busStopFormatter(it.key)}", it.value))
                }
            }

            core.averageHeightBusStopStat.forEach {
                if (it.key != "STATION") {
                    globalStatisticsDatasource.add(StatisticCell.makeCellConfidental("${StatName.globalHeightOnBusStop}: ${Formatter.busStopFormatter(it.key)}", it.value))
                }
            }

            core.agentBus()!!.vehicles.forEach {
                globalStatisticsDatasource.add(StatisticCell.makeCellConfidental("${StatName.globalBusAvailability} ID: ${it.id}", core.averageBusesAvailability[it.id]!!, true))
            }
        }
    }

    override fun refresh(core: Simulation?) {
        Platform.runLater(Runnable {
            simulationTime = Formatter.timeFormatterInc(core!!.currentTime())

            if (!isFastModeEnabled) {
                refreshSimulationStates(core as BusHockeySimulation)
                refreshBusLinks(core)
                refreshBusPassengers(core)
                refreshBusStopPassengers(core)

                computeLocalStatistics(core)
            }

            if (isLogEnabled) {
                refreshLogs()
            }
        })
    }

    private fun updateProgress(core: BusHockeySimulation) {
        val repDelimeter = numberOfReplications.toDouble() / 100

        if ((core.currentReplication() + 1) % repDelimeter.toDouble() <= 0.0) {
            progress += 0.01
        }
    }

    private fun updateGraphs(core: BusHockeySimulation) {
        if (((core.currentReplication() + 1).toDouble() / numberOfReplications.toDouble()) > 0.1) {
            val border = if (numberOfReplications / 4000 == 0) 1 else numberOfReplications / 4000
            if ((core.currentReplication() + 1) % border == 0) {
                averageWaitingChartData.add(XYChart.Data(core.currentReplication() + 1, core.averageWaitingTimeStat!!.mean()))
                averageMissHockeyChartData.add(XYChart.Data(core.currentReplication() + 1, core.averageNoOnTime!!.mean()))
            }
        }
    }

    private fun computeLocalStatistics(core: BusHockeySimulation) {
        val numbOfPassengers = core.agentModel()?.getNumberOfPassengers()
        val averageWaitingTime = core.agentBusStop()!!.averageWaitingStat.mean()
        val profit = core.agentBus()!!.vehicles.fold(0) { sum, e -> e.profit + sum }

        localStatisticsDatasource.clear()
        localStatisticsDatasource.add(StatisticCell.makeCell(StatName.localPassengersCount, "${numbOfPassengers}"))
        localStatisticsDatasource.add(StatisticCell.makeCell(StatName.localPassengerWaiting, "${averageWaitingTime}"))
        localStatisticsDatasource.add(StatisticCell.makeCell(StatName.localMicrobusProfit, "${profit}"))
    }

    private fun refreshBusStopPassengers(core: BusHockeySimulation) {
        stopPassengerDataSource.clear()

        core.agentBusStop()!!.getBusStopAdministration().busStops.forEach {
            var passengers = it.value.getWaitingPassengersQueue().clone() as MutableList<PassengerEntity>
            val key = it.key

            busStopPassengerCollection[key]!!.stopPassengers = passengers
        }
    }

    private fun refreshBusPassengers(core: BusHockeySimulation) {
        busPassengerDatasource.clear()

        core.agentBus()!!.vehicles.forEach {
            val passengers = it.getPassengers().clone() as LinkedList<PassengerEntity>
            val vehicle = it

            busPassengersDatasources[vehicle.id]?.busPassengers = passengers
        }
    }

    private fun refreshBusLinks(core: BusHockeySimulation) {
        linkADataSource.clear()
        linkBDataSource.clear()
        linkCDataSource.clear()
        linkKDataSource.clear()

        linkADataSource.removeAll()
        linkBDataSource.removeAll()
        linkCDataSource.removeAll()
        linkKDataSource.removeAll()


        core.agentBusStop()?.getBusStopAdministration()?.busStops?.forEach {
            if (it.value.type.getLink() == null) {
                linkKDataSource.add(it.value.transformToCell())
            } else {
                when(it.value.type.getLink()!!) {
                    BusLink.LINK_A -> linkADataSource.add(it.value.transformToCell())
                    BusLink.LINK_B -> linkBDataSource.add(it.value.transformToCell())
                    BusLink.LINK_C -> linkCDataSource.add(it.value.transformToCell())
                }
            }
        }
    }

    private fun refreshLogs() {
        val entries = BusHockeySimulation.logEntries.clone() as LinkedList<LogEntry>
        BusHockeySimulation.logEntries.clear()
        entries.forEach {
            logTableViewDataSource.add(it.logCell())
        }
    }

    private fun refreshSimulationStates(core: BusHockeySimulation) {
        busProgressDataSource.clear()
        busProgressDataSource.removeAll()

        core.getVehiclesDatasource().forEach {
            busProgressDataSource.add(it)
        }
    }

    fun setSimulationSpeed(value: Double) {
        simSpeed = value
        simulationCore.setSimSpeed(simSpeed, simIntensity)
    }

    fun setSimulationIntensity(value: Double) {
        simIntensity = value
        simulationCore.setSimSpeed(simSpeed, simIntensity)
    }

    override fun simStateChanged(core: Simulation?, state: SimState?) {

    }

    fun startSimulation() {
        if(isFastModeEnabled) {
            simulationCore.setMaxSimSpeed()
        } else {
            simulationCore.setSimSpeed(1.0, 0.1)
        }

        BusStop.values().forEach {
            busStopPassengerCollection[it.name] = StopPassengersCollection()
        }

        if (simulationCore.isPaused) {
            simulationCore.resumeSimulation()
        } else {
            val thread = object: Thread() {
                override fun run() {
                    simulationCore.simulate(numberOfReplications, 100000000000.0)
                }
            }
            thread.start()
        }
    }

    fun addVehicle(busData: BusTableData) {
        val bus = busData.transformToVehicle(simulationCore)
        simulationCore.addVehicle(bus)

        var busDataSource = BusPassengersCollection()
        busPassengersDatasources[bus.id] = busDataSource
    }

    fun pauseSimulation() {
        if (simulationCore.isPaused) {
            simulationCore.resumeSimulation()
        } else {
            simulationCore.pauseSimulation()
        }
    }

    fun stopSimulation() {
        simulationCore.stopSimulation()
    }

    fun updateBusPassengersTable() {
        busPassengerDatasource.removeAll()
        busPassengerDatasource.clear()

        busPassengersDatasources[busPassengerSelectedIndex]!!.busPassengers.forEach {
            busPassengerDatasource.add(it.transformToCell())
        }
    }

    fun updateBusStopPassengersTable() {
        stopPassengerDataSource.removeAll()
        stopPassengerDataSource.clear()

        busStopPassengerCollection[busStopPassengerSelectedIndex]!!.stopPassengers.forEach {
            stopPassengerDataSource.add(it.transformToCell())
        }
    }

    fun clearVehicles() {
        simulationCore.agentBus()?.vehicles?.clear()
    }

    fun exportGlobalStatistics(): Boolean {
        if (globalStatisticsDatasource.count() > 0) {
            statisticExporter.initializeWriter("global_statistics_export.csv")

            globalStatisticsDatasource.forEach {
                statisticExporter.addRow(it)
            }

            statisticExporter.closeWriter()

            return true
        }

        return false
    }

}