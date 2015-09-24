package experiment.fish;

import java.awt.Point;
import java.util.Iterator;

import core.Agent;
import core.Engine;
import core.Environment;
import gui.Graph;

public class PopulationGraph extends Graph {

	
	private static final long serialVersionUID = 1L;

	public PopulationGraph(int width, int height) {
		super(width, height);
	}
	
	public void update(Engine engine) {
		
		int countShark = 0;
		int countTuna = 0;
		
		Iterator<Agent> it = engine.getAgents();
		
		while(it.hasNext()) {
			Agent agent = it.next();
			
			if(agent instanceof Shark) {
				countShark++;
			}
			else if(agent instanceof Tuna) {
				countTuna++;
			}
		}
		
		addPoint("shark", new Point(engine.getTurn(), countShark));
		addPoint("tuna", new Point(engine.getTurn(), countTuna));
	}

}
