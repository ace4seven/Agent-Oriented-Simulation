package controller

import OSPABA.ISimDelegate
import OSPABA.SimState
import OSPABA.Simulation
import aba.simulation.BusHockeySimulation
import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

/** Author: Bc. Juraj Macak **/

abstract class CoreController: Controller(), ISimDelegate {

    val numberOfReplicationsProperty = SimpleIntegerProperty()
    val numberOfReplications: Int by numberOfReplicationsProperty

    fun test() {
        val core = BusHockeySimulation()
        core.registerDelegate(this)
    }

    override fun refresh(core: Simulation?) {

    }

    override fun simStateChanged(p0: Simulation?, p1: SimState?) {

    }

}