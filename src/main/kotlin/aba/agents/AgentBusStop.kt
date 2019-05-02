package aba.agents

import OSPABA.*
import OSPDataStruct.SimQueue
import OSPRNG.TriangularRNG
import OSPRNG.UniformContinuousRNG
import aba.simulation.*
import aba.managers.*
import aba.continualAssistants.*
import aba.entities.BusStopAdministration
import aba.entities.PassengerEntity
import helper.BusStop
import helper.Constants

//meta! id="5"
class AgentBusStop(id: Int, mySim: Simulation, parent: Agent) : Agent(id, mySim, parent) {

    private var busStopAdministration: BusStopAdministration? = null

    val incomeGeneratorBus = TriangularRNG(0.6, 1.2, 4.2, Constants.randomSeader)
    val outComeGeneratorBus = TriangularRNG(0.6, 1.2, 4.2, Constants.randomSeader)
    val incomeGeneratorMicroBus = UniformContinuousRNG(6.0, 10.0, Constants.randomSeader)
    val outGeneratorMicroBus = 4.0

    init {
        init()
    }

    override fun prepareReplication() {
        super.prepareReplication()
        // Setup component for the next replication

        busStopAdministration = BusStopAdministration(mySim())

        busStopAdministration?.busStops?.forEach {
            println(it.key)
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        ManagerBusStop(Id.managerBusStop, mySim(), this)
        IncomeIntoBusCA(Id.incomeIntoBusCA, mySim(), this)
        OutCameFromBusCA(Id.OutComFromBusCA, mySim(), this)

        addOwnMessage(Mc.waitForBus)
        addOwnMessage(Mc.busArrival)
        addOwnMessage(Mc.passengerFinishIncome)
        addOwnMessage(Mc.passengerOutFromBusFinish)
    }
    //meta! tag="end"

    fun getBusStopAdministration(): BusStopAdministration {
        return busStopAdministration!!
    }

    fun getBusStopPassengers(stop: BusStop): SimQueue<PassengerEntity> {
        return busStopAdministration!!.busStops[stop.getConcreteStop().name]!!.getWaitingPassengersQueue()
    }

}
