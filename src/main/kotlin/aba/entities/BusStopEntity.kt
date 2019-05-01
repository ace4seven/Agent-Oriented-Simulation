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

    fun addPassenger(passenger: PassengerEntity) {
        passenger.passengerCameInBusStop()

        waitingPassengersQueue.add(passenger)
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