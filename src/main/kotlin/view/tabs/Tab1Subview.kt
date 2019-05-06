package view.tabs

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import model.BusTableData
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class Tab1Subview : View("Nastavenie parametrov simulácie") {

    override val root = vbox {
        form {
            hbox {
                fieldset("Nastavenie simulácie") {
                    hboxConstraints {
                        marginLeft = 15.0
                    }
                    field("Počet replikácii") {
                        textfield {
                            bind(D.controller.numberOfReplicationsProperty)

                            D.replicationTextField = this
                            D.replicationTextField.promptText = "Min 30"
                            text = "1"

                            isDisable = true
                        }
                    }
                }
            }

            fieldset {
                hbox(20) {
                    hboxConstraints {
                        paddingTop = 20.0
                        paddingLeft = 15.0
                        paddingRight = 60.0
                    }
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

                    field("Vyslanie v čase") {
                        textfield {
                            D.timeToDeployTextField = this
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
        }
        hbox {
            hboxConstraints {
                paddingRight = 10.0
            }

            vbox {
                label("LINKA A") {
                    vboxConstraints {
                        marginLeft = 20.0
                        marginBottom = 10.0
                    }
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
                    vboxConstraints {
                        marginLeft = 20.0
                        marginBottom = 10.0
                    }
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
                    vboxConstraints {
                        marginLeft = 20.0
                        marginBottom = 10.0
                    }
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

        vbox {
            D.resetBusTables = button("Vymaž záznamy") {
                vboxConstraints {
                    marginTop = 20.0
                    marginLeft = 20.0
                }
                action {
                    D.alert(Alert.AlertType.WARNING, "Pozor", "Naozaj chcete vymazat všetky záznamy?") {
                        apply {
                            D.clearBusTables()
                        }
                    }
                }
            }
        }
    }

}