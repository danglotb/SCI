package experiment.fish;

import gui.Graph;

import java.awt.Point;
import java.util.Iterator;

import core.Agent;
import core.Engine;
import core.Environment;

public class PopulationRatioGraph extends Graph {
	
	private static final long serialVersionUID = 1L;

	public PopulationRatioGraph(int width, int height) {
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
		
		addPoint("ratio", new Point(countTuna, countShark));
	}

}