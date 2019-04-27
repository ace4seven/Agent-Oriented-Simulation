package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process

//meta! id="49"
class PrepareForStartCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()

    }

    //meta! sender="AgentBus", id="50", type="Start"
    fun processStart(message: MessageForm) {
        myAgent().vehicles.forEach {
            val msgCopy: AppMessage = message.createCopy() as AppMessage
            // TODO: Make some delay of busses
            msgCopy.vehicle = it
            msgCopy.setCode(Mc.finishInitVehicle)

            hold(10.0, msgCopy)
        }
    }

    fun processFinishInitVehicle(message: MessageForm) {
        assistantFinished(message)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.start -> processStart(message)
            Mc.finishInitVehicle -> processFinishInitVehicle(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBus {
        return super.myAgent() as AgentBus
    }

}
