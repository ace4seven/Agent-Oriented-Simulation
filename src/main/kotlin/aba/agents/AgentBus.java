package aba.agents;

import OSPABA.*;
import aba.simulation.*;
import aba.managers.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="47"
public class AgentBus extends Agent
{
	public AgentBus(int id, Simulation mySim, Agent parent)
	{
		super(id, mySim, parent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	private void init()
	{
		new ManagerBus(Id.managerBus, mySim(), this);
		new PrepareForStartCA(Id.prepareForStartCA, mySim(), this);
		addOwnMessage(Mc.prepareForStart);
	}
	//meta! tag="end"
}
