package view.tabs

import javafx.scene.text.FontWeight
import model.BusProgressCell
import model.BusTableData
import view.support.CoreView
import tornadofx.*
import view.support.SimulationActionsView

/** Author: Bc. Juraj Macak **/

open class Tab2Subview : CoreView("Priebeh simulácie") {

    override val root = vbox {
        prefWidth = 1500.0
        prefHeight = 1000.0

        vbox {

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

            hbox {
                simulationTime = label("11 : 00 : 00") {
                    style {
                        fontWeight = FontWeight.BOLD
                        fontSize = 30.px
                    }
                    hboxConstraints {
                        marginTop = 50.0
                        marginBottom = 10.0
                        marginLeft = 10.0
                    }
                bind(controller.simulationTimeProperty)
                }

                slider {
                    hboxConstraints {
                        marginTop = 60.0
                        marginBottom = 10.0
                        marginLeft = 10.0
                    }

                    minWidth = 700.0
                }
            }
            hbox {
                button("Start") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }

                }
                button("Pauza") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                }
                button("Krok") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                }
                button("Stop") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                }
            }
        }
    }

}