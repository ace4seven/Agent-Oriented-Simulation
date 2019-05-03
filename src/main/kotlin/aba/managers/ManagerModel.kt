package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*

//meta! id="2"
class ManagerModel(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
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

    //meta! sender="AgentStation", id="35", type="Response"
    fun processPassengerOut(message: MessageForm) {
        myAgent().passengerArrivedStadion()

        if (myAgent().isAllPassengersBoarded()) {
            mySim().stopReplication()
        }
    }

    //meta! sender="AgentEnviroment", id="37", type="Notice"
    fun processTravelerArrival(message: MessageForm) {
        val msg = message as AppMessage

        myAgent().registerPassenger(msg.passenger!!)

        msg.setCode(Mc.travelerArrival)
        msg.setAddressee(Id.agentStation)

        notice(msg)
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
            Mc.passengerOut -> processPassengerOut(message)

            Mc.travelerArrival -> processTravelerArrival(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentModel {
        return super.myAgent() as AgentModel
    }

}
