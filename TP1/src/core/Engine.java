package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import agent.Agent;

public class Engine extends Observable {
	
	private List<Agent> agents;
	
	private int sleepTime;
	
	public Engine(int sleepTime) {
		this.agents = new ArrayList<Agent>();
		this.sleepTime = sleepTime;
	}

	public void run(int nbTurn) {
		
		for(int i=0; nbTurn == 0 ? true : i<nbTurn; i++) {
			Collections.shuffle(agents);
			for(int j=0; j<agents.size(); j++)
				agents.get(j).execute();
			
			this.setChanged();
			this.notifyObservers();
			
			try {
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	public void addAgent(Agent agent) {
		this.agents.add(agent);
		agent.initialize();
	}

	public Iterator<Agent> getAgents() {
		return this.agents.iterator();
	}

}
