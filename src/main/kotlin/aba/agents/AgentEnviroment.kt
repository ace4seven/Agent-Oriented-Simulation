package aba.agents

import OSPABA.*
import OSPRNG.ExponentialRNG
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.instantAssistants.*
import helper.BusStop
import helper.Constants

//meta! id="1"
class AgentEnviroment(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {

    var arrivalGenerator = mutableMapOf<String, ExponentialRNG>()

    var incomeChecker = mutableMapOf<String, Int>()

    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication

        arrivalGenerator.clear()
        incomeChecker.clear()

        Constants.availableBusStops.forEach {
            arrivalGenerator[it.name] = ExponentialRNG(it.generateInterval().lambda)
        }
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
