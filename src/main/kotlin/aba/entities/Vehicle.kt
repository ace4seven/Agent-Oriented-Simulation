package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import OSPDataStruct.SimQueue
import OSPStat.WStat
import aba.simulation.BusHockeySimulation
import com.sun.org.apache.xpath.internal.operations.Bool
import helper.BusLink
import helper.BusScheduler
import helper.BusStop
import view.window.BusStopDetail
import java.util.*


/** Author: Bc. Juraj Macak **/

enum class BusType {
    SMALL, BIG, MICROBUS;

    companion object {
        val random = Random()

        fun generateRandom(): BusType {
            var rand = random.nextDouble()

            if (rand < 0.50) {
                return SMALL
            }

            return BIG
        }
    }

    fun numbOfDors(): Int {
        when(this) {
            SMALL -> return 3
            BIG -> return 4
            MICROBUS -> return 1
        }
    }

    fun formattedName(isSample: Boolean = false): String {
       if (isSample) {
           when(this) {
               SMALL -> return "S"
               BIG -> return "L"
               MICROBUS -> return "M"
           }
       } else {
           when(this) {
               SMALL -> return "Malý"
               BIG -> return "Veľký"
               MICROBUS -> return "Mikrobus"
           }
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

    companion object {
        val random = Random()

        fun generateRandom(): TravelStrategyType {
            val rand = random.nextDouble()

            if (rand < 0.50) {
                return WAIT
            }

            return NO_WAIT
        }
    }

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

    fun formattedName(isSample: Boolean = false): String {
       if (isSample) {
           when(this) {
               WAIT -> return "WAIT"
               NO_WAIT -> return "NO WAIT"
           }
       } else {
           when(this) {
               WAIT -> return "S čakaním"
               NO_WAIT -> return "Bez čakania"
           }
       }
    }
}

class Vehicle(val id: Int,
                       val link: BusLink,
                       val type: BusType,
                       val strategy: TravelStrategyType,
                       val deployTime: Double,
                       val sim: Simulation): Entity(sim) {

    companion object {
        private var uniqueID = 0

        fun getUniqueID(): Int {
            uniqueID += 1

            return uniqueID
        }

        fun clear() {
            uniqueID = 0
        }
    }

    val scheduler = BusScheduler(link)
    var currentActivity = "-"
    var isDeployed = false
    var waitingStop: BusStop? = null
    var hasLeftWithFullCapacity = false
    var isWaiting = false
    var firstLoad = false

    var busyFactorheight = WStat(sim)
        private set

    var profit = 0

    var hasWait = false

    private var passengers = SimQueue<PassengerEntity>()

    var busyDoors = 0

    var circuit = 1
        private set

    fun updateBusHeigtFactor() {
        busyFactorheight.addSample(passengers.count().toDouble())
    }

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

    fun incCircuit() {
        circuit += 1
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

    fun clear() {
        scheduler.clear()
        passengers.clear()
        currentActivity = "-"
        profit = 0
        circuit = 1
        hasWait = false
        busyDoors = 0
        waitingStop = null
        hasLeftWithFullCapacity = false
        isWaiting = false
        firstLoad = false
        busyFactorheight = WStat(sim)
    }

    fun payForticket() {
        profit += 1
    }

    fun clone(): Vehicle {
        return Vehicle(this.id, this.link, this.type, this.strategy, this.deployTime, this.sim)
    }

}