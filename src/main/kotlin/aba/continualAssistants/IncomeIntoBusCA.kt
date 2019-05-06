package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process
import aba.entities.BusType
import aba.entities.PassengerEntity

//meta! id="57"
class IncomeIntoBusCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Scheduler(id, mySim, myAgent) {

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

                if (bus.isMicroBus() && !passengers.peek().wantToGoByMicroBus()) {
                    break
                }

                bus.incBusyDoor()

                val passenger = passengers.dequeue()

                passenger.passengerIncomeIntoBus()

                BusHockeySimulation.logEntry(mySim().currentTime(), "Pasažier ${passenger.id} - začiatok nástupu do AUTOBUS: ${bus.id} (dvere: ${i}) na zastávke ${bus.scheduler.getActualStop()!!.name}")

                updateStatistic(passenger)

                passenger.numberOfDoorIn = i

                bus.addPassenger(passenger)

                val msgCopy = msg.createCopy() as AppMessage
                msgCopy.setCode(Mc.passengerFinishIncome)

                msgCopy.doorIdentifier = i

                var sample: Double

                if (bus.isMicroBus()) {
                    sample = myAgent().incomeGeneratorMicroBus.sample()
                } else {
                    sample = myAgent().incomeGeneratorBus.sample()
                }

                hold(sample, msgCopy)
            }
        }

        assistantFinished(msg)
    }

    fun processPassengerFinishIncome(message: MessageForm) {
        val msg = message as AppMessage
        val bus = msg.vehicle!!

        if (bus.type == BusType.MICROBUS) {
            bus.payForticket()
        }

        var passengers = myAgent().getBusStopPassengers(bus.getActualStop())

        bus.decBusyDoor()

        if(passengers.size > 0 && bus.getFreeCapacity() > 0) {
            if (bus.isMicroBus() && !passengers.peek().wantToGoByMicroBus()) {
//                assistantFinished(msg)
            } else {
                bus.incBusyDoor()

                val passenger = passengers.dequeue()

                passenger.passengerIncomeIntoBus()

                passenger.numberOfDoorIn = msg.doorIdentifier

                BusHockeySimulation.logEntry(mySim().currentTime(), "Pasažier ${passenger.id} - začiatok nástupu do AUTOBUS: ${bus.id} (dvere: ${msg.doorIdentifier}) na zastávke ${bus.scheduler.getActualStop()!!.name}")

                updateStatistic(passenger)

                bus.addPassenger(passenger)

                val msgCopy = msg.createCopy()

                var sample: Double

                if (bus.isMicroBus()) {
                    sample = myAgent().incomeGeneratorMicroBus.sample()
                } else {
                    sample = myAgent().incomeGeneratorBus.sample()
                }

                hold(sample, msgCopy)
            }
        }

        if (!bus.isBusy()) {
            assistantFinished(msg)
        }
    }

    private fun updateStatistic(passenger: PassengerEntity) {
        myAgent().averageWaitingStat.addSample(passenger.getWaitingTime())
        myAgent().getBusStopAdministration().busStops[passenger.type.name]!!.addWaitingStat(passenger)
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
