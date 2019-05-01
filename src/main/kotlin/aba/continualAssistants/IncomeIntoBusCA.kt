package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process

//meta! id="57"
class IncomeIntoBusCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentBusStop", id="58", type="Start"
    fun processStart(message: MessageForm) {
        val msg = message as AppMessage
        val bus = msg.vehicle!!

        var passengers = myAgent().getBusStopPassengers(bus.getActualStop())

        for (i in 1..bus.type.numbOfDors()) {
            if (passengers.size > 0 && bus.getFreeCapacity() > 0) {
                bus.incBusyDoor()

                val passenger = passengers.dequeue()

                passenger.passengerIncomeIntoBus()
                passenger.numberOfDoorIn = i

                bus.addPassenger(passenger)

                val msgCopy = msg.createCopy() as AppMessage
                msgCopy.setCode(Mc.passengerFinishIncome)

                msgCopy.doorIdentifier = i

                hold(myAgent().incomeGenerator.sample(), msgCopy)
            }
        }

        assistantFinished(msg)
    }

    fun processPassengerFinishIncome(message: MessageForm) {
        val msg = message as AppMessage
        val bus = msg.vehicle!!

        var passengers = myAgent().getBusStopPassengers(bus.getActualStop())

        bus.decBusyDoor()

        if(passengers.size > 0 && bus.getFreeCapacity() > 0) {
            bus.incBusyDoor()

            val passenger = passengers.dequeue()
            passenger.numberOfDoorIn = msg.doorIdentifier

            passenger.passengerIncomeIntoBus()
            bus.addPassenger(passenger)

            val msgCopy = msg.createCopy()

            hold(myAgent().incomeGenerator.sample(), msgCopy)
        }

        if (!bus.isBusy()) {
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
            Mc.passengerFinishIncome -> processPassengerFinishIncome(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBusStop {
        return super.myAgent() as AgentBusStop
    }

}
