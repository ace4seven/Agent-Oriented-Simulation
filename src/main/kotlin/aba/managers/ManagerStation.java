package aba.managers;

import OSPABA.*;
import aba.simulation.*;
import aba.agents.*;
import aba.continualAssistants.*;
import aba.instantAssistants.*;

//meta! id="3"
public class ManagerStation extends Manager
{
	public ManagerStation(int id, Simulation mySim, Agent myAgent)
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

	//meta! sender="AgentModel", id="35", type="Request"
	public void processTravelingProcess(MessageForm message)
	{
	}

	//meta! sender="AgentBusStop", id="31", type="Response"
	public void processBusArrival(MessageForm message)
	{
	}

	//meta! sender="ExitTravelerCA", id="66", type="Finish"
	public void processFinish(MessageForm message)
	{
	}

	//meta! sender="AgentTransport", id="33", type="Response"
	public void processBusMoveStart(MessageForm message)
	{
	}

	//meta! sender="AgentBus", id="51", type="Response"
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
		case Mc.travelingProcess:
			processTravelingProcess(message);
		break;

		case Mc.busArrival:
			processBusArrival(message);
		break;

		case Mc.finish:
			processFinish(message);
		break;

		case Mc.prepareForStart:
			processPrepareForStart(message);
		break;

		case Mc.busMoveStart:
			processBusMoveStart(message);
		break;

		default:
			processDefault(message);
		break;
		}
	}
	//meta! tag="end"

	@Override
	public AgentStation myAgent()
	{
		return (AgentStation)super.myAgent();
	}

}
