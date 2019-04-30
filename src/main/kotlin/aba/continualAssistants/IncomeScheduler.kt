package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.entities.PassengerEntity
import tornadofx.*

//meta! id="43"
class IncomeScheduler(id: Int, mySim: Simulation, myAgent: CommonAgent) : Scheduler(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentEnviroment", id="44", type="Start"
    fun processStart(message: MessageForm) {
        val msg = message as AppMessage

        msg.setCode(Mc.newPassenger)
        hold(msg.passengerIncomeStop!!.generateInterval().lambda + msg.passengerIncomeStop!!.generateInterval().startGenerateTime(), msg)
    }

    fun processNewPassenger(message: MessageForm) {
        val msg = message as AppMessage
        val cpyMsg = msg.createCopy()

        if(myAgent().incomeChecker.containsKey(msg.passengerIncomeStop!!.name)) {
            myAgent().incomeChecker[msg.passengerIncomeStop!!.name] = myAgent().incomeChecker[msg.passengerIncomeStop!!.name]!! + 1
        } else {
            myAgent().incomeChecker[msg.passengerIncomeStop!!.name] = 1
        }

        if (msg.passengerIncomeStop!!.generateInterval().stopGenerateTime() > mySim().currentTime()) {
            if (myAgent().incomeChecker[msg.passengerIncomeStop!!.name]!! < msg.passengerIncomeStop!!.capacity()) {
                hold(myAgent().arrivalGenerator[msg.passengerIncomeStop!!.name]!!.sample(), cpyMsg)
            }
        }

        val passenger = PassengerEntity(PassengerEntity.indexPassenger, msg.passengerIncomeStop!!, mySim())
        PassengerEntity.incIndex()

        msg.passenger = passenger

        assistantFinished(msg)
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
            Mc.newPassenger -> processNewPassenger(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentEnviroment {
        return super.myAgent() as AgentEnviroment
    }

}
