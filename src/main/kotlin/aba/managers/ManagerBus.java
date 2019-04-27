package aba.managers;

import OSPABA.*;
import aba.simulation.*;
import aba.agents.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="47"
public class ManagerBus extends Manager
{
	public ManagerBus(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="PrepareForStartCA", id="50", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentStation", id="51", type="Request"
	public void processPrepareForStart(MessageForm message)
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
		case Mc.prepareForStart:
			processPrepareForStart(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentBus myAgent()
	{
		return (AgentBus)super.myAgent();
	}

}
