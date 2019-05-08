package view.tabs

import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class ExperimentView : View("Analyzer") {

    override val root = vbox {
        form {
            hbox {
                fieldset("Nastavenie experimentov pri nájdenie konfigurácii") {
                    hboxConstraints {
                        marginLeft = 20.0
                        marginTop = 50.0
                    }
                    field("Počet opakovaní na 1 konfiguráciu") {
                        vboxConstraints {
                            marginTop = 30.0
                        }
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
                        D.addMicrobusesCheckBox = checkbox()
                    }

                    field {
                        D.startExperiments = button("Spusti experimenty") {
                            vboxConstraints {
                                marginTop = 20.0
                                marginLeft = 20.0
                            }
                            action {
                                D.startExperiments.isDisable = true
                                D.startAnalyzer {
                                    D.startExperiments.isDisable = false
                                }
                            }
                        }
                    }
                }

                fieldset("Aplikácia mikrobusov experimenty") {
                    hboxConstraints {
                        marginLeft = 100.0
                        marginTop = 50.0
                    }
                    field("Počet opakovaní na 1 konfiguráciu") {
                        vboxConstraints {
                            marginTop = 30.0
                        }
                        textfield {
                            D.repeatDelimiterMicroBusesTextField = this
                            text = "100"
                        }
                    }

                    field("Stratégia vozidiel") {
                        D.analyzerBusStrategyMicroBusesCombobox = combobox {
                            items = D.analyzerStrategyMicrobuses
                        }
                    }

                    field("Min vyslania") {
                        textfield {
                            D.incomeBusMinMicroBusesTextField = this
                            text = "0"
                        }
                    }
                    field("Max vyslania") {
                        textfield {
                            D.incomeBusMaxMicroBusesTextField = this
                            text = "3600"
                        }
                    }

                    field("Názov súboru") {
                        textfield {
                            D.analyzeFileNameMicrobusesTextField = this
                            text = "experiments_microbuses.csv"
                        }
                    }

                    field("Max počet uložených výsledkov") {
                        textfield {
                            D.analyzerResultCountMicrobuses = this
                            text = "10"
                        }
                    }

                    field {
                        button("Spusti experimenty s aplikáciou mikrobusov") {
                            vboxConstraints {
                                marginTop = 20.0
                                marginLeft = 20.0
                            }
                            action {
                                D.startAnalyzeWithMicrobuses()
                            }
                        }
                    }
                }
            }
        }
    }
}