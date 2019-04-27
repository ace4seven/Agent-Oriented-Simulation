package aba.agents;

import OSPABA.*;
import aba.simulation.*;
import aba.managers.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="5"
public class AgentBusStop extends Agent
{
	public AgentBusStop(int id, Simulation mySim, Agent parent)
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
		new ManagerBusStop(Id.managerBusStop, mySim(), this);
		new IncomeIntoBusCA(Id.incomeIntoBusCA, mySim(), this);
		addOwnMessage(Mc.waitForBus);
		addOwnMessage(Mc.busArrival);
	}
	//meta! tag="end"
}
