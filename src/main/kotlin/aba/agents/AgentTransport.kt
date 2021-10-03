package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="6"
class AgentTransport(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {
    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerTransport(Id.managerTransport, mySim(), this)
        TravellingCA(Id.travellingCA, mySim(), this)

        addOwnMessage(Mc.busMoveStart)
        addOwnMessage(Mc.finishTravelStop)
    }
    //meta! tag="end"
}
