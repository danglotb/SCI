package core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Engine extends Observable {

	private List<Agent> agents;
	private int sleepTime;
	private int cptTurn;
	
	public Engine(int sleepTime) {
		this.agents = new ArrayList<Agent>();
		this.sleepTime = sleepTime;
	}

	public void run(int nbTurn) {

		for (this.cptTurn = 0; nbTurn == 0 ? true : this.cptTurn < nbTurn; this.cptTurn++) {
			Collections.shuffle(agents);
			for (int j = 0; j < agents.size(); j++) {
				if (agents.get(j).isAlive())
					agents.get(j).execute(this);
				else
					agents.remove(j--);
			}
			
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

	public int getTurn() {
		return this.cptTurn;
	}

}
