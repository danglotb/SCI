package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Engine extends Observable {

	private List<Agent> agents;
<<<<<<< HEAD
	
	private int sleepTime;
	
=======

	private int sleepTime;

>>>>>>> d98164924b93a844b5efd56744643340d34e1ad4
	public Engine(int sleepTime) {
		this.agents = new ArrayList<Agent>();
		this.sleepTime = sleepTime;
	}

	public void run(int nbTurn) {

		for (int i = 0; nbTurn == 0 ? true : i < nbTurn; i++) {
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
<<<<<<< HEAD
				Thread.sleep(this.sleepTime);
=======
				Thread.sleep(sleepTime);
>>>>>>> d98164924b93a844b5efd56744643340d34e1ad4
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
