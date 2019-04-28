package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process

//meta! id="54"
class TravellingCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentTransport", id="55", type="Start"
    fun processStart(message: MessageForm) {
        val msg = message as AppMessage
        msg.vehicle?.scheduler?.getDuration()

        msg.setCode(Mc.finishTravelStop)
        hold(msg.vehicle!!.scheduler.getDuration()!!, msg)
    }

    fun processFinishTravel(message: MessageForm) {
        println("Koniec cestovania")

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
            Mc.finishTravelStop -> processFinishTravel(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentTransport {
        return super.myAgent() as AgentTransport
    }

}
