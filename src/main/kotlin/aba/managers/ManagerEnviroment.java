package aba.managers;

import OSPABA.*;
import aba.simulation.*;
import aba.agents.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="1"
public class ManagerEnviroment extends Manager
{
	public ManagerEnviroment(int id, Simulation mySim, Agent myAgent)
	{
		super(id, mySim, myAgent);
		init();
	}

	@Override
	public void prepareReplication()
	{
		super.prepareReplication();
		// Setup component for the next replication

		if (petriNet() != null)
		{
			petriNet().clear();
		}
	}

	//meta! sender="AgentModel", id="67", type="Notice"
	public void processTravellerExit(MessageForm message)
	{
	}

	//meta! sender="IncomeScheduler", id="44", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! userInfo="Process messages defined in code", id="0"
	public void processDefault(MessageForm message)
	{
		switch (message.code())
		{
		}
	}

	//meta! userInfo="Generated code: do not modify", tag="begin"
	public void init()
	{
	}

	@Override
	public void processMessage(MessageForm message)
	{
		switch (message.code())
		{
		case Mc.finish:
			processFinish(message);
		break;

		case Mc.travellerExit:
			processTravellerExit(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentEnviroment myAgent()
	{
		return (AgentEnviroment)super.myAgent();
	}

}
