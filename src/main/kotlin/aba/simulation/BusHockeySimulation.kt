package aba.simulation

import OSPABA.*
import OSPRNG.ExponentialRNG
import aba.agents.*
import aba.entities.Vehicle
import helper.Constants
import helper.Formatter
import model.BusProgressCell
import model.LogCell
import model.LogEntry
import java.util.*
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

    companion object {
        var logEntries = LinkedList<LogEntry>()

        fun logEntry(time: Double, desc: String) {
            logEntries.add(LogEntry(LogCell.index, time, desc))
            LogCell.inc()
        }
    }

    public override fun prepareSimulation() {
        super.prepareSimulation()

        Constants.availableBusStops.forEach {
            agentEnviroment()!!.arrivalGenerator[it.name] = ExponentialRNG(it.generateInterval().lambda)
        }
    }

    public override fun prepareReplication() {
        super.prepareReplication()

        BusHockeySimulation.logEntries.clear()
        agentModel()?.startSimulation()
    }

    public override fun replicationFinished() {
        // Collect local statistics into global, update UI, etc...
        super.replicationFinished()
    }

    public override fun simulationFinished() {
        super.simulationFinished()

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
            cell.type = it.type.formattedName()
            cell.strategy = it.strategy.formattedName()
            cell.activity = it.currentActivity
            cell.progress = "${if (it.isDeployed) Formatter.round2Decimals(it.getRouteProgress()) else "-"} %"
            cell.currentStop = "${it.getActualStop().formattedStop()}"
            cell.nextStop =  "${it.getNextStop().formattedStop()}"
            cell.link = it.link.formattedName()
            cell.freeCapacity = "${it.getFreeCapacity()}"
            cell.numbOfTravelers = "${it.getNumberOfPassengers()}"

            result.add(cell)
        }

        return result
    }

}
