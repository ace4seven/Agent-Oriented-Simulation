package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="2"
class ManagerModel(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
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

    //meta! sender="AgentStation", id="35", type="Response"
    fun processTravelingProcess(message: MessageForm) {}

    //meta! sender="AgentEnviroment", id="37", type="Notice"
    fun processTravelerArrival(message: MessageForm) {
        message.setCode(Mc.waitForBus)
        message.setAddressee(Id.agentBusStop)

        notice(message)
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
            Mc.travelingProcess -> processTravelingProcess(message)

            Mc.travelerArrival -> processTravelerArrival(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentModel {
        return super.myAgent() as AgentModel
    }

}
