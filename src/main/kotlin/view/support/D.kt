package view.support

import controller.AppController
import helper.Formatter
import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import model.BusProgressCell
import model.BusTableData
import tornadofx.*

/** Author: Bc. Juraj Macak **/

class D {

    companion object {
        val controller = AppController()

        var simulationTime: Label by singleAssign()

        var busID = 1

        val busLinks = FXCollections.observableArrayList("Linka A", "Linka B","Linka C")
        val busType = FXCollections.observableArrayList("Štandartný", "Vylepšený")
        val busStrategy = FXCollections.observableArrayList("Bez čakania", "S čakaním")

        // TABLES

        var busProgressTableView: TableView<BusProgressCell> by singleAssign()

        var linkATableView: TableView<BusTableData> by singleAssign()
        var linkATableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        var linkBTableView: TableView<BusTableData> by singleAssign()
        var linkBTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        var linkCTableView: TableView<BusTableData> by singleAssign()
        var linkCTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

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
                    "Linka A" -> linkATableViewDataSource.add(it)
                    "Linka B" -> linkBTableViewDataSource.add(it)
                    "Linka C" -> linkCTableViewDataSource.add(it)
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

}