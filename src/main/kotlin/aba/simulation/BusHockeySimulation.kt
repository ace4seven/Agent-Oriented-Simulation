package aba.simulation

import OSPABA.*
import aba.agents.*
import aba.entities.Vehicle
import model.BusProgressCell
import java.util.function.Consumer

class BusHockeySimulation : Simulation() {

    private var _agentModel: AgentModel? = null
    private var _agentStation: AgentStation? = null
    private var _agentBusStop: AgentBusStop? = null
    private var _agentTransport: AgentTransport? = null
    private var _agentBus: AgentBus? = null
    private var _agentEnviroment: AgentEnviroment? = null

    init {
        prepareAgents()
    }

    public override fun prepareSimulation() {
        super.prepareSimulation()
        // Create global statistcis
    }

    public override fun prepareReplication() {
        super.prepareReplication()

        // Reset entities, queues, local statistics, etc...
        agentModel()?.startSimulation()
    }

    public override fun replicationFinished() {
        // Collect local statistics into global, update UI, etc...
        super.replicationFinished()
    }

    public override fun simulationFinished() {
        // Dysplay simulation results
        super.simulationFinished()

        agentBusStop()!!.getBusStopAdministration().busStops.forEach {
            println("${it.key} -- ${it.value.getWaitingPassengersQueue().count()}")
        }
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun prepareAgents() {
        setAgentModel(AgentModel(Id.agentModel, this, null))
        setAgentStation(AgentStation(Id.agentStation, this, agentModel()!!))
        setAgentBusStop(AgentBusStop(Id.agentBusStop, this, agentStation()!!))
        setAgentTransport(AgentTransport(Id.agentTransport, this, agentStation()!!))
        setAgentBus(AgentBus(Id.agentBus, this, agentStation()!!))
        setAgentEnviroment(AgentEnviroment(Id.agentEnviroment, this, agentModel()!!))
    }

    fun agentModel(): AgentModel? {
        return _agentModel
    }

    fun setAgentModel(agentModel: AgentModel) {
        _agentModel = agentModel
    }

    fun agentStation(): AgentStation? {
        return _agentStation
    }

    fun setAgentStation(agentStation: AgentStation) {
        _agentStation = agentStation
    }

    fun agentBusStop(): AgentBusStop? {
        return _agentBusStop
    }

    fun setAgentBusStop(agentBusStop: AgentBusStop) {
        _agentBusStop = agentBusStop
    }

    fun agentTransport(): AgentTransport? {
        return _agentTransport
    }

    fun setAgentTransport(agentTransport: AgentTransport) {
        _agentTransport = agentTransport
    }

    fun agentBus(): AgentBus? {
        return _agentBus
    }

    fun setAgentBus(agentBus: AgentBus) {
        _agentBus = agentBus
    }

    fun agentEnviroment(): AgentEnviroment? {
        return _agentEnviroment
    }

    fun setAgentEnviroment(agentEnviroment: AgentEnviroment) {
        _agentEnviroment = agentEnviroment
    }
    //meta! tag="end"

    fun addVehicle(vehicle: Vehicle) {
        _agentBus?.addVehicle(vehicle)
    }

    fun getVehiclesDatasource(): List<BusProgressCell> {
        val result = mutableListOf<BusProgressCell>()

        agentBus()?.vehicles?.forEach {
            val cell = BusProgressCell()
            cell.id = it.id
            cell.activity = it.currentActivity
            cell.currentStop = "${it.getActualStop()} -> ${it.getNextStop()} (${it.getRouteProgress()})"
            cell.link = it.link.name
            cell.freeCapacity = "0"
            result.add(cell)
        }

        return result
    }

}
