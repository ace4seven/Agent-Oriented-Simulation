package view

import javafx.scene.control.TabPane
import javafx.scene.text.FontWeight
import tornadofx.*
import view.support.D
import view.tabs.GlobalStatisticView
import view.tabs.Tab1Subview
import view.tabs.Tab2Subview

class MainView : View("Agentovo orientovaná simulácia") {

    override val root = vbox {
        prefWidth = 1500.0
        minWidth = 1500.0
        prefHeight = 1000.0

        vbox {
            hbox {
                D.simulationTime = label("11 : 00 : 00") {
                    minWidth = 200.0
                    style {
                        fontWeight = FontWeight.BOLD
                        fontSize = 30.px
                    }
                    hboxConstraints {
                        marginTop = 10.0
                        marginBottom = 10.0
                        marginLeft = 10.0
                    }

                    bind(D.controller.simulationTimeProperty)
                }

                D.speedSlider = slider {
                    hboxConstraints {
                        marginTop = 20.0
                        marginBottom = 10.0
                        marginLeft = 20.0
                    }

                    isShowTickLabels = true
                    majorTickUnit = 10.0
                    blockIncrement = 1.0
                    minorTickCount = 10
                    isSnapToTicks = true

                    valueProperty().addListener { _, oldValue, newValue ->
                        if (oldValue.toInt() != newValue.toInt()) {
                            D.controller.setSimSpeed(newValue.toDouble())
                        }
                    }

                    minWidth = 700.0
                }
            }
            hbox {
                D.startButton = button("Start") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    action { D.startSimulationButtonPressed() }
                }
                D.pauseButton = button("Pauza") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    action { D.pauseSimulation() }
                }
                D.stopButton = button("Stop") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    action { D.stopSimulation() }
                }

                D.fastModeCheckBox = checkbox("Rýchly režim", D.controller.isFastModeEnabledProperty) {
                    hboxConstraints {
                        marginTop = 10.0
                        marginLeft = 50.0
                    }

                    action {
                        if (D.fastModeCheckBox.isSelected) {
                            D.speedSlider.isDisable = true
                        } else {
                            D.speedSlider.isDisable = false
                        }
                    }
                }

                D.logCheckbox = checkbox("Logovanie", D.controller.isLogEnabledProperty) {
                    hboxConstraints {
                        marginTop = 10.0
                        marginLeft = 10.0
                    }
                }

                hboxConstraints {
                    marginBottom = 10.0
                    paddingBottom = 10.0
                }
            }
        }

        tabpane {
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

            tab(Tab1Subview::class)
            tab(Tab2Subview::class)
            tab(GlobalStatisticView::class)
        }
    }

}