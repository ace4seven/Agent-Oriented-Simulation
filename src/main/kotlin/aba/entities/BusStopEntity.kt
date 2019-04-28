package aba.entities

import OSPABA.Entity
import OSPDataStruct.SimQueue
import aba.simulation.BusHockeySimulation
import helper.BusStop

/** Author: Bc. Juraj Macak **/

class BusStopEntity(val type: BusStop, sim: BusHockeySimulation): Entity(sim) {
    private var waitingPassengersQueue = SimQueue<PassengerEntity>()

    fun addPassenger(passenger: PassengerEntity) {
        passenger.passengerCameInBusStop()

        waitingPassengersQueue.add(passenger)
    }
}