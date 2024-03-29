package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import OSPDataStruct.SimQueue
import OSPStat.Stat
import OSPStat.WStat
import aba.simulation.AppMessage
import aba.simulation.BusHockeySimulation
import helper.BusStop
import model.LinkCell

/** Author: Bc. Juraj Macak **/


class BusStopEntity(val type: BusStop, sim: Simulation): Entity(sim) {

    private var waitingPassengersQueue = SimQueue<PassengerEntity>()
    private var waitingBuses = mutableMapOf<Int, AppMessage>()
    private var waitingOnStopStat = Stat()

    private var stopHeight: WStat = WStat(mySim())

    fun addPassenger(passenger: PassengerEntity) {
        waitingPassengersQueue.add(passenger)
    }

    fun updateStopHeight() {
        stopHeight.addSample(waitingPassengersQueue.count().toDouble())
    }

    fun addWaitingStat(passenger: PassengerEntity) {
        waitingOnStopStat.addSample(passenger.getWaitingTime())
    }

    fun getWaitingStats(): Stat {
        return waitingOnStopStat
    }

    fun getStopHeight(): WStat {
        return stopHeight!!
    }

    fun addBusForWaiting(message: AppMessage) {
        waitingBuses[message.vehicle!!.id] = message
    }

    fun removeVehicleFromWaitingQueue(id: Int): AppMessage? {
        return waitingBuses.remove(id)
    }

    fun getWaitingBus(id: Int): AppMessage? {
        return waitingBuses.get(id)
    }

    fun getAvailableWaitingBus(): Vehicle? {
        waitingBuses.forEach {
            if (it.value.vehicle!!.hasFreeDoor()) {
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
        waitingOnStopStat.clear()
        stopHeight?.clear()
    }

}