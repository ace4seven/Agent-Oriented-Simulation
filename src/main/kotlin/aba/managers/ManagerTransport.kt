package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="6"
class ManagerTransport(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication

        if (petriNet() != null) {
            petriNet().clear()
        }
    }

    //meta! sender="TravellingCA", id="55", type="Finish"
    fun processFinish(message: MessageForm) {
        message.setCode(Mc.busMoveStart)

        response(message)
    }

    //meta! sender="AgentStation", id="33", type="Request"
    fun processBusMoveStart(message: MessageForm) {
        message.setAddressee(myAgent().findAssistant(Id.travellingCA))

        startContinualAssistant(message)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    fun init() {}

    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.finish -> processFinish(message)

            Mc.busMoveStart -> processBusMoveStart(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentTransport {
        return super.myAgent() as AgentTransport
    }

}
