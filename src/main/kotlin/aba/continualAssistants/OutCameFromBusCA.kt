package aba.continualAssistants

import OSPABA.*
import aba.simulation.*
import aba.agents.*
import OSPABA.Process
import helper.Constants
import java.lang.Exception

//meta! id="65"
class OutCameFromBusCA(id: Int, mySim: Simulation, myAgent: CommonAgent) : Process(id, mySim, myAgent) {

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication
    }

    //meta! sender="AgentStation", id="66", type="Start"
    fun processStart(message: MessageForm) {
        val msg = message as AppMessage
        val bus = msg.vehicle!!

        var busPassengers = bus.getPassengers()

        if (bus.id == 1) {
//            println("Autobus")
        }

        for (i in 1..bus.type.numbOfDors()) {
            if (busPassengers.size > 0) {
                bus.incBusyDoor()

                val passenger = busPassengers.dequeue()

                BusHockeySimulation.logEntry(mySim().currentTime(), "Pasažier ${passenger.id} - začiatok odchodu z AUTOBUS: ${bus.id} (dvere: ${i}) na zastávke ${bus.scheduler.getActualStop()!!.name}")

                passenger.passengerOutComeFromBus()
                passenger.numberOfDoorOut = i

                val msgCopy = msg.createCopy() as AppMessage
                msgCopy.setCode(Mc.passengerOutFromBusFinish)
                msgCopy.doorIdentifier = i

                var sample: Double

                if (bus.isMicroBus()) {
                    sample = myAgent().outGeneratorMicroBus
                } else {
                    sample = myAgent().outComeGeneratorBus.sample()
                }

                hold(sample, msgCopy)
            }
        }

        assistantFinished(message)
    }

    //meta! userInfo="Process messages defined in code", id="0"
    fun processDefault(message: MessageForm) {
        when (message.code()) {

        }
    }

    fun processPassengerOutFromBusFinish(message: MessageForm) {
        val msg = message as AppMessage
        val bus = msg.vehicle!!

        bus.decBusyDoor()

        var busPassengers = bus.getPassengers()

        if(busPassengers.size > 0) {
            bus.incBusyDoor()

            val passenger = busPassengers.dequeue()

            BusHockeySimulation.logEntry(mySim().currentTime(), "Pasažier ${passenger.id} - začiatok odchodu z AUTOBUS: ${bus.id} (dvere: ${msg.doorIdentifier}) na zastávke ${bus.scheduler.getActualStop()!!.name}")

            passenger.passengerOutComeFromBus()
            passenger.numberOfDoorOut = msg.doorIdentifier

            val msgCopy = msg.createCopy()

            var sample: Double

            if (bus.isMicroBus()) {
                sample = myAgent().outGeneratorMicroBus
            } else {
                sample = myAgent().outComeGeneratorBus.sample()
            }

            hold(sample, msgCopy)
        }

        if (bus.busyDoors < 0) {
            throw Exception("Dors bad logic implemented")
        }

        if (!bus.isBusy()) {
            assistantFinished(msg)
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    override fun processMessage(message: MessageForm) {
        when (message.code()) {
            Mc.start -> processStart(message)
            Mc.passengerOutFromBusFinish -> processPassengerOutFromBusFinish(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBusStop {
        return super.myAgent() as AgentBusStop
    }
}
