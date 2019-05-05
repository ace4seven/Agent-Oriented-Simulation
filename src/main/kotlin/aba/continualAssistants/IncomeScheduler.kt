package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import aba.entities.PassengerEntity

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

        val sample = myAgent().arrivalGenerator[msg.passengerIncomeStop!!.getConcreteStop().name]!!.sample()

        hold(sample + msg.passengerIncomeStop!!.generateInterval().start + mySim().currentTime(), msg)
    }

    fun processNewPassenger(message: MessageForm) {
        val msg = message as AppMessage

        if (myAgent().passengersCapacityChecker.containsKey(msg.passengerIncomeStop!!.name)) {
            myAgent().passengersCapacityChecker[msg.passengerIncomeStop!!.name] = myAgent().passengersCapacityChecker[msg.passengerIncomeStop!!.name]!! + 1
        } else {
            myAgent().passengersCapacityChecker[msg.passengerIncomeStop!!.name] = 1
        }

        if (msg.passengerIncomeStop!!.generateInterval().stopGenerateTime() > mySim().currentTime()) {
            if (myAgent().passengersCapacityChecker[msg.passengerIncomeStop!!.name]!! < msg.passengerIncomeStop!!.capacity()) {
                val cpyMsg = msg.createCopy()

                hold(myAgent().arrivalGenerator[msg.passengerIncomeStop!!.getConcreteStop().name]!!.sample(), cpyMsg)
            }
        }

        val passenger = PassengerEntity(PassengerEntity.getuniqueID(), msg.passengerIncomeStop!!, mySim())

        BusHockeySimulation.logEntry(mySim().currentTime(), "Pasažier ${passenger.id} príchod na zastávku: ${passenger.type.name}")

        msg.passenger = passenger

        if (msg.passengerIncomeStop!!.generateInterval().stopGenerateTime() > mySim().currentTime()) {
            assistantFinished(msg)
        }
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
