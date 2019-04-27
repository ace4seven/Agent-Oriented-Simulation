package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="1"
class AgentEnviroment(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {
    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerEnviroment(Id.managerEnviroment, mySim(), this)
        IncomeScheduler(Id.incomeScheduler, mySim(), this)
        addOwnMessage(Mc.travellerExit)
    }
    //meta! tag="end"
}
