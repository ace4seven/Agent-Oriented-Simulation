package aba.managers;

import OSPABA.*;
import aba.simulation.*;
import aba.agents.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="5"
public class ManagerBusStop extends Manager
{
	public ManagerBusStop(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentStation", id="28", type="Notice"
	public void processWaitForBus(MessageForm message)
	{
	}

	//meta! sender="AgentStation", id="31", type="Request"
	public void processBusArrival(MessageForm message)
	{
	}

	//meta! sender="IncomeIntoBusCA", id="58", type="Finish"
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
		case Mc.busArrival:
			processBusArrival(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.waitForBus:
			processWaitForBus(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentBusStop myAgent()
	{
		return (AgentBusStop)super.myAgent();
	}

}
