package view.support

import controller.AppController
import helper.Formatter
import javafx.collections.FXCollections
import javafx.scene.control.*
import model.*
import tornadofx.*

/** Author: Bc. Juraj Macak **/

class D {

    companion object {
        val controller = AppController()

        var simulationTime: Label by singleAssign()

        var bussPassengerSelectedIndex = 1

        var busID = 1

        val busLinks = FXCollections.observableArrayList("Linka A", "Linka B","Linka C")
        val busType = FXCollections.observableArrayList("Štandartný", "Vylepšený")
        val busStrategy = FXCollections.observableArrayList("Bez čakania", "S čakaním")

        var logCheckbox = CheckBox()
        var fastModeCheckBox = CheckBox()

        var speedSlider = Slider()

        // TABLES

        var startButton: Button by singleAssign()
        var pauseButton: Button by singleAssign()
        var stopButton: Button by singleAssign()

        var busProgressTableView: TableView<BusProgressCell> by singleAssign()

        var linkATableView: TableView<LinkCell> by singleAssign()
        var linkBTableView: TableView<LinkCell> by singleAssign()
        var linkCTableView: TableView<LinkCell> by singleAssign()
        var linkKTableView: TableView<LinkCell> by singleAssign()

        var busPassengersTableView: TableView<PassengerCell> by singleAssign()

        var busLinkATableView: TableView<BusTableData> by singleAssign()
        var busLinkATableViewDatasource= FXCollections.observableArrayList<BusTableData>()

        var busLinkBTableView: TableView<BusTableData> by singleAssign()
        var busLinkBTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        var busLinkCTableView: TableView<BusTableData> by singleAssign()
        var busLinkCTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        var logTableView: TableView<LogCell> by singleAssign()
        var logTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        // MARK: CoMBOBOX

        var busLinkComboBox: ComboBox<String> by singleAssign()
        var busTypeComboBox: ComboBox<String> by singleAssign()
        var busStrategyComboBox: ComboBox<String> by singleAssign()

        // TEXTFIELDS

        var replicationTextField: TextField by singleAssign()

        var busData: MutableList<BusTableData>? = null

        // METHODS

        fun loadBusesFromFile() {
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
                    "Linka A" -> busLinkATableViewDatasource.add(it)
                    "Linka B" -> busLinkBTableViewDataSource.add(it)
                    "Linka C" -> busLinkCTableViewDataSource.add(it)
                }
            }
        }

        fun addBuss() {
            var busTableData = BusTableData()

            busTableData.type = busTypeComboBox.value
            busTableData.strategy = busStrategyComboBox.value

            when (busLinkComboBox.value) {
                "Linka A" -> {
                    busTableData.id = busID
                    busLinkATableViewDatasource.add(busTableData)
                }
                "Linka B" -> {
                    busTableData.id = busID
                    busLinkBTableViewDataSource.add(busTableData)
                }
                "Linka C" -> {
                    busTableData.id = busID
                    busLinkCTableViewDataSource.add(busTableData)
                }
            }

            busID += 1
        }

        fun startSimulationButtonPressed() {
            startButton.isDisable = true
            stopButton.isDisable = false
            pauseButton.isDisable = false

            logCheckbox.isDisable = true
            fastModeCheckBox.isDisable = true

            busData?.forEach {
                controller.addVehicle(it)
            }

            controller.startSimulation()
        }

        fun stopSimulation() {
            startButton.isDisable = false
            stopButton.isDisable = true
            pauseButton.isDisable = true

            logCheckbox.isDisable = false
            fastModeCheckBox.isDisable = false

            busData?.clear()

            controller.stopSimulation()
        }

        fun pauseSimulation() {
            startButton.isDisable = true
            stopButton.isDisable = false
            pauseButton.isDisable = false

            controller.pauseSimulation()

            if (controller.simulationCore.isPaused) {
                pauseButton.text = "Prestávka"
            } else {
                pauseButton.text = "Pokračovať"
            }
        }

    }

}