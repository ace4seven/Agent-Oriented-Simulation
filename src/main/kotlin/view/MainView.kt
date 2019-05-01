package view

import javafx.scene.control.TabPane
import javafx.scene.text.FontWeight
import tornadofx.*
import view.support.CoreView
import view.tabs.Tab1Subview
import view.tabs.Tab2Subview

class MainView : CoreView("Agentovo orientovaná simulácia") {

    override val root = vbox {
        prefWidth = 1500.0
        minWidth = 1500.0
        prefHeight = 1000.0

        vbox {
            hbox {
                simulationTime = label("11 : 00 : 00") {
                    style {
                        fontWeight = FontWeight.BOLD
                        fontSize = 30.px
                    }
                    hboxConstraints {
                        marginTop = 10.0
                        marginBottom = 10.0
                        marginLeft = 10.0
                    }
                    bind(controller.simulationTimeProperty)
                }

                slider {
                    hboxConstraints {
                        marginTop = 20.0
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
                    action { startSimulationButtonPressed() }
                }
                button("Pauza") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    action { pauseSimulation() }
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
                    action { stopSimulation() }
                }

                hboxConstraints {
                    marginBottom = 10.0
                    paddingBottom = 10.0
                }
            }

            button("TEST") {
                action {
                    test()
                }
            }
        }

        tabpane {
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

            tab(Tab1Subview::class)
            tab(Tab2Subview::class)
        }
    }

}