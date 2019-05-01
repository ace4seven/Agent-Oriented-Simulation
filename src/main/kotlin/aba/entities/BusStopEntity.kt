package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import OSPDataStruct.SimQueue
import aba.simulation.BusHockeySimulation
import helper.BusStop
import model.LinkCell

/** Author: Bc. Juraj Macak **/


class BusStopEntity(val type: BusStop, val sim: Simulation): Entity(sim) {

    private var waitingPassengersQueue = SimQueue<PassengerEntity>()
    private var waitingBuses = mutableListOf<Vehicle>()

    fun addPassenger(passenger: PassengerEntity) {
        passenger.passengerCameInBusStop()

        waitingPassengersQueue.add(passenger)
    }

    fun getAvailableWaitingBus(): Vehicle? {
        waitingBuses.forEach {
            if (it.getFreeCapacity() > 0 && !it.hasFreeDoor()) {
                return it
            }
        }

        return null
    }

    fun getWaitingPassengersQueue(): SimQueue<PassengerEntity> {
        return waitingPassengersQueue
    }

    fun transformToCell(): LinkCell {
        val cell = LinkCell()

        cell.busStop = this.type.formattedStop()
        cell.peopleCount = "${this.waitingPassengersQueue.count()} cestujúcich"

        return cell
    }

}