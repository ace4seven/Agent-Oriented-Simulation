package controller

import OSPABA.ISimDelegate
import OSPABA.SimState
import OSPABA.Simulation
import aba.simulation.BusHockeySimulation
import helper.Constants
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import model.BusProgressCell
import model.BusTableData
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreController: Controller() {

    val timeOfOneReplication = Constants.simulationTime

    val numberOfReplicationsProperty = SimpleIntegerProperty()
    val numberOfReplications: Int by numberOfReplicationsProperty

    val simulationTimeProperty = SimpleStringProperty("15 : 00 : 00")
    var simulationTime: String by simulationTimeProperty

    var busProgressDataSource= FXCollections.observableArrayList<BusProgressCell>()

    var simulationCore: BusHockeySimulation

    init {
        simulationCore = BusHockeySimulation()
    }

}