package aba.agents;

import OSPABA.*;
import aba.simulation.*;
import aba.managers.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="1"
public class AgentEnviroment extends Agent
{
	public AgentEnviroment(int id, Simulation mySim, Agent parent)
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
		new ManagerEnviroment(Id.managerEnviroment, mySim(), this);
		new IncomeScheduler(Id.incomeScheduler, mySim(), this);
		addOwnMessage(Mc.travellerExit);
	}
	//meta! tag="end"
}
