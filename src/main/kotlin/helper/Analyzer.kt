package helper

import OSPRNG.UniformDiscreteRNG
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import aba.simulation.BusHockeySimulation
import javafx.application.Platform

/** Author: Bc. Juraj Macak **/

class Analyzer {
    private var microbuses = arrayListOf<Vehicle>()
    private var filemanager = FileManager()

    private var scheduleGenerator: UniformDiscreteRNG? = null

    private var replicationCount = 10
    private val experimentExporter = helper.ExperimentExporter()

    private var indexStart = 0

    private var indexStop = 1000

    private var numberOfVehicles = 100
    private var maxNumberOfVehicles = 150

    private var hasMicrobuses: Boolean = false

    private var preferedStrategy = TravelStrategyType.NO_WAIT
    private var preferredBusType: BusType? = null

    private var actualResult = 0
    private var numberOfResults = 0

    private var microbusProfit = 0.0

    private var buses = mutableListOf<Vehicle>()

    private var core = BusHockeySimulation()

    fun changeFileName(value: String) {
        experimentExporter.initializeWriter(value)
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

    fun prepareSimulationWithMicrobuses() {
        val thread = object: Thread() {
            override fun run() {
                var busesWithMicrobuses = mutableListOf<Vehicle>()

                val busesClone = mutableListOf<Vehicle>()

                buses.forEach {
                    busesClone.add(it.clone())
                }

                busesClone.forEach {
                    busesWithMicrobuses.add(it)
                }

                for (i in 1..13) {
                    busesWithMicrobuses.add(
                            Vehicle(Vehicle.getUniqueID(),
                                    BusLink.generateRandom(),
                                    BusType.MICROBUS,
                                    preferedStrategy,
                                    scheduleGenerator!!.sample().toDouble(), core))
                }

                core.agentBus()?.vehicles?.clear()

                busesWithMicrobuses.forEach {
                    core.agentBus()?.vehicles?.add(it)
                }

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
                                preferedStrategy,
                                scheduleGenerator!!.sample().toDouble(), core))
            }

            microbuses.forEach {
                core.addVehicle(it)
            }
        }

        var vehicles = mutableListOf<Vehicle>()

        var allLinksHasBus = false

        while (!allLinksHasBus) {
            vehicles.clear()

            for(k in 1..numberOfVehicles) {
                vehicles.add(
                        Vehicle(Vehicle.getUniqueID(),
                                BusLink.generateRandom(),
                                if (preferredBusType == null) BusType.generateRandom() else preferredBusType!!,
                                preferedStrategy,
                                scheduleGenerator!!.sample().toDouble(), core
                        )
                )
            }

            var linkACheck = 0
            var linkBCheck = 0
            var linkCCheck = 0

            vehicles.forEach {
                when (it.link) {
                    BusLink.LINK_A -> linkACheck += 1
                    BusLink.LINK_B -> linkBCheck += 1
                    BusLink.LINK_C -> linkCCheck += 1
                }
            }

            if (linkACheck != 0 && linkBCheck != 0 && linkCCheck !=0) {
                allLinksHasBus = true
            }
        }

        vehicles.forEach {
            core.agentBus()?.addVehicle(it)
        }

    }

    fun startExperimentsMicrobuses() {
        core = BusHockeySimulation()
        val busTables = filemanager.getBusSchedule()

        busTables.map { it.rawTime = it.scheduleTime.toDouble() }

        buses = busTables.map { it.transformToVehicle(core) }.toMutableList()

        core.onSimulationDidFinish {
            it as BusHockeySimulation

            if (it.averageMicrobusProfit.mean() > microbusProfit) {

                microbusProfit = it.averageMicrobusProfit.mean()

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

            if (indexStart < indexStop) {
                indexStart += 1

                println("INDEX Opakovanie ${indexStart}")
                prepareSimulationWithMicrobuses()
            } else {
                experimentExporter.closeWriter()
            }
        }

        prepareSimulationWithMicrobuses()
    }

    fun startExperiments(completion: () -> Unit) {
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
                experimentExporter.closeWriter()
                Platform.runLater(Runnable {
                    completion()
                })
            } else {
                if (indexStart < indexStop) {
                    indexStart += 1

                    if (indexStart % 10 == 0) {
                        println("INDEX opakovania ${indexStart} VEHICLES: ${numberOfVehicles}")
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