package view.tabs

import javafx.scene.text.FontWeight
import model.BusProgressCell
import model.BusTableData
import tornadofx.*
import view.support.CoreView

/** Author: Bc. Juraj Macak **/

open class OverviewSimulationTabView : CoreView("Stav objektov") {

    override val root = vbox {

        prefWidth = 1500.0
        prefHeight = 1000.0

        label("Prehľad autobusov") {
            vboxConstraints {
                marginLeft = 20.0
                marginTop = 20.0
            }
        }
        busProgressTableView = tableview {
            vboxConstraints {
                marginLeft = 20.0
                marginRight = 20.0
                marginTop = 10.0
            }

            items = controller.busProgressDataSource
            column("ID", BusProgressCell::id) {
                minWidth = 20.0
            }

            column("Linka", BusProgressCell::link) {
                minWidth = 100.0
            }

            column("Aktuálna zastávka", BusProgressCell::currentStop) {
                minWidth = 100.0
            }

            column("Nasledujúca zastávka", BusProgressCell::nextStop) {
                minWidth = 100.0
            }

            column("Progress jazdy", BusProgressCell::progress) {
                minWidth = 100.0
            }

            column("Aktivita", BusProgressCell::activity) {
                minWidth = 200.0
            }

            column("Voľná kapacita", BusProgressCell::freeCapacity) {
                minWidth = 100.0
            }

            column("Počet cestujúcich", BusProgressCell::numbOfTravelers) {
                minWidth = 100.0
            }
        }
    }

}