package aba.entities

import OSPABA.Entity
import OSPABA.Simulation
import aba.simulation.BusHockeySimulation
import helper.BusStop

/** Author: Bc. Juraj Macak **/

class BusStopAdministration(val sim: Simulation): Entity(sim) {

    var busStops = mutableMapOf<String, BusStopEntity>()

    init {
        busStops["A_A"] = BusStopEntity(BusStop.A_A, sim)
        busStops["A_B"] = BusStopEntity(BusStop.A_B, sim)
        busStops["A_C"] = BusStopEntity(BusStop.A_C, sim)
        busStops["A_D"] = BusStopEntity(BusStop.A_D, sim)
        busStops["A_E"] = BusStopEntity(BusStop.A_E, sim)
        busStops["A_F"] = BusStopEntity(BusStop.A_F, sim)
        busStops["A_G"] = BusStopEntity(BusStop.A_G, sim)
        busStops["A_H"] = BusStopEntity(BusStop.A_H, sim)
        busStops["A_I"] = BusStopEntity(BusStop.A_I, sim)
        busStops["A_J"] = BusStopEntity(BusStop.A_J, sim)
        busStops["A_K"] = BusStopEntity(BusStop.A_K, sim)
        busStops["A_L"] = BusStopEntity(BusStop.A_L, sim)
        
        busStops["K1"] = BusStopEntity(BusStop.K1, sim)
        busStops["K2"] = BusStopEntity(BusStop.K2, sim)
        busStops["K3"] = BusStopEntity(BusStop.K3, sim)

        busStops["B_A"] = BusStopEntity(BusStop.B_A, sim)
        busStops["B_B"] = BusStopEntity(BusStop.B_B, sim)
        busStops["B_C"] = BusStopEntity(BusStop.B_C, sim)
        busStops["B_D"] = BusStopEntity(BusStop.B_D, sim)
        busStops["B_E"] = BusStopEntity(BusStop.B_E, sim)
        busStops["B_F"] = BusStopEntity(BusStop.B_F, sim)
        busStops["B_G"] = BusStopEntity(BusStop.B_G, sim)
        busStops["B_H"] = BusStopEntity(BusStop.B_H, sim)
        busStops["B_I"] = BusStopEntity(BusStop.B_I, sim)
        busStops["B_J"] = BusStopEntity(BusStop.B_J, sim)

        busStops["C_A"] = BusStopEntity(BusStop.C_A, sim)
        busStops["C_B"] = BusStopEntity(BusStop.C_B, sim)
        busStops["C_C"] = BusStopEntity(BusStop.C_C, sim)
        busStops["C_D"] = BusStopEntity(BusStop.C_D, sim)
        busStops["C_E"] = BusStopEntity(BusStop.C_E, sim)
        busStops["C_F"] = BusStopEntity(BusStop.C_F, sim)
        busStops["C_G"] = BusStopEntity(BusStop.C_G, sim)

        busStops["STATION"] = BusStopEntity(BusStop.STATION, sim)
    }

    fun busStopHasPassenger(busStop: BusStop): Boolean {
        return busStops[busStop.getConcreteStop().name]?.getWaitingPassengersQueue()!!.isEmpty()
    }

    fun clear() {
        busStops.forEach {
            it.value.clear()
        }
    }

}