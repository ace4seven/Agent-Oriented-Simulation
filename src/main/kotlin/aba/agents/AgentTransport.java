package aba.agents;

import OSPABA.*;
import aba.simulation.*;
import aba.managers.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="6"
public class AgentTransport extends Agent
{
	public AgentTransport(int id, Simulation mySim, Agent parent)
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
		new ManagerTransport(Id.managerTransport, mySim(), this);
		new TravellingCA(Id.travellingCA, mySim(), this);
		addOwnMessage(Mc.busMoveStart);
	}
	//meta! tag="end"
}
