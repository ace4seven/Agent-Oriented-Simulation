package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="5"
class AgentBusStop(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {
    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerBusStop(Id.managerBusStop, mySim(), this)
        IncomeIntoBusCA(Id.incomeIntoBusCA, mySim(), this)
        addOwnMessage(Mc.waitForBus)
        addOwnMessage(Mc.busArrival)
    }
    //meta! tag="end"
}
