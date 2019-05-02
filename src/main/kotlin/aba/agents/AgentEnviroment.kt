package aba.agents

import OSPABA.*
import OSPRNG.ExponentialRNG
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.entities.PassengerEntity

//meta! id="1"
class AgentEnviroment(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {

    val arrivalGenerator = mutableMapOf<String, ExponentialRNG>()

    var passengersCapacityChecker = mutableMapOf<String, Int>()
        private set

    var passengerRegisterList = mutableListOf<PassengerEntity>()
        private set

    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication

        passengersCapacityChecker.clear()
        passengerRegisterList.clear()
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerEnviroment(Id.managerEnviroment, mySim(), this)
        IncomeScheduler(Id.incomeScheduler, mySim(), this)
        addOwnMessage(Mc.travellerExit)
        addOwnMessage(Mc.initPassengers)
        addOwnMessage(Mc.newPassenger)
    }
    //meta! tag="end"
}
