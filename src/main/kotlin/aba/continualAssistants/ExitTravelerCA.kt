package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process

//meta! id="65"
class ExitTravelerCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentStation", id="66", type="Start"
    fun processStart(message: MessageForm) {
        hold(10.0, message)
        message.setCode(Mc.passengersFinishExit)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    fun processFinishExit(message: MessageForm) {
        assistantFinished(message)
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.start -> processStart(message)
            Mc.passengersFinishExit -> processFinishExit(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentStation {
        return super.myAgent() as AgentStation
    }
}
