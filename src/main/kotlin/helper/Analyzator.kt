package helper

import OSPRNG.UniformContinuousRNG
import OSPRNG.UniformDiscreteRNG
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import aba.simulation.BusHockeySimulation

/** Author: Bc. Juraj Macak **/

class Analyzator(val core: BusHockeySimulation) {

    var vehicles = arrayListOf<Vehicle>()
    var scheduleGenerator = UniformContinuousRNG(0.0, 3600.0)
    var vehicleIndex = UniformDiscreteRNG(0, 9999)

    var replicationCount = 10

    val experimentExporter = helper.ExperimentExporter("experiment_results.csv")

    fun startExperiments() {
        experimentExporter.initializeWriter()

        for (i in 1..10000) {
            vehicles.add(
                    Vehicle(i,
                            BusLink.generateRandom(),
                            BusType.generateRandom(),
                            TravelStrategyType.generateRandom(),
                            scheduleGenerator.sample(), core
                    )
            )
        }

        for (i in 1..3000) {
            core.agentBus()!!.vehicles.clear()
            core.stopSimulation()

            for (j in 5..50) {
                core.agentBus()!!.vehicles.clear()
                for(k in 1..j) {
                    core.addVehicle(vehicles[vehicleIndex.sample()])
                }

                core.simulate(replicationCount)
                core.onSimulationDidFinish {
                    it as BusHockeySimulation

                    if (it.averageNoOnTime!!.mean() <= 0.07 && it.averageWaitingTimeStat!!.mean() <= 600) {
                        experimentExporter.addBusHeader()
                        it.agentBus()!!.vehicles.forEach {
                            experimentExporter.addRow(CSVBusEntry(it.link.formattedName(), it.type.formattedName(), it.strategy.formattedName(), "${it.deployTime}"))
                        }
                        experimentExporter.addResultHeader()
                        experimentExporter.addRow(CSVResultEntry(it.averageWaitingTimeStat!!.mean(), it.averageNoOnTime!!.mean()))
                        experimentExporter.addBlockLine()
                    }
                }
            }
        }
    }

}