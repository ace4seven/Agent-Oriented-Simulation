package aba.managers

import OSPABA.*
import OSPRNG.TriangularRNG
import aba.simulation.*
import aba.agents.*
import helper.Constants
import helper.Messages

//meta! id="5"
class ManagerBusStop(id: Int, mySim: Simulation, myAgent: Agent) : Manager(id, mySim, myAgent) {

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

    //meta! sender="AgentStation", id="28", type="Notice"
    fun processWaitForBus(message: MessageForm) {
        val msg = message as AppMessage

        myAgent().getBusStopAdministration().busStops[msg.passengerIncomeStop!!.name]!!.addPassenger(msg.passenger!!)
    }

    //meta! sender="AgentStation", id="31", type="Request"
    fun processBusArrival(message: MessageForm) {
        val msg = message as AppMessage

        if (msg.vehicle!!.scheduler.isFinalDestination()) {
            message.setAddressee(Id.OutComFromBusCA)

            val msg = message
            msg.vehicle?.currentActivity = Messages.busPassengersOutcome

            if (Constants.isDebug) {
                println("Autobus ${msg.vehicle!!.id} vystupovanie: ${msg.vehicle!!.getNumberOfPassengers()}")
            }

            startContinualAssistant(msg)
        } else {
            msg.vehicle?.currentActivity = Messages.busPassengersIncome

            if (Constants.isDebug) {
                println("Autobus ${msg.vehicle!!.id} (obsadeny: ${msg.vehicle!!.getNumberOfPassengers()}) prichod na zastavku ${msg.vehicle!!.scheduler.getActualStop()} (${myAgent().getBusStopPassengers(msg.vehicle!!.getActualStop()).count()})")
            }

            msg.setAddressee(Id.incomeIntoBusCA)

            startContinualAssistant(msg)
        }
    }

    //meta! sender="IncomeIntoBusCA", id="58", type="Finish"
    fun processFinish(message: MessageForm) {
        val msg = message as AppMessage
        val bus = msg.vehicle!!

        when(msg.sender().id()) {
            Id.incomeIntoBusCA -> {
                if (!bus.isBusy()) {
                    message.setCode(Mc.busArrival)

                    if(Constants.isDebug) {
                        println("KAPACITA zastavky po odchode: ${myAgent().getBusStopPassengers(bus.getActualStop()).count()}")
                    }
                    response(message)
                }
            }

            Id.OutComFromBusCA -> {
                val bus  = msg.vehicle!!
                if (!bus.isBusy()) {
                    message.setCode(Mc.busArrival)

                    bus.currentActivity = Messages.busReturn

                    if (Constants.isDebug) {
                        println("Autobus ${msg.vehicle!!.id} vystupovanie ukončené: ${msg.vehicle!!.getNumberOfPassengers()} ${bus.busyDoors}")
                    }

                    response(message)
                }
            }
        }

         // Start autobusu na dalsiu linku
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
            Mc.busArrival -> processBusArrival(message)
            Mc.finish -> processFinish(message)
            Mc.waitForBus -> processWaitForBus(message)

            else -> processDefault(message)
        }
    }
    //meta! tag="end"

    override fun myAgent(): AgentBusStop {
        return super.myAgent() as AgentBusStop
    }

}
