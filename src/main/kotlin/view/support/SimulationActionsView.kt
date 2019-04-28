package view.support

import javafx.scene.text.FontWeight
import tornadofx.*

/** Author: Bc. Juraj Macak **/

class SimulationActionsView : CoreView("Agentovo orientovaná simulácia") {

    override val root = vbox {
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
//                bind(controller.simulationTimeProperty)
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