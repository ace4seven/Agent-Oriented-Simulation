package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import aba.simulation.BusHockeySimulation
import helper.BusStop
import java.lang.Exception

/** Author: Bc. Juraj Macak **/

class PassengerEntity(val id: Int, val type: BusStop, val sim: Simulation): Entity(sim)  {

    private var incomeBusStop: Double? = null

    private var icomeBus: Double? = null
    private var outFromBus: Double? = null

    var numberOfDoorIn = 0
    var numberOfDoorOut = 0

    fun passengerCameInBusStop() {
        incomeBusStop = sim.currentTime()
    }

    fun passengerIncomeIntoBus() {
        icomeBus = sim.currentTime()
    }

    fun passengerOutComeFromBus() {
        outFromBus = sim.currentTime()
    }

    fun getWaitingTime(): Double {
        if (icomeBus == null || incomeBusStop == null) {
            throw Exception("Passenger time not properly tracked")
        }

        return icomeBus!! - incomeBusStop!!
    }

    companion object {
        var indexPassenger = 1

        fun incIndex() {
            indexPassenger += 1
        }
    }

}