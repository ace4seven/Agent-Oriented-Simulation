package helper

import OSPRNG.UniformContinuousRNG
import OSPRNG.UniformDiscreteRNG
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import aba.simulation.BusHockeySimulation
import javafx.application.Platform

/** Author: Bc. Juraj Macak **/

interface AnalyzatorObserver {
    fun analyzatorFinish()
}

class Analyzator {
    private var microbuses = arrayListOf<Vehicle>()

    private var scheduleGenerator: UniformDiscreteRNG? = null

    private var replicationCount = 10
    private val experimentExporter = helper.ExperimentExporter("experiment_results.csv")

    private var indexStart = 0

    private var indexStop = 1000

    private var numberOfVehicles = 100
    private var maxNumberOfVehicles = 150

    private var hasMicrobuses: Boolean = false

    private var preferedStrategy = TravelStrategyType.NO_WAIT
    private var preferredBusType: BusType? = null

    private var actualResult = 0
    private var numberOfResults = 0

    private var observer: AnalyzatorObserver? = null

    private val core = BusHockeySimulation()

    fun register(observer: AnalyzatorObserver) {
        this.observer = observer
    }

    fun changeFileName(value: String) {
        experimentExporter.changeFileName(value)
    }

    fun addNumberOfResults(value: Int) {
        numberOfResults = value
    }

    fun addIndexStop(value: Int) {
        indexStop = value
    }

    fun addVehiclesCount(min: Int, max: Int) {
        numberOfVehicles = min
        maxNumberOfVehicles = max
    }

    fun addScheduleTimeBorder(min: Int, max: Int) {
        scheduleGenerator = UniformDiscreteRNG(min, max)
    }

    fun setHasMicrobuses(value: Boolean) {
        hasMicrobuses = value
    }

    fun setTravelStrategy(strategy: TravelStrategyType) {
        preferedStrategy = strategy
    }

    fun setBusType(type: BusType?) {
        preferredBusType = type
    }

    // MARK: - public

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

        if (hasMicrobuses) {
            for (i in 1..13) {
                microbuses.add(
                        Vehicle(Vehicle.getUniqueID(),
                                BusLink.generateRandom(),
                                BusType.MICROBUS,
                                TravelStrategyType.WAIT,
                                scheduleGenerator!!.sample().toDouble(), core))
            }

            microbuses.forEach {
                core.addVehicle(it)
            }
        }

        for(k in 1..numberOfVehicles) {
            core.addVehicle(
                    Vehicle(Vehicle.getUniqueID(),
                            BusLink.generateRandom(),
                            if (preferredBusType == null) BusType.generateRandom() else preferredBusType!!,
                            preferedStrategy,
                            scheduleGenerator!!.sample().toDouble(), core
                    )
            )
        }
    }

    fun startExperiments(completion: () -> Unit) {
        experimentExporter.initializeWriter()

        core.onSimulationDidFinish {
            it as BusHockeySimulation
            if (it.averageNoOnTime!!.mean() <= 0.07 && it.averageWaitingTimeStat!!.mean() <= 600) {

                println("Nájdené riešenie")

                actualResult += 1

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

            if (actualResult >= numberOfResults || numberOfVehicles >= maxNumberOfVehicles) {
                Platform.runLater(Runnable {
                    completion()
                })
            } else {
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
        }

        prepareSimulation()
    }

}