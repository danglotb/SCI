package core;


import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

public class Engine extends Observable {

	private List<Agent> agents;
	private int sleepTime;
	private int cptTurn;
	private boolean run;
	
	public Engine(int sleepTime) {
		this.agents = new CopyOnWriteArrayList<Agent>();
		this.sleepTime = sleepTime;
	}

	public void stop() {
		this.run = false;
	}
	
	public void run(int nbTurn) {

		this.run = true;
		
		for (this.cptTurn = 0; nbTurn == 0 ? true : this.cptTurn < nbTurn && this.run ; this.cptTurn++) {
//			Collections.shuffle(agents);
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
