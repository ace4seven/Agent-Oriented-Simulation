package view.support

import controller.AppController
import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import model.BusTableData
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreView(title: String) : View(title) {

    protected val controller: AppController by inject()

    protected val busLinks = FXCollections.observableArrayList("Linka A", "Linka B","Linka C")
    protected val busType = FXCollections.observableArrayList("Štandartný", "Vylepšený")
    protected val busStrategy = FXCollections.observableArrayList("Bez čakania", "S čakaním")

    // TABLES

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
                busTableData.id = linkATableViewDataSource.count() + 1
                linkATableViewDataSource.add(busTableData)
            }
            "Linka B" -> {
                busTableData.id = linkBTableViewDataSource.count() + 1
                linkBTableViewDataSource.add(busTableData)
            }
            "Linka C" -> {
                busTableData.id = linkCTableViewDataSource.count() + 1
                linkCTableViewDataSource.add(busTableData)
            }
        }
    }

}