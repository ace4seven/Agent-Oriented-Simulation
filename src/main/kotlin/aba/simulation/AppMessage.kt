package aba.simulation

import OSPABA.*
import aba.entities.PassengerEntity
import aba.entities.Vehicle
import helper.BusStop

class AppMessage : MessageForm {

    var vehicle: Vehicle? = null

    var passenger: PassengerEntity? = null
    var outGoingPassenger: PassengerEntity? = null
    var passengerIncomeStop: BusStop? = null
    var doorIdentifier = 0

    var isFirstTraveller = true

    constructor(sim: Simulation) : super(sim) {}

    constructor(original: AppMessage) : super(original) {
        // copy() is called in superclass
    }

    override fun createCopy(): MessageForm {
        return AppMessage(this)
    }

    override fun copy(message: MessageForm) {
        super.copy(message)
        val original = message as AppMessage
        // Copy attributes
        vehicle = original.vehicle
        passenger = original.passenger
        passengerIncomeStop = original.passengerIncomeStop
        isFirstTraveller = original.isFirstTraveller
        doorIdentifier = original.doorIdentifier
    }

}
