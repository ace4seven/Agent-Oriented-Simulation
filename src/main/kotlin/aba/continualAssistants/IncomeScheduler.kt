package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*

//meta! id="43"
class IncomeScheduler(id: Int, mySim: Simulation, myAgent: CommonAgent) : Scheduler(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentEnviroment", id="44", type="Start"
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

    override fun myAgent(): AgentEnviroment {
        return super.myAgent() as AgentEnviroment
    }

}
