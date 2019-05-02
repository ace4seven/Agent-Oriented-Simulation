package aba.continualAssistants

import OSPABA.CommonAgent
import OSPABA.MessageForm
import OSPABA.Process
import OSPABA.Simulation
import aba.agents.AgentBusStop
import aba.simulation.Mc

/** Author: Bc. Juraj Macak **/

//meta! id="91"
class IncomeWaitingBusCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentBusStop", id="92", type="Start"
    fun processStart(message: MessageForm) {}

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.start -> processStart(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBusStop {
        return super.myAgent() as AgentBusStop
    }

}
