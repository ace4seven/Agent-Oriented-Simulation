package aba.entities

import OSPABA.Entity
import aba.simulation.BusHockeySimulation
import helper.BusStop
import java.lang.Exception

/** Author: Bc. Juraj Macak **/

class PassengerEntity(val type: BusStop, val sim: BusHockeySimulation): Entity(sim)  {

    private var incomeBusStop: Double? = null
    private var icomeBus: Double? = null

    fun passengerCameInBusStop() {
        incomeBusStop = sim.currentTime()
    }

    fun passengerIncomeIntoBus() {
        icomeBus = sim.currentTime()
    }

    fun getWaitingTime(): Double {
        if (icomeBus == null || incomeBusStop == null) {
            throw Exception("Passenger time not properly tracked")
        }

        return icomeBus!! - incomeBusStop!!
    }

}