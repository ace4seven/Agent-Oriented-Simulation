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
import javafx.beans.property.SimpleIntegerProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import model.BusTableData
import tornadofx.*

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
            testBusses()
            val thread = object: Thread() {
                override fun run() {
                    simulationCore.simulate(numberOfReplications, timeOfOneReplication)
                }
            }
            thread.start()
        }
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

    private fun testBusses() {
        simulationCore.addVehicle(BusEntity(1, BusLink.LINK_A, BusType.SMALL, TravelStrategyType.WAIT, 1000.0, simulationCore))
        simulationCore.addVehicle(BusEntity(2, BusLink.LINK_B, BusType.SMALL, TravelStrategyType.WAIT, 1000.0, simulationCore))
        simulationCore.addVehicle(BusEntity(3, BusLink.LINK_C, BusType.SMALL, TravelStrategyType.WAIT, 1000.0, simulationCore))

        simulationCore.addVehicle(BusEntity(4, BusLink.LINK_C, BusType.SMALL, TravelStrategyType.WAIT, 3000.0, simulationCore))
        simulationCore.addVehicle(BusEntity(5, BusLink.LINK_A, BusType.SMALL, TravelStrategyType.WAIT, 4000.0, simulationCore))
        simulationCore.addVehicle(BusEntity(6, BusLink.LINK_B, BusType.MICROBUS, TravelStrategyType.NO_WAIT, 2000.0, simulationCore))
        simulationCore.addVehicle(BusEntity(7, BusLink.LINK_C, BusType.MICROBUS, TravelStrategyType.WAIT, 7000.0, simulationCore))
    }


}