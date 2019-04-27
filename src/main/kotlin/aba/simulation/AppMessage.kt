package aba.simulation

import OSPABA.*
import aba.entities.Vehicle

class AppMessage : MessageForm {
    
    var vehicle: Vehicle? = null

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
    }
}
