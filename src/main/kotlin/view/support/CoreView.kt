package view.support

import controller.AppController
import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.text.FontWeight
import model.BusProgressCell
import model.BusTableData
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreView(title: String) : View(title) {

    protected val controller: AppController by inject()

    protected var simulationTime: Label by singleAssign()

    protected var busID = 1

    protected val simulationActionsViewTab1: SimulationActionsView by inject()
    protected val simulationActionsViewTab2: SimulationActionsView by inject()

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


    // METHODS

    protected fun addBuss() {
        var busTableData = BusTableData()

        busTableData.type = busTypeComboBox.value
        busTableData.strategy = busStrategyComboBox.value

        when(busLinkComboBox.value) {
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

    fun simulationActions(): HBox {
        return hbox {
            simulationTime = label("11 : 00 : 00") {
                style {
                    fontWeight = FontWeight.BOLD
                    fontSize = 30.px
                }
                vboxConstraints {
                    marginTop = 50.0
                    marginBottom = 10.0
                    marginLeft = 30.0
                }
//                bind(controller.simulationTimeProperty)
            }
        }
    }

    fun startSimulationButtonPressed() {
        controller.startSimulation()
    }

    fun stopSimulation() {
        controller.stopSimulation()
    }

    fun pauseSimulation() {
        controller.pauseSimulation()
    }

}