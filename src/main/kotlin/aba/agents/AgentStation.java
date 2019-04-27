package aba.agents;

import OSPABA.*;
import aba.simulation.*;
import aba.managers.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="3"
public class AgentStation extends Agent
{
	public AgentStation(int id, Simulation mySim, Agent parent)
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
		new ManagerStation(Id.managerStation, mySim(), this);
		new CheckStationAction(Id.checkStationAction, mySim(), this);
		new ExitTravelerCA(Id.exitTravelerCA, mySim(), this);
		addOwnMessage(Mc.travelingProcess);
		addOwnMessage(Mc.busArrival);
		addOwnMessage(Mc.busMoveStart);
		addOwnMessage(Mc.prepareForStart);
	}
	//meta! tag="end"
}
