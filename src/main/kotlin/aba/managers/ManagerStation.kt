package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*
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
    fun processTravelingProcess(message: MessageForm) {}

    //meta! sender="AgentBusStop", id="31", type="Response"
    fun processBusArrival(message: MessageForm) {
        message.setCode(Mc.busMoveStart)
        message.setAddressee(mySim().findAgent(Id.agentTransport))

        request(message)
    }

    //meta! sender="ExitTravelerCA", id="66", type="Finish"
    fun processFinish(message: MessageForm) {
        when(message.sender().id()) {
            Id.returnBusCA -> {
                val msg = message as AppMessage

                msg.vehicle?.prepareToMoveNextStop()

                msg.setCode(Mc.busArrival)
                msg.setAddressee(mySim().findAgent(Id.agentBusStop))

                request(msg)
            }
            Id.exitTravelerCA -> {
                message.setAddressee(myAgent().findAssistant(Id.returnBusCA))

                val msg = message as AppMessage
                msg.vehicle?.currentActivity = Messages.busReturn

                startContinualAssistant(msg)
            }
        }
    }

    //meta! sender="AgentTransport", id="33", type="Response"
    fun processBusMoveStart(message: MessageForm) {
        val message = message as AppMessage

        // Ak som vo finalnej destinaci, tak zacnem proces vystup
        if (message.vehicle!!.scheduler.isFinalDestination()) {
            message.setAddressee(Id.exitTravelerCA)

            val msg = message as AppMessage
            msg.vehicle?.currentActivity = Messages.busPassengersOutcome

            startContinualAssistant(msg)
        } else {
            message.vehicle?.prepareToMoveNextStop()
            message.setCode(Mc.busArrival)
            message.setAddressee(mySim().findAgent(Id.agentBusStop))

            request(message)
        }
    }

    //meta! sender="AgentBus", id="51", type="Response"
    fun processPrepareForStart(message: MessageForm) {
        message.setCode(Mc.busArrival)
        message.setAddressee(mySim().findAgent(Id.agentBusStop))

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

        println("Poziadavka na prvotnú inicializaciu autobusov")
        request(message)
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    fun init() {}

    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.travelingProcess -> processTravelingProcess(message)
            Mc.busArrival -> processBusArrival(message)
            Mc.finish -> processFinish(message)
            Mc.prepareForStart -> processPrepareForStart(message)
            Mc.busMoveStart -> processBusMoveStart(message)
            Mc.initVehicles -> processInitVehicles(message)
            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentStation {
        return super.myAgent() as AgentStation
    }

}
