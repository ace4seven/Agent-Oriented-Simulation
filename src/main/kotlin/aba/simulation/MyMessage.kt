package aba.simulation

import OSPABA.*

class MyMessage : MessageForm {
    constructor(sim: Simulation) : super(sim) {}

    constructor(original: MyMessage) : super(original) {
        // copy() is called in superclass
    }

    override fun createCopy(): MessageForm {
        return MyMessage(this)
    }

    override fun copy(message: MessageForm) {
        super.copy(message)
        val original = message as MyMessage
        // Copy attributes
    }
}
