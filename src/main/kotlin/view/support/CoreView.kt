package view.support

import controller.AppController
import helper.Formatter
import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.FontWeight
import model.BusProgressCell
import model.BusTableData
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreView(title: String) : View(title) {

    protected val controller: AppController by inject()

    protected var simulationTime: Label by singleAssign()

    protected var busID = 1

    protected val busLinks = FXCollections.observableArrayList("Linka A", "Linka B","Linka C")
    protected val busType = FXCollections.observableArrayList("Štandartný", "Vylepšený")
    protected val busStrategy = FXCollections.observableArrayList("Bez čakania", "S čakaním")

    // TABLES

    protected var busProgressTableView: TableView<BusProgressCell> by singleAssign()

    protected var linkATableView: TableView<BusTableData> by singleAssign()
    var linkATableViewDataSource= FXCollections.observableArrayList<BusTableData>()

    protected var linkBTableView: TableView<BusTableData> by singleAssign()
    var linkBTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

    protected var linkCTableView: TableView<BusTableData> by singleAssign()
    var linkCTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

    // MARK: CoMBOBOX

    protected var busLinkComboBox: ComboBox<String> by singleAssign()
    protected var busTypeComboBox: ComboBox<String> by singleAssign()
    protected var busStrategyComboBox: ComboBox<String> by singleAssign()

    // TEXTFIELDS

    protected var replicationTextField: TextField by singleAssign()

    var busData: MutableList<BusTableData>? = null

    // METHODS

    protected fun loadBusesFromFile() {
        val bData = controller.fileManager.getBusSchedule().map {
            val formattedBus = BusTableData()

            formattedBus.scheduleTime = Formatter.timeFormatterInc(it.scheduleTime.toDouble())
            formattedBus.type = Formatter.convertToText(it.type)
            formattedBus.strategy = Formatter.convertToText(it.strategy)
            formattedBus.id = it.id
            formattedBus.link = Formatter.convertToText(it.link)
            formattedBus.rawTime = it.scheduleTime.toDouble()

            return@map formattedBus
        }

        this.busData = bData.toMutableList()

        busData?.forEach {
            when(it.link) {
                "Linka A" -> linkATableViewDataSource.add(it)
                "Linka B" -> linkBTableViewDataSource.add(it)
                "Linka C" -> linkCTableViewDataSource.add(it)
            }
        }
    }

    protected fun addBuss() {
        var busTableData = BusTableData()

        busTableData.type = busTypeComboBox.value
        busTableData.strategy = busStrategyComboBox.value

        when (busLinkComboBox.value) {
            "Linka A" -> {
                busTableData.id = busID
                linkATableViewDataSource.add(busTableData)
            }
            "Linka B" -> {
                busTableData.id = busID
                linkBTableViewDataSource.add(busTableData)
            }
            "Linka C" -> {
                busTableData.id = busID
                linkCTableViewDataSource.add(busTableData)
            }
        }

        busID += 1
    }

    fun startSimulationButtonPressed() {
        busData?.forEach {
            controller.addVehicle(it)
        }

        controller.startSimulation()
    }

    fun stopSimulation() {
        busData?.clear()

        controller.stopSimulation()
    }

    fun pauseSimulation() {
        controller.pauseSimulation()
    }

    fun test() {
        println("OK")
    }

}