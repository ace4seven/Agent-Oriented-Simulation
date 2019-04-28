package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import helper.Messages

//meta! id="5"
class ManagerBusStop(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
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

    //meta! sender="AgentStation", id="28", type="Notice"
    fun processWaitForBus(message: MessageForm) {}

    //meta! sender="AgentStation", id="31", type="Request"
    fun processBusArrival(message: MessageForm) {
        val msg = message as AppMessage

        // TODO: Nastup cestujucich
        msg.vehicle?.currentActivity = Messages.busPassengersIncome
        println(msg.vehicle?.scheduler?.getActualStop())
        response(msg)
    }

    //meta! sender="IncomeIntoBusCA", id="58", type="Finish"
    fun processFinish(message: MessageForm) {

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
            Mc.busArrival -> processBusArrival(message)

            Mc.finish -> processFinish(message)

            Mc.waitForBus -> processWaitForBus(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBusStop {
        return super.myAgent() as AgentBusStop
    }

}
