package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process

//meta! id="49"
class PrepareForStartCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()

    }

    //meta! sender="AgentBus", id="50", type="Start"
    fun processStart(message: MessageForm) {
        myAgent().vehicles.forEach {
            val msgCopy: AppMessage = message.createCopy() as AppMessage

            msgCopy.vehicle?.currentActivity = "Čaká na prvé vyslanie"

            msgCopy.vehicle = it
            msgCopy.setCode(Mc.finishInitVehicle)

            hold(it.deployTime, msgCopy)
        }
    }

    fun processFinishInitVehicle(message: MessageForm) {
        val msg = message as AppMessage
        msg.vehicle?.isDeployed = true

        BusHockeySimulation.logEntry(mySim().currentTime(), "AUTOBUS ${msg.vehicle!!.id} (${msg.vehicle!!.link.formattedName()}) vyslaný na zastávku: ${msg.vehicle!!.scheduler.getActualStop()!!.formattedStop()}")

        assistantFinished(message)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.start -> processStart(message)
            Mc.finishInitVehicle -> processFinishInitVehicle(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBus {
        return super.myAgent() as AgentBus
    }

}
