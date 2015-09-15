package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import agent.Agent;

public class Engine extends Observable {
	
	private List<Agent> agents;
	
	public Engine() {
		this.agents = new ArrayList<Agent>();
	}

	public void run(int nbTurn) {
		for(int i=0; nbTurn == 0 ? true : i<nbTurn; i++) {
			Collections.shuffle(agents);
			for(int j=0; j<agents.size(); j++)
				agents.get(j).execute();
			
			this.setChanged();
			this.notifyObservers();
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public void addAgent(Agent agent) {
		this.agents.add(agent);
		agent.initialize();
	}

}
