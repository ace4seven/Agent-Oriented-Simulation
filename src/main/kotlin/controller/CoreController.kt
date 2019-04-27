package controller

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreController: Controller() {

    val numberOfReplicationsProperty = SimpleIntegerProperty()
    val numberOfReplications: Int by numberOfReplicationsProperty

}