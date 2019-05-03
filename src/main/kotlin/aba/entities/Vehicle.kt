package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import OSPDataStruct.SimQueue
import aba.simulation.BusHockeySimulation
import com.sun.org.apache.xpath.internal.operations.Bool
import helper.BusLink
import helper.BusScheduler
import helper.BusStop


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

    fun formattedName(): String {
        when(this) {
            SMALL -> return "Malý"
            BIG -> return "Veľký"
            MICROBUS -> return "Mikrobus"
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

    fun hasWaitingStrategy(): Boolean {
        when(this) {
            WAIT -> return true
        }

        return false
    }

    fun formattedName(): String {
        when(this) {
            WAIT -> return "S čakaním"
            NO_WAIT -> return "Bez čakania"
        }
    }
}

abstract class Vehicle(val id: Int,
                       val link: BusLink,
                       val type: BusType,
                       val strategy: TravelStrategyType,
                       val deployTime: Double,
                       val sim: Simulation): Entity(sim) {

    val scheduler = BusScheduler(link)
    var currentActivity = "-"
    var isDeployed = false

    var hasWait = false

    private var passengers = SimQueue<PassengerEntity>()

    var busyDoors = 0

    var isReadyForNextStop = false

    fun getActualStop(): BusStop {
        return scheduler.getActualStop()!!
    }

    fun getNextStop(): BusStop {
        return scheduler.getNextStop()!!
    }

    fun getPassengers(): SimQueue<PassengerEntity> {
        return passengers
    }

    fun getNumberOfPassengers(): Int {
        return passengers.count()
    }

    fun getFreeCapacity(): Int {
        return type.capacity() - passengers.count()
    }

    fun getRouteProgress(): Double {
        val percentInterval = (scheduler.getEndTime() - scheduler.getStarTime())
        if (percentInterval == 0.0) {
            return 0.0
        }
        val progressTime = sim.currentTime() - scheduler.getStarTime()
        val progress = progressTime / percentInterval * 100

        if (progress > 100) {
            return 100.0
        }

        return progress
    }

    fun prepareToMoveNextStop() {
        scheduler.prepareToMoveNextStop(sim.currentTime())
    }

    fun initStartStats() {
        scheduler.initTransportStats(mySim().currentTime())
    }

    fun resetStartsStats() {
        scheduler.resetTransposrtStats()
    }

    fun addPassenger(passenger: PassengerEntity) {
        passenger.passengerIncomeIntoBus()

        passengers.add(passenger)
    }

    fun incBusyDoor() {
        busyDoors += 1
    }

    fun decBusyDoor() {
        busyDoors -= 1
    }

    fun isBusy(): Boolean {
        return busyDoors != 0
    }

    fun hasFreeDoor(): Boolean {
        return  busyDoors < type.numbOfDors()
    }

    fun isMicroBus(): Boolean {
        when(type) {
            BusType.MICROBUS -> return true
        }

        return false
    }

}