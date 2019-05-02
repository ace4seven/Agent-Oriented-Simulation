package view.tabs

import model.BusProgressCell
import model.LinkCell
import tornadofx.*
import view.support.D
import view.window.BusDetailView


/** Author: Bc. Juraj Macak **/

open class OverviewSimulationTabView : View("Stav objektov") {

    override val root = vbox {

        prefWidth = 1500.0
        prefHeight = 700.0

        label("Prehľad autobusov") {
            vboxConstraints {
                marginLeft = 20.0
                marginTop = 20.0
            }
        }
        D.busProgressTableView = tableview {
            vboxConstraints {
                marginLeft = 20.0
                marginRight = 20.0
                marginTop = 10.0
            }

            onUserSelect {
                D.controller.busPassengerSelectedIndex = it.id
                D.controller.updateBusPassengersTable()
            }

            onDoubleClick {
                openInternalWindow(BusDetailView::class)
            }

            maxHeight = 300.0

            items = D.controller.busProgressDataSource
            column("ID", BusProgressCell::id) {
                minWidth = 20.0
            }

            column("Linka", BusProgressCell::link) {
                minWidth = 100.0
            }

            column("Typ", BusProgressCell::type) {
                minWidth = 100.0
            }

            column("Stratégia", BusProgressCell::strategy) {
                minWidth = 100.0
            }

            column("Aktuálna zastávka", BusProgressCell::currentStop) {
                minWidth = 150.0
            }

            column("Nasledujúca zastávka", BusProgressCell::nextStop) {
                minWidth = 150.0
            }

            column("Progress jazdy", BusProgressCell::progress) {
                minWidth = 100.0
            }

            column("Aktivita", BusProgressCell::activity) {
                minWidth = 200.0
            }

            column("Voľná kapacita", BusProgressCell::freeCapacity) {
                minWidth = 150.0
            }

            column("Počet cestujúcich", BusProgressCell::numbOfTravelers) {
                minWidth = 150.0
            }
        }

        hbox {
            vbox {
                label("Linka A - Prehľad") {
                    vboxConstraints {
                        marginLeft = 20.0
                        marginTop = 20.0
                    }
                }
                D.linkATableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                        marginTop = 10.0
                    }
                    maxHeight = 300.0

                    items = D.controller.linkADataSource
                    column("Zastávka", LinkCell::busStop) {
                        minWidth = 50.0
                    }

                    column("Počet čakajúcich", LinkCell::peopleCount) {
                        minWidth = 120.0
                    }
                }
            }

            vbox {
                label("Linka B - Prehľad") {
                    vboxConstraints {
                        marginLeft = 20.0
                        marginTop = 20.0
                    }
                }
                D.linkBTableView = tableview {
                    vboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    maxHeight = 300.0

                    items = D.controller.linkBDataSource
                    column("Zastávka", LinkCell::busStop) {
                        minWidth = 50.0
                    }

                    column("Počet čakajúcich", LinkCell::peopleCount) {
                        minWidth = 120.0
                    }
                }
            }

            vbox {
                label("Linka C - Prehľad") {
                    vboxConstraints {
                        marginLeft = 20.0
                        marginTop = 20.0
                    }
                }
                D.linkCTableView = tableview {
                    vboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    maxHeight = 300.0

                    items = D.controller.linkCDataSource
                    column("Zastávka", LinkCell::busStop) {
                        minWidth = 50.0
                    }

                    column("Počet čakajúcich", LinkCell::peopleCount) {
                        minWidth = 120.0
                    }
                }
            }


            vbox {
                label("Ostatné - Prehľad") {
                    vboxConstraints {
                        marginLeft = 20.0
                        marginTop = 20.0
                    }
                }
                D.linkKTableView = tableview {
                    vboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    maxHeight = 300.0

                    items = D.controller.linkKDataSource
                    column("Zastávka", LinkCell::busStop) {
                        minWidth = 50.0
                    }

                    column("Počet čakajúcich", LinkCell::peopleCount) {
                        minWidth = 120.0
                    }
                }
            }

        }
    }

}