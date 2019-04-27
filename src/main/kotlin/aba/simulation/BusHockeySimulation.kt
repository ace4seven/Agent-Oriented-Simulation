package aba.simulation

import OSPABA.*
import aba.agents.*

class MySimulation : Simulation() {

    private var _agentModel: AgentModel? = null

    private var _agentStation: AgentStation? = null

    private var _agentBusStop: AgentBusStop? = null

    private var _agentTransport: AgentTransport? = null

    private var _agentBus: AgentBus? = null

    private var _agentEnviroment: AgentEnviroment? = null

    init {
        init()
    }

    public override fun prepareSimulation() {
        super.prepareSimulation()
        // Create global statistcis
    }

    public override fun prepareReplication() {
        super.prepareReplication()
        // Reset entities, queues, local statistics, etc...
    }

    public override fun replicationFinished() {
        // Collect local statistics into global, update UI, etc...
        super.replicationFinished()
    }

    public override fun simulationFinished() {
        // Dysplay simulation results
        super.simulationFinished()
    }

    //meta! userInfo="Generated code: do not modify", tag="begin"
    private fun init() {
        setAgentModel(AgentModel(Id.agentModel, this, null))
        setAgentStation(AgentStation(Id.agentStation, this, agentModel()))
        setAgentBusStop(AgentBusStop(Id.agentBusStop, this, agentStation()))
        setAgentTransport(AgentTransport(Id.agentTransport, this, agentStation()))
        setAgentBus(AgentBus(Id.agentBus, this, agentStation()))
        setAgentEnviroment(AgentEnviroment(Id.agentEnviroment, this, agentModel()))
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
}
