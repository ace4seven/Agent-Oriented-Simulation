package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import OSPDataStruct.SimQueue
import aba.simulation.AppMessage
import aba.simulation.BusHockeySimulation
import helper.BusStop
import model.LinkCell

/** Author: Bc. Juraj Macak **/


class BusStopEntity(val type: BusStop, val sim: Simulation): Entity(sim) {

    private var waitingPassengersQueue = SimQueue<PassengerEntity>()
    private var waitingBuses = mutableMapOf<Int, AppMessage>()

    fun addPassenger(passenger: PassengerEntity) {
        waitingPassengersQueue.add(passenger)
    }

    fun addBusForWaiting(message: AppMessage) {
        waitingBuses[message.vehicle!!.id] = message
    }

    fun removeVehicleFromWaitingQueue(id: Int): AppMessage? {
        return waitingBuses.remove(id)
    }

    fun getAvailableWaitingBus(): Vehicle? {
        waitingBuses.forEach {
            if (it.value.vehicle!!.getFreeCapacity() > 0 && !it.value.vehicle!!.hasFreeDoor()) {
                return it.value.vehicle

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

    fun clear() {
        waitingPassengersQueue.clear()
        waitingBuses.clear()
    }

}