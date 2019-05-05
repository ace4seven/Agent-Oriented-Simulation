package view.tabs

import model.LogCell
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class ExperimentView : View("Experimenty") {

    override val root = vbox {
        D.startExperiments = button("Spusti experimenty") {
            vboxConstraints {
                marginTop = 20.0
                marginLeft = 20.0
            }
            action {
                D.startExperiments()
            }
        }
    }
}