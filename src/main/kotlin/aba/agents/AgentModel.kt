package aba.agents

import OSPABA.*
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.entities.PassengerEntity
import aba.instantAssistants.*
import helper.Constants

//meta! id="2"
class AgentModel(id: Int, mySim: Simulation, parent: Agent?) : Agent(id, mySim, parent) {
    private var passengerRegisterList = mutableListOf<PassengerEntity>()
        private set

    private var numbOfIncome = 0
    private var numberOfOutCome = 0
    private var noOnTimePassengers = 0

    init {
        init()
    }

    fun getNumberOfPassengers(): Int {
        return numbOfIncome
    }

    fun getPercentagePeopleNoOnTime(): Double {
        return noOnTimePassengers.toDouble() / numbOfIncome.toDouble()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication

        passengerRegisterList.clear()
        numberOfOutCome = 0
        numbOfIncome = 0
        noOnTimePassengers = 0
    }

    fun registerPassenger(passenger: PassengerEntity) {
        passengerRegisterList.add(passenger)

        numbOfIncome += 1
    }

    fun passengerArrivedStadion() {
        numberOfOutCome += 1
    }

    fun passengerComeLate() {
        noOnTimePassengers += 1
    }

    fun isAllPassengersBoarded(): Boolean {
        return numbOfIncome == numberOfOutCome
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerModel(Id.managerModel, mySim(), this)

        addOwnMessage(Mc.travelingProcess)
        addOwnMessage(Mc.travelerArrival)
        addOwnMessage(Mc.passengerOut)
    }
    //meta! tag="end"

    fun startSimulation() {
        preparePassengers()
        prepareBusses()
    }

    private fun preparePassengers() {
        Constants.availableBusStops.forEach {
            val message = AppMessage(mySim())

            message.passengerIncomeStop = it

            message.setCode(Mc.initPassengers)
            message.setAddressee(mySim().findAgent(Id.agentEnviroment))

            manager().notice(message)
        }
    }

    private fun prepareBusses() {
        val message = AppMessage(mySim())

        message.setCode(Mc.initVehicles)
        message.setAddressee(mySim().findAgent(Id.agentStation))
        manager().notice(message)
    }

}
