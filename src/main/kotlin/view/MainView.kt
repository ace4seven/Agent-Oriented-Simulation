package view

import javafx.scene.control.TabPane
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*
import view.support.D
import view.tabs.ExperimentView
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
                    majorTickUnit = 1.0
                    blockIncrement = 1.0
                    minorTickCount = 1
                    isSnapToTicks = true

                    min = 1.0
                    max = 100.0

                    valueProperty().addListener { _, oldValue, newValue ->
                        if (oldValue.toInt() != newValue.toInt()) {
                            D.controller.setSimulationSpeed(newValue.toDouble())
                        }
                    }

                    minWidth = 700.0
                }

                D.intensitySlider = slider {
                    hboxConstraints {
                        marginTop = 20.0
                        marginBottom = 10.0
                        marginLeft = 20.0
                    }

                    isShowTickLabels = true
                    isSnapToTicks = true

                    majorTickUnit = 0.1
                    blockIncrement = 0.1
                    minorTickCount = 1

                    min = 0.1
                    max = 1.0

                    valueProperty().addListener { _, oldValue, newValue ->
                        if (oldValue.toDouble() != newValue.toDouble()) {
                            D.controller.setSimulationIntensity(newValue.toDouble())
                        }
                    }

                    minWidth = 400.0
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
                D.pauseButton = button("Prestávka") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }
                    action { D.pauseSimulation() }
                    isDisable = true
                }
                D.stopButton = button("Stop") {
                    hboxConstraints {
                        marginLeft = 10.0
                        marginTop = 10.0
                    }

                    action { D.stopSimulation() }
                    isDisable = true
                }

                D.fastModeCheckBox = checkbox("Rýchly režim", D.controller.isFastModeEnabledProperty) {
                    hboxConstraints {
                        marginTop = 10.0
                        marginLeft = 50.0
                    }

                    action {
                        if (D.fastModeCheckBox.isSelected) {
                            D.speedSlider.isDisable = true
                            D.intensitySlider.isDisable = true
                            D.replicationTextField.isDisable = false
                            D.logCheckbox.isDisable = true
                        } else {
                            D.speedSlider.isDisable = false
                            D.intensitySlider.isDisable = false
                            D.replicationTextField.isDisable = true
                            D.controller.numberOfReplicationsProperty.value = 1
                            D.logCheckbox.isDisable = false
                        }
                    }
                }

                D.logCheckbox = checkbox("Logovanie", D.controller.isLogEnabledProperty) {
                    hboxConstraints {
                        marginTop = 10.0
                        marginLeft = 10.0
                    }
                }

                D.simulationProgressBar = progressbar {
                    hboxConstraints {
                        marginTop = 10.0
                        marginLeft = 50.0
                    }

                    style {
                        accentColor = Color.RED
                    }

                    minWidth = 840.0
                    bind(D.controller.progressProperty)
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
            tab(ExperimentView::class)
        }
    }

}