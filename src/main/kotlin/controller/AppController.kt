package controller

import OSPABA.ISimDelegate
import OSPABA.SimState
import OSPABA.Simulation
import aba.entities.BusEntity
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import aba.simulation.BusHockeySimulation
import helper.BusLink
import helper.Formatter
import javafx.application.Platform
import model.BusTableData

/** Author: Bc. Juraj Macak **/

// ONLY METHJODS

class AppController: CoreController(), ISimDelegate {

    init { simulationCore.registerDelegate(this) }

    override fun refresh(core: Simulation?) {
        Platform.runLater(Runnable {
            simulationTime = Formatter.timeFormatterInc(core!!.currentTime())

            refreshSimulationStates(core as BusHockeySimulation)
        })
    }

    private fun refreshSimulationStates(core: BusHockeySimulation) {
        busProgressDataSource.clear()
        busProgressDataSource.removeAll()

        core.getVehiclesDatasource().forEach {
            busProgressDataSource.add(it)
        }
    }

    override fun simStateChanged(core: Simulation?, state: SimState?) {

    }

    fun startSimulation() {
        if (simulationCore.isPaused) {
            simulationCore.resumeSimulation()
        } else {
            simulationCore.setSimSpeed(5.0, 0.1)
            val thread = object: Thread() {
                override fun run() {
                    simulationCore.simulate(numberOfReplications, timeOfOneReplication)
                }
            }
            thread.start()
        }
    }

    fun addVehicle(busData: BusTableData) {
        simulationCore.addVehicle(busData.transformToVehicle(simulationCore))
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

    fun stepSimulation() {

    }


}