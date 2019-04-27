package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="1"
class ManagerEnviroment(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
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

    //meta! sender="AgentModel", id="67", type="Notice"
    fun processTravellerExit(message: MessageForm) {}

    //meta! sender="IncomeScheduler", id="44", type="Finish"
    fun processFinish(message: MessageForm) {}

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

            Mc.travellerExit -> processTravellerExit(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentEnviroment {
        return super.myAgent() as AgentEnviroment
    }

}
