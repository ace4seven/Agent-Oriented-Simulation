package view.tabs

import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class ExperimentView : View("Experimenty") {

    override val root = vbox {
        form {
            hbox {
                fieldset("Nastavenie experimentov") {
                    hboxConstraints {
                        marginLeft = 15.0
                    }
                    field("Počet opakovaní na 1 konfiguráciu") {
                        textfield {
                            D.repeatDelimiterTextField = this
                            text = "100"
                        }
                    }

                    field("Stratégia vozidiel") {
                        D.analyzerBusStrategyCombobox = combobox {
                            items = D.analyzerStrategy
                        }
                    }

                    field("Preferovaný typ vozidiel") {
                        D.analyzerBusTypeCombobox = combobox {
                            items = D.analyzerBusType
                        }
                    }

                    field("Minimálna vzorka vozidiel") {
                        textfield {
                            D.numberOfBussesMin = this
                            text = "10"
                        }
                    }

                    field("Maximálna vzorka vozidiel") {
                        textfield {
                            D.numberOfBussesMax = this
                            text = "100"
                        }
                    }

                    field("Min vyslania") {
                        textfield {
                            D.incomeBusMinTextField = this
                            text = "0"
                        }
                    }
                    field("Max vyslania") {
                        textfield {
                            D.incomeBusMaxTextField = this
                            text = "3600"
                        }
                    }

                    field("Názov súboru") {
                        textfield {
                            D.analyzeFileNameTextField = this
                            text = "experiments.csv"
                        }
                    }

                    field("Max počet uložených výsledkov") {
                        textfield {
                            D.analyzerResultCount = this
                            text = "10"
                        }
                    }

                    field("Aktivácia mikrobusov") {
                        D.hasMicrobusesCheckBox = checkbox()
                    }
                }
            }
        }


        D.startExperiments = button("Spusti experimenty") {
            vboxConstraints {
                marginTop = 20.0
                marginLeft = 20.0
            }
            action {
                D.startExperiments.isDisable = true
                D.startAnalyzer {
//                    alert(Alert.AlertType.INFORMATION, "Experimenty dokončené", "Analyzér skončil prevádzku")
                    D.startExperiments.isDisable = false
                }
            }
        }
    }
}