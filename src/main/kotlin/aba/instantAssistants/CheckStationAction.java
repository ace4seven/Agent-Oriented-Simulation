package aba.instantAssistants;

import OSPABA.*;
import aba.simulation.*;
import aba.agents.*;

//meta! id="60"
public class CheckStationAction extends Action
{
	public CheckStationAction(int id, Simulation mySim, CommonAgent myAgent)
	{
		super(id, mySim, myAgent);
	}

	@Override
	public void execute(MessageForm message)
	{
	}

	@Override
	public AgentStation myAgent()
	{
		return (AgentStation)super.myAgent();
	}

}
