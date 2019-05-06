package helper

import OSPRNG.UniformContinuousRNG
import OSPRNG.UniformDiscreteRNG
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import aba.simulation.BusHockeySimulation
import javafx.application.Platform

/** Author: Bc. Juraj Macak **/

class Analyzator {

    var vehicles = arrayListOf<Vehicle>()
    var microbuses = arrayListOf<Vehicle>()

    var scheduleGenerator = UniformDiscreteRNG(0, 3600)
    var vehicleIndex = UniformDiscreteRNG(0, 999999)

    var replicationCount = 1

    val experimentExporter = helper.ExperimentExporter("experiment_results.csv")

    var indexStart = 0

    var indexStop = 1000

    var numberOfVehicles = 100
    var maxNumberOfVehicles = 150

    val core = BusHockeySimulation()

    private fun initVehiclesVariants() {
        for (i in 1..1000000) {
            vehicles.add(
                    Vehicle(i,
                            BusLink.generateRandom(),
                            BusType.generateRandom(),
                            TravelStrategyType.WAIT,
                            scheduleGenerator.sample().toInt().toDouble(), core
                    )
            )
        }
    }

    fun prepareSimulation() {
        val thread = object: Thread() {
            override fun run() {
                prepareValues()
                core.simulate(replicationCount)
            }
        }
        thread.start()
    }

    private fun prepareValues() {
        core.agentBus()!!.vehicles.clear()
        microbuses.clear()

        for (i in 1..13) {
            microbuses.add(
                    Vehicle(indexStop + i + 1,
                            BusLink.generateRandom(),
                            BusType.MICROBUS,
                            TravelStrategyType.WAIT,
                            scheduleGenerator.sample().toInt().toDouble(), core))
        }

        microbuses.forEach {
            core.addVehicle(it)
        }

        for(k in 1..numberOfVehicles) {
            core.addVehicle(
                    Vehicle(Vehicle.getUniqueID(),
                            BusLink.generateRandom(),
                            BusType.generateRandom(),
                            TravelStrategyType.WAIT,
                            scheduleGenerator.sample().toInt().toDouble(), core
                    )
            )
        }
    }

    fun startExperiments() {
        experimentExporter.initializeWriter()
        initVehiclesVariants()

        core.onSimulationDidFinish {
            it as BusHockeySimulation
            if (it.averageNoOnTime!!.mean() <= 0.07 && it.averageWaitingTimeStat!!.mean() <= 600) {

                println("Nájdené riešenie")

                experimentExporter.addBusHeader()

                var expenses = 0

                it.agentBus()!!.vehicles.forEach {
                    expenses += it.type.price()
                    experimentExporter.addRow(CSVBusEntry(it.link.formattedName(true), it.type.formattedName(true), it.strategy.formattedName(true), "${it.deployTime}"))
                }

                experimentExporter.addResultHeader()
                experimentExporter.addRow(CSVResultEntry(it.averageWaitingTimeStat!!.mean(), it.averageNoOnTime!!.mean(), it.averageMicrobusProfit.mean(), expenses))
                expenses = 0
                experimentExporter.addBlockLine()
            }

            if (indexStart < indexStop) {
                indexStart += 1

                if (indexStart % 10 == 0) {
                    println("INDEX replikacie ${indexStart} VEHICLES: ${numberOfVehicles}")
                }

                prepareSimulation()
            } else {
                numberOfVehicles += 1

                if (numberOfVehicles < maxNumberOfVehicles) {
                    indexStart = 0

                    prepareSimulation()
                } else {
                    experimentExporter.closeWriter()
                }
            }
        }
        prepareSimulation()
    }

}