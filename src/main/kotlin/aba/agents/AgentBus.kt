package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.entities.Vehicle
import aba.instantAssistants.*

//meta! id="47"
class AgentBus(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {

    var vehicles = mutableListOf<Vehicle>()

    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerBus(Id.managerBus, mySim(), this)
        PrepareForStartCA(Id.prepareForStartCA, mySim(), this)

        addOwnMessage(Mc.prepareForStart)
        addOwnMessage(Mc.finishInitVehicle)
    }
    //meta! tag="end"

    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

}
