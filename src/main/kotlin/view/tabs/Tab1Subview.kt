package view.tabs

import model.BusTableData
import tornadofx.*
import view.support.CoreView

/** Author: Bc. Juraj Macak **/

open class Tab1Subview : CoreView("Nastavenie parametrov simulácie") {

    override val root = vbox {
        prefWidth = 1500.0
        prefHeight = 1000.0

        form {
            hbox {
                fieldset("Nastavenia autobusov") {
                    hbox(20) {
                        field("Linka") {
                            busLinkComboBox = combobox{
                                items = busLinks
                            }
                        }

                        field("Typ autobusu") {
                            busTypeComboBox = combobox {
                                items = busType
                            }
                        }

                        field("Stratégia vozenia") {
                            busStrategyComboBox = combobox {
                                items = busStrategy
                            }
                        }

                        field {
                            button("Pridať") {
                                action {
                                    addBuss()
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
                            bind(controller.numberOfReplicationsProperty)

                            replicationTextField = this
                            replicationTextField.promptText = "Min 30"
                            text = "1"
                        }
                    }
                }
            }
        }
        hbox {
            hboxConstraints {
                paddingLeft = 20.0
            }
            vbox {
                label("LINKA A") {
                    vboxConstraints { marginLeft = 20.0 }
                }
                linkATableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                    }

                    minWidth = 300.0
                    items = linkATableViewDataSource
                    column("ID", BusTableData::id) {
                        minWidth = 20.0
                    }
                    column("Typ", BusTableData::type) {
                        minWidth = 140.0
                    }
                    column("Stratégia", BusTableData::strategy) {
                        minWidth = 140.0
                    }
                }
            }

            vbox {
                label("LINKA B") {
                    vboxConstraints { marginLeft = 20.0 }
                }
                linkBTableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                    }

                    minWidth = 300.0
                    items = linkBTableViewDataSource

                    column("ID", BusTableData::id) {
                        minWidth = 20.0
                    }
                    column("Typ", BusTableData::type) {
                        minWidth = 140.0
                    }
                    column("Stratégia", BusTableData::strategy) {
                        minWidth = 140.0
                    }
                }
            }

            vbox {
                label("LINKA C") {
                    vboxConstraints { marginLeft = 20.0 }
                }
                linkCTableView = tableview {
                    vboxConstraints {
                        marginLeft = 20.0
                    }
                    minWidth = 300.0
                    items = linkCTableViewDataSource

                    column("ID", BusTableData::id) {
                        minWidth = 20.0
                    }
                    column("Typ", BusTableData::type) {
                        minWidth = 140.0
                    }
                    column("Stratégia", BusTableData::strategy) {
                        minWidth = 140.0
                    }
                }
            }
        }
    }

}