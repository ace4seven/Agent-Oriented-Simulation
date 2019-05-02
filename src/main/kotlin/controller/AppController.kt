package controller

import OSPABA.ISimDelegate
import OSPABA.SimState
import OSPABA.Simulation
import aba.entities.*
import aba.simulation.BusHockeySimulation
import helper.BusLink
import helper.Formatter
import javafx.application.Platform
import javafx.collections.FXCollections
import model.BusPassengersCollection
import model.BusTableData
import model.LogEntry
import model.PassengerCell
import java.util.*

/** Author: Bc. Juraj Macak **/

// ONLY METHJODS

class AppController: CoreController(), ISimDelegate {

    init { simulationCore.registerDelegate(this) }

    override fun refresh(core: Simulation?) {
        Platform.runLater(Runnable {
            simulationTime = Formatter.timeFormatterInc(core!!.currentTime())

            if (!isFastModeEnabled) {
                refreshSimulationStates(core as BusHockeySimulation)
                refreshBusLinks(core as BusHockeySimulation)
                refreshBusPassengers(core as BusHockeySimulation)
            }

            if (isLogEnabled) {
                refreshLogs()
            }
        })
    }

    private fun refreshBusPassengers(core: BusHockeySimulation) {
        busPassengersDatasources.forEach {
            it.value.busPassengers.clear()
        }

        busPassengerDatasource.removeAll()
        busPassengerDatasource.clear()

        core.agentBus()!!.vehicles.forEach {
            val passengers = it.getPassengers().clone() as LinkedList<PassengerEntity>
            val vehicle = it
            passengers.forEach {
                busPassengersDatasources[vehicle.id]?.busPassengers?.add(it.transformToCell())
            }
        }

        busPassengersDatasources[busPassengerSelectedIndex]!!.busPassengers.forEach {
            busPassengerDatasource.add(it)
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

    fun setSimSpeed(value: Double) {
        simulationCore.setSimSpeed(value, 0.1)
    }

    override fun simStateChanged(core: Simulation?, state: SimState?) {

    }

    fun startSimulation() {
        if(isFastModeEnabled) {
            simulationCore.setMaxSimSpeed()
        } else {
            simulationCore.setSimSpeed(1.0, 0.1)
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
            busPassengerDatasource.add(it)
        }
    }

}