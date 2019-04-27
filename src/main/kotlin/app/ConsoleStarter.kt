package app

import aba.simulation.BusHockeySimulation
import helper.BusLink
import helper.BusScheduler

/** Author: Bc. Juraj Macak **/

fun main(args: Array<String>) {

}

private fun testSimulation() {
    val core = BusHockeySimulation()

}

private fun testScheduler() {
    val scheduler = BusScheduler(BusLink.LINK_C)

    for (i in 1..1000) {
        Thread.sleep(scheduler.getDuration()!!.toLong())
        println("${scheduler.getNextStop()} - duration: ${scheduler.getDuration()}")
        scheduler.moveToAnotherStop()
    }
}