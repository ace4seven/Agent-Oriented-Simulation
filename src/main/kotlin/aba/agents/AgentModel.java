package aba.agents;

import OSPABA.*;
import aba.simulation.*;
import aba.managers.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="2"
public class AgentModel extends Agent
{
	public AgentModel(int id, Simulation mySim, Agent parent)
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
		new ManagerModel(Id.managerModel, mySim(), this);
		addOwnMessage(Mc.travelingProcess);
		addOwnMessage(Mc.travelerArrival);
	}
	//meta! tag="end"
}
