package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import helper.Constants
import helper.Messages

//meta! id="3"
class ManagerStation(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {
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

    //meta! sender="AgentModel", id="35", type="Request"
    fun processTravellerArrival(message: MessageForm) {
        message.setCode(Mc.waitForBus)
        message.setAddressee(Id.agentBusStop)

        notice(message)
    }

    //meta! sender="AgentBusStop", id="31", type="Response"
    fun processBusArrival(message: MessageForm) {
        message.setCode(Mc.busMoveStart)
        message.setAddressee(mySim().findAgent(Id.agentTransport))

        val msg = message as AppMessage

        msg.vehicle?.initStartStats()

        if (Constants.isDebug) {
            println("Autobus ${msg.vehicle!!.id} vyjazd s ${msg.vehicle!!.getNumberOfPassengers()}")
        }

        request(message)
    }

    //meta! sender="AgentTransport", id="33", type="Response"
    fun processBusMoveStart(message: MessageForm) {
        val message = message as AppMessage

        message.vehicle?.prepareToMoveNextStop()

        message.setCode(Mc.busArrival)
        message.setAddressee(mySim().findAgent(Id.agentBusStop))

        request(message)
    }

    //meta! sender="AgentBus", id="51", type="Response"
    fun processPrepareForStart(message: MessageForm) {
        val msg = message as AppMessage

        msg.vehicle!!.updateBusHeigtFactor()

        msg.setCode(Mc.busArrival)
        msg.setAddressee(mySim().findAgent(Id.agentBusStop))

        request(message)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    fun processInitVehicles(message: MessageForm) {
        message.setCode(Mc.prepareForStart)
        message.setAddressee(Id.agentBus)

        request(message)
    }

    fun processpassengerOutOfBus(message: MessageForm) {
        message.setAddressee(mySim().findAgent(Id.agentModel))

        notice(message)
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    fun init() {}

    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.travelerArrival -> processTravellerArrival(message)
            Mc.busArrival -> processBusArrival(message)
            Mc.prepareForStart -> processPrepareForStart(message)
            Mc.busMoveStart -> processBusMoveStart(message)
            Mc.initVehicles -> processInitVehicles(message)
            Mc.passengerOut -> processpassengerOutOfBus(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentStation {
        return super.myAgent() as AgentStation
    }

}
