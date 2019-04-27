package aba.managers

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.continualAssistants.*
import aba.instantAssistants.*

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
    fun processBusArrival(message: MessageForm) {}

    //meta! sender="ExitTravelerCA", id="66", type="Finish"
    fun processFinish(message: MessageForm) {}

    //meta! sender="AgentTransport", id="33", type="Response"
    fun processBusMoveStart(message: MessageForm) {}

    //meta! sender="AgentBus", id="51", type="Response"
    fun processPrepareForStart(message: MessageForm) {}

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
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

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentStation {
        return super.myAgent() as AgentStation
    }

}
