package controller

import OSPABA.ISimDelegate
import OSPABA.SimState
import OSPABA.Simulation
import aba.simulation.BusHockeySimulation
import helper.Constants
import helper.FileManager
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import model.*
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreController: Controller() {

    val timeOfOneReplication = Constants.simulationTimeExtra

    val numberOfReplicationsProperty = SimpleIntegerProperty()
    val numberOfReplications: Int by numberOfReplicationsProperty

    val simulationTimeProperty = SimpleStringProperty("15 : 00 : 00")
    var simulationTime: String by simulationTimeProperty

    var busProgressDataSource= FXCollections.observableArrayList<BusProgressCell>()

    var linkADataSource= FXCollections.observableArrayList<LinkCell>()
    var linkBDataSource= FXCollections.observableArrayList<LinkCell>()
    var linkCDataSource= FXCollections.observableArrayList<LinkCell>()
    var linkKDataSource= FXCollections.observableArrayList<LinkCell>()

    var busPassengersDatasources = mutableMapOf<Int, BusPassengersCollection>()
    var busPassengerDatasource = FXCollections.observableArrayList<PassengerCell>()

    var busStopPassengerCollection = mutableMapOf<String, StopPassengersCollection>()
    var stopPassengerDataSource = FXCollections.observableArrayList<PassengerCell>()

    var logTableViewDataSource= FXCollections.observableArrayList<LogCell>()

    var busPassengerSelectedIndex: Int = 1
    var busStopPassengerSelectedIndex: String = ""

    var simulationCore: BusHockeySimulation

    val fileManager: FileManager = FileManager()

    val isLogEnabledProperty = SimpleBooleanProperty()
    var isLogEnabled: Boolean by isLogEnabledProperty

    val isFastModeEnabledProperty = SimpleBooleanProperty()
    var isFastModeEnabled: Boolean by isFastModeEnabledProperty

    init {
        simulationCore = BusHockeySimulation()
    }

}