package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.instantAssistants.*
import helper.Constants

//meta! id="2"
class AgentModel(id: Int, mySim: Simulation, parent: Agent?) : Agent(id, mySim, parent) {
    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerModel(Id.managerModel, mySim(), this)

        addOwnMessage(Mc.travelingProcess)
        addOwnMessage(Mc.travelerArrival)
    }
    //meta! tag="end"

    fun startSimulation() {
        preparePassengers()
        prepareBusses()
    }

    private fun preparePassengers() {
        Constants.availableBusStops.forEach {
            val message = AppMessage(mySim())

            message.passengerIncomeStop = it

            message.setCode(Mc.initPassengers)
            message.setAddressee(mySim().findAgent(Id.agentEnviroment))

            manager().notice(message)
        }
    }

    private fun prepareBusses() {
        val message = AppMessage(mySim())

        message.setCode(Mc.initVehicles)
        message.setAddressee(mySim().findAgent(Id.agentStation))
        manager().notice(message)
    }

}
