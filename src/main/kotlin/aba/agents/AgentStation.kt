package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.instantAssistants.*

//meta! id="3"
class AgentStation(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {
    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerStation(Id.managerStation, mySim(), this)
        CheckStationAction(Id.checkStationAction, mySim(), this)

        addOwnMessage(Mc.travelerArrival)
        addOwnMessage(Mc.busArrival)
        addOwnMessage(Mc.busMoveStart)
        addOwnMessage(Mc.prepareForStart)

        addOwnMessage(Mc.initVehicles)
        addOwnMessage(Mc.finishBusReturn)
        addOwnMessage(Mc.passengerOut)
    }
    //meta! tag="end"

}
