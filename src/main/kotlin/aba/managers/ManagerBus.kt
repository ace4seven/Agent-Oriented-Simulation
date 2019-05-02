package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="47"
class ManagerBus(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
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

    //meta! sender="PrepareForStartCA", id="50", type="Finish"
    fun processFinish(message: MessageForm) {
        message.setCode(Mc.prepareForStart)

        response(message)
    }

    //meta! sender="AgentStation", id="51", type="Request"
    fun processPrepareForStart(message: MessageForm) {
        message.setAddressee(myAgent().findAssistant(Id.prepareForStartCA))

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
            Mc.prepareForStart -> processPrepareForStart(message)
            Mc.finish -> processFinish(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBus {
        return super.myAgent() as AgentBus
    }

}
