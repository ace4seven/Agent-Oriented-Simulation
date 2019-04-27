package aba.entities

import OSPABA.Entity
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

abstract class Vehicle(val link: BusLink, val type: BusType, val strategy: TravelStrategyType, sim: BusHockeySimulation): Entity(sim) {

    private val scheduler = BusScheduler(link)
}