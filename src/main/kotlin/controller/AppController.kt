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
import model.*
import java.util.*

/** Author: Bc. Juraj Macak **/

class AppController: CoreController(), ISimDelegate {

    init {
        simulationCore.registerDelegate(this)

        simulationCore.onReplicationDidFinish {
            finishReplicationUpdateGUI(it as BusHockeySimulation)
        }
    }

    private fun finishReplicationUpdateGUI(core: BusHockeySimulation) {
        val replicationNumb = core.currentReplication() + 1

        globalStatisticsDatasource.clear()
        if (replicationNumb > 2) {
            globalStatisticsDatasource.add(StatisticCell.makeCell(StatName.numbOfReplications, "${replicationNumb}"))
            globalStatisticsDatasource.add(StatisticCell.makeCellConfidental(StatName.numbOfPassengerIncome, core.averageNumberOfPassengers!!))
            globalStatisticsDatasource.add(StatisticCell.makeCellConfidental(StatName.passengerWaitingTime, core.averageWaitingTimeStat!!))
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

    private fun computeLocalStatistics(core: BusHockeySimulation) {
        val numbOfPassengers = core.agentModel()?.getNumberOfPassengers()
        val averageWaitingTime = core.agentBusStop()!!.averageWaitingStat.mean()
        val profit = core.agentBus()!!.vehicles.fold(0) { sum, e -> e.profit + sum }

        localStatisticsDatasource.clear()
        localStatisticsDatasource.add(StatisticCell.makeCell(StatName.numbOfPassengerIncome, "${numbOfPassengers}"))
        localStatisticsDatasource.add(StatisticCell.makeCell(StatName.passengerWaitingTime, "${averageWaitingTime}"))
        localStatisticsDatasource.add(StatisticCell.makeCell(StatName.profitFromMicrobus, "${profit}"))
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
                    simulationCore.simulate(numberOfReplications, timeOfOneReplication)
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

}