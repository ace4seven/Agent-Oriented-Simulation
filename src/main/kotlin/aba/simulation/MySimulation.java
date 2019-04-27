package aba.simulation;

import OSPABA.*;
import aba.agents.*;

public class MySimulation extends Simulation
{
	public MySimulation()
	{
		init();
	}

	@Override
	public void prepareSimulation()
	{
		super.prepareSimulation();
		// Create global statistcis
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Reset entities, queues, local statistics, etc...
	}

	@Override
	public void replicationFinished()
	{
		// Collect local statistics into global, update UI, etc...
		super.replicationFinished();
	}

	@Override
	public void simulationFinished()
	{
		// Dysplay simulation results
		super.simulationFinished();
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		setAgentModel(new AgentModel(Id.agentModel, this, null));
		setAgentStation(new AgentStation(Id.agentStation, this, agentModel()));
		setAgentBusStop(new AgentBusStop(Id.agentBusStop, this, agentStation()));
		setAgentTransport(new AgentTransport(Id.agentTransport, this, agentStation()));
		setAgentBus(new AgentBus(Id.agentBus, this, agentStation()));
		setAgentEnviroment(new AgentEnviroment(Id.agentEnviroment, this, agentModel()));
	}

	private AgentModel _agentModel;

public AgentModel agentModel()
	{ return _agentModel; }

	public void setAgentModel(AgentModel agentModel)
	{_agentModel = agentModel; }

	private AgentStation _agentStation;

public AgentStation agentStation()
	{ return _agentStation; }

	public void setAgentStation(AgentStation agentStation)
	{_agentStation = agentStation; }

	private AgentBusStop _agentBusStop;

public AgentBusStop agentBusStop()
	{ return _agentBusStop; }

	public void setAgentBusStop(AgentBusStop agentBusStop)
	{_agentBusStop = agentBusStop; }

	private AgentTransport _agentTransport;

public AgentTransport agentTransport()
	{ return _agentTransport; }

	public void setAgentTransport(AgentTransport agentTransport)
	{_agentTransport = agentTransport; }

	private AgentBus _agentBus;

public AgentBus agentBus()
	{ return _agentBus; }

	public void setAgentBus(AgentBus agentBus)
	{_agentBus = agentBus; }

	private AgentEnviroment _agentEnviroment;

public AgentEnviroment agentEnviroment()
	{ return _agentEnviroment; }

	public void setAgentEnviroment(AgentEnviroment agentEnviroment)
	{_agentEnviroment = agentEnviroment; }
	//meta! tag="end"
}
