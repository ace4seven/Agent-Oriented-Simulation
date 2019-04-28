package continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process

//meta! id="71"
class ReturnBusCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentStation", id="72", type="Start"
    fun processStart(message: MessageForm) {
        println("Autobus sa vracia spat na stanoviste ...")
        val msg = message as AppMessage
        msg.setCode(Mc.finishBusReturn)

        hold(msg.vehicle!!.link.backWayDuration(), msg)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    fun processfinishBusReturn(message: MessageForm) {
        assistantFinished(message)
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.start -> processStart(message)
            Mc.finishBusReturn -> processfinishBusReturn(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentStation {
        return super.myAgent() as AgentStation
    }

}
