package aba.entities

import OSPABA.Entity
import OSPDataStruct.SimQueue
import aba.simulation.BusHockeySimulation
import helper.BusLink
import helper.BusScheduler


/** Author: Bc. Juraj Macak **/

enum class BusType {
    SMALL, BIG, MICROBUS;

    fun numbOfDors(): Int {
        when(this) {
            SMALL -> return 3
            BIG -> return 4
            MICROBUS -> return 1
        }
    }

    fun capacity(): Int {
        when(this) {
            SMALL -> return 107
            BIG -> return 186
            MICROBUS -> return 8
        }
    }

    fun price(): Int {
        when(this) {
            SMALL -> return 320000
            BIG -> return 545000
            MICROBUS -> return 0
        }
    }

}

enum class TravelStrategyType {
    WAIT, NO_WAIT;

    fun getExtraWaitingTime(): Double {
        when(this) {
            WAIT -> return 90.0
            NO_WAIT -> return 0.0
        }
    }
}

abstract class Vehicle(val id: Int, val link: BusLink, val type: BusType, val strategy: TravelStrategyType, val sim: BusHockeySimulation): Entity(sim) {

    val scheduler = BusScheduler(link)
    var currentActivity = "-"

    private var passengers = SimQueue<PassengerEntity>()

    fun getActualStop(): String {
        return scheduler.getActualStop()!!.name
    }

    fun getRouteProgress(): String {
        val percentInterval = (scheduler.getEndTime() - scheduler.getStarTime())
        val progressTime = sim.currentTime() - scheduler.getStarTime()
        val progress = progressTime / percentInterval * 100

        return "${progress} %"
    }

    fun prepareToMoveNextStop() {
        scheduler.prepareToMoveNextStop(sim.currentTime())
    }

    fun getNextStop(): String {
        return scheduler.getNextStop()!!.name
    }

    fun addPassenger(passenger: PassengerEntity) {
        passenger.passengerIncomeIntoBus()

        passengers.add(passenger)
    }

}