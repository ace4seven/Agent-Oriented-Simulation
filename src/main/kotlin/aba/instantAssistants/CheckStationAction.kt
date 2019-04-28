package aba.instantAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*

//meta! id="60"
class CheckStationAction(id: Int, mySim: Simulation, myAgent: CommonAgent) : Action(id, mySim, myAgent) {

    override fun execute(message: MessageForm) {}

    override fun myAgent(): AgentStation {
        return super.myAgent() as AgentStation
    }

}
