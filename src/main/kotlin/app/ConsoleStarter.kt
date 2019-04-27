package app

import aba.entities.BusEntity
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import aba.simulation.BusHockeySimulation
import helper.BusLink
import helper.BusScheduler

/** Author: Bc. Juraj Macak **/

fun main(args: Array<String>) {

    testSimulation()
}

private fun testSimulation() {
    val core = BusHockeySimulation()

    core.addVehicle(BusEntity(BusLink.LINK_A, BusType.SMALL, TravelStrategyType.NO_WAIT, core))
    core.addVehicle(BusEntity(BusLink.LINK_B, BusType.BIG, TravelStrategyType.WAIT, core))
    core.addVehicle(BusEntity(BusLink.LINK_C, BusType.BIG, TravelStrategyType.NO_WAIT, core))

    core.setSimSpeed(60.0,0.1)

//    core.onRefreshUI {
//        println(it.currentTime())
//    }

    core.simulate(1, 10000.0)
}

private fun testScheduler() {
    val scheduler = BusScheduler(BusLink.LINK_C)

    for (i in 1..1000) {
        Thread.sleep(scheduler.getDuration()!!.toLong())
        println("${scheduler.getNextStop()} - duration: ${scheduler.getDuration()}")
        scheduler.moveToAnotherStop()
    }
}