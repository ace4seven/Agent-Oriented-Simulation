package continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process
import tornadofx.*

//meta! id="79"
class BusWaitingCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentBusStop", id="80", type="Start"
    fun processStart(message: MessageForm) {
        val msg = message as AppMessage

        msg.vehicle!!.isWaiting = true

        msg.setCode(Mc.busFinishWaiting)

        hold(90.0, msg)
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
            Mc.busFinishWaiting -> {
                val msg = message as AppMessage

                msg.vehicle!!.isWaiting = false
                assistantFinished(msg)
            }

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBusStop {
        return super.myAgent() as AgentBusStop
    }

}
