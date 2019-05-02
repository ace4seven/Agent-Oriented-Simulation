package view.tabs

import model.BusTableData
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class Tab1Subview : View("Nastavenie parametrov simulácie") {

    override val root = vbox {
        form {
            hbox {
                fieldset("Nastavenia autobusov") {
                    hbox(20) {
                        field("Linka") {
                            D.busLinkComboBox = combobox{
                                items = D.busLinks
                            }
                        }

                        field("Typ autobusu") {
                            D.busTypeComboBox = combobox {
                                items = D.busType
                            }
                        }

                        field("Stratégia vozenia") {
                            D.busStrategyComboBox = combobox {
                                items = D.busStrategy
                            }
                        }

                        field {
                            button("Pridať") {
                                action {
                                    D.addBuss()
                                }
                            }
                        }

                        field {
                            button("Načítaj zo súboru") {
                                action {
                                    D.loadBusesFromFile()
                                }
                            }
                        }
                    }
                }

                fieldset("Nastavenie simulácie") {
                    hboxConstraints {
                        marginLeft = 60.0
                    }
                    field("Počet replikácii") {
                        textfield {
                            bind(D.controller.numberOfReplicationsProperty)

                            D.replicationTextField = this
                            D.replicationTextField.promptText = "Min 30"
                            text = "1"
                        }
                    }
                }
            }
        }
        hbox {
            hboxConstraints {
                paddingRight = 10.0
            }

            vbox {
                label("LINKA A") {
                    vboxConstraints { marginLeft = 20.0 }
                }
                D.busLinkATableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                    }

                    minWidth = 300.0
                    items = D.busLinkATableViewDatasource

                    column("ID", BusTableData::id) {
                        minWidth = 10.0
                    }
                    column("Typ", BusTableData::type) {
                        minWidth = 120.0
                    }
                    column("Stratégia", BusTableData::strategy) {
                        minWidth = 120.0
                    }

                    column("Čas vyslania", BusTableData::scheduleTime) {
                        minWidth = 140.0
                    }
                }
            }

            vbox {
                label("LINKA B") {
                    vboxConstraints { marginLeft = 20.0 }
                }
                D.busLinkBTableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                    }

                    minWidth = 300.0
                    items = D.busLinkBTableViewDataSource

                    column("ID", BusTableData::id) {
                        minWidth = 10.0
                    }
                    column("Typ", BusTableData::type) {
                        minWidth = 120.0
                    }
                    column("Stratégia", BusTableData::strategy) {
                        minWidth = 120.0
                    }

                    column("Čas vyslania", BusTableData::scheduleTime) {
                        minWidth = 140.0
                    }
                }
            }

            vbox {
                label("LINKA C") {
                    vboxConstraints { marginLeft = 20.0 }
                }
                D.busLinkCTableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                    }
                    minWidth = 300.0
                    items = D.busLinkCTableViewDataSource

                    column("ID", BusTableData::id) {
                        minWidth = 10.0
                    }
                    column("Typ", BusTableData::type) {
                        minWidth = 120.0
                    }
                    column("Stratégia", BusTableData::strategy) {
                        minWidth = 120.0
                    }

                    column("Čas vyslania", BusTableData::scheduleTime) {
                        minWidth = 140.0
                    }
                }
            }
        }
    }

}