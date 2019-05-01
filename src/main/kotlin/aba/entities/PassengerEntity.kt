package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import aba.simulation.BusHockeySimulation
import helper.BusStop
import helper.Formatter
import model.PassengerCell
import java.lang.Exception

/** Author: Bc. Juraj Macak **/

class PassengerEntity(val id: Int, val type: BusStop, val sim: Simulation): Entity(sim)  {

    var incomeBusStop: Double? = null

    var icomeBus: Double? = null
    var outFromBus: Double? = null

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
        if (icomeBus == null) {
            return sim.currentTime() - incomeBusStop!!
        }

        return icomeBus!! - incomeBusStop!!
    }

    fun wantToGoByMicroBus(): Boolean {
        return (sim.currentTime() - incomeBusStop!! > 360)
    }

    companion object {
        var indexPassenger = 1

        fun incIndex() {
            indexPassenger += 1
        }
    }

    fun transformToCell(): PassengerCell {
        val cell = PassengerCell()

        cell.id = id
        cell.stopArrival = Formatter.timeFormatterInc(incomeBusStop!!)
        cell.waitingTime = Formatter.timeFormatterInc(getWaitingTime(), true)
        cell.busArrival = if (icomeBus != null) Formatter.timeFormatterInc(this.icomeBus!!) else "-"
        cell.busOut = if (outFromBus != null) Formatter.timeFormatterInc(outFromBus!!) else "-"
        cell.doorIn = "Dvere ${numberOfDoorIn}"
        cell.doorOut = "Dvere ${numberOfDoorOut}"

        return cell
    }

}