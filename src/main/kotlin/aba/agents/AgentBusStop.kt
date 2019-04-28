package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.entities.BusStopAdministration
import aba.instantAssistants.*

//meta! id="5"
class AgentBusStop(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {

    private var busStopAdministration: BusStopAdministration? = null

    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication

        busStopAdministration = BusStopAdministration(mySim())

        busStopAdministration?.busStops?.forEach {
            println(it.key)
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerBusStop(Id.managerBusStop, mySim(), this)
        IncomeIntoBusCA(Id.incomeIntoBusCA, mySim(), this)
        addOwnMessage(Mc.waitForBus)
        addOwnMessage(Mc.busArrival)
    }
    //meta! tag="end"

    fun getBusStopAdministration(): BusStopAdministration {
        return busStopAdministration!!
    }

}
