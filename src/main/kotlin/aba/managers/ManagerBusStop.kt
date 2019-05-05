package aba.managers

import OSPABA.*
import OSPRNG.TriangularRNG
import aba.simulation.*
import aba.agents.*
import aba.entities.BusType
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
    }

    //meta! sender="AgentStation", id="28", type="Notice"
    fun processWaitForBus(message: MessageForm) {
        val msg = message as AppMessage
        msg.passenger?.passengerCameInBusStop()

        // Ak su autobusy, ktoré čakajú, treba prioritne ísť tam a nie čakať na zastávke

        val waitingBus = myAgent().getBusStopEntity(msg.passengerIncomeStop!!).getAvailableWaitingBus()

        if (waitingBus != null) {
            when(waitingBus.type) {
                BusType.MICROBUS -> {
                    if (msg.passenger!!.wantToGoByMicroBus()) {
                        msg.setAddressee(Id.incomeWaitingBusCA)
                        msg.vehicle?.currentActivity = Messages.busPassengersIncome
                        startContinualAssistant(msg)
                    } else {
                        myAgent().getBusStopAdministration().busStops[msg.passengerIncomeStop!!.name]!!.addPassenger(msg.passenger!!)
                    }
                }
                else -> {
                    msg.setAddressee(Id.incomeWaitingBusCA)
                    msg.passenger?.incomingWaitingBus = waitingBus
                    msg.vehicle?.currentActivity = Messages.busPassengersIncome
                    startContinualAssistant(msg)
                }
            }
        } else {
            myAgent().getBusStopAdministration().busStops[msg.passengerIncomeStop!!.name]!!.addPassenger(msg.passenger!!)
        }
    }

    //meta! sender="AgentStation", id="31", type="Request"
    fun processBusArrival(message: MessageForm) {
        val msg = message as AppMessage

        if (msg.vehicle!!.scheduler.isFinalDestination()) {
            prepareOutcomePassengers(msg)
        } else {
            msg.vehicle?.currentActivity = Messages.busPassengersIncome

            if (Constants.isDebug) {
                println("Autobus ${msg.vehicle!!.id} (obsadeny: ${msg.vehicle!!.getNumberOfPassengers()}) prichod na zastavku ${msg.vehicle!!.scheduler.getActualStop()} (${myAgent().getBusStopPassengers(msg.vehicle!!.getActualStop()).count()})")
            }

            msg.setAddressee(Id.incomeIntoBusCA)

            startContinualAssistant(msg)
        }
    }

    private fun prepareOutcomePassengers(msg: AppMessage) {
        msg.setAddressee(Id.OutComFromBusCA)
        msg.vehicle?.currentActivity = Messages.busPassengersOutcome

        if (Constants.isDebug) {
            println("Autobus ${msg.vehicle!!.id} vystupovanie: ${msg.vehicle!!.getNumberOfPassengers()}")
        }

        startContinualAssistant(msg)
    }

    //meta! sender="IncomeIntoBusCA", id="58", type="Finish"
    fun processFinish(message: MessageForm) {
        val msg = message as AppMessage

        when(msg.sender().id()) {
            Id.incomeIntoBusCA -> {
                val bus = msg.vehicle!!
                if (!bus.isBusy()) {
                    message.setCode(Mc.busArrival)

                    if(Constants.isDebug) {
                        println("KAPACITA zastavky po odchode: ${myAgent().getBusStopPassengers(bus.getActualStop()).count()}")
                    }

                    if (bus.strategy.hasWaitingStrategy() && bus.getFreeCapacity() > 0 && !bus.hasWait) { // Nastavenie na 1.5 min cakanie
                        myAgent().getBusStopEntity(bus.getActualStop()).addBusForWaiting(msg)

                        val cpyMessage = msg.createCopy()
                        cpyMessage.setAddressee(Id.busWaitingCA)

                        bus.hasWait = true

                        bus.currentActivity = Messages.extraWait

                        startContinualAssistant(cpyMessage)
                    } else {
                        msg.vehicle!!.hasWait = false
                        response(message)
                    }
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

                    bus.incCircuit()

                    response(message)
                }
            }

            Id.busWaitingCA -> {
                // Pokial vrati message, autobus este neodisiel, pokial vrati null, autobus odisiel
                val bus = msg.vehicle!!

                if (!bus.isBusy()) {
                    val waitingBusMessage = myAgent().returnWaitingBusMsg(bus.getActualStop(), bus.id)

                    if (waitingBusMessage != null) {
                        waitingBusMessage.vehicle!!.hasWait = true

                        bus.currentActivity = Messages.busPassengersIncome

                        waitingBusMessage.setCode(Mc.busArrival)
                        waitingBusMessage.setAddressee(this)

                        notice(waitingBusMessage)
                    }
                }
            }

            // TODO: Not shure if good
            Id.incomeWaitingBusCA -> {
                val waitingBus = msg.passenger!!.incomingWaitingBus

                if (waitingBus!!.getFreeCapacity() == 0) {
                    val waitingFrontMessage = myAgent().returnWaitingBusMsg(waitingBus.getActualStop(), waitingBus.id)!!

                    waitingFrontMessage.setCode(Mc.busArrival)

                    response(waitingFrontMessage)
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
