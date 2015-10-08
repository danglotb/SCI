package experiment.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import core.Agent;

public class Environment extends core.Environment {
	
	int [][] dijkstra;
	Map<Agent, Point> targets;

	public Environment(int boardWidth, int boardHeight) {
		super(boardWidth, boardHeight);
		this.dijkstra = new int[getWidth()][getHeight()];
		for (int x = 0 ; x < getWidth(); x++) {
			for (int y = 0 ; y < getHeight() ; y++) {
				this.dijkstra[x][y] = -1;
			}
		}
		this.targets = new HashMap<Agent, Point>();
	}
	
	public void recomputeDijkstra() {
		Dijkstra.compute(getAgents(), dijkstra, targets.values());
	}
	

	public int[][] getDijkstra() {
		return this.dijkstra;
	}
	
	public void updateTarget(Agent agent) {
		if(this.targets.containsKey(agent))
			this.targets.replace(agent, new Point(agent.getX(), agent.getY()));
		else
			this.targets.put(agent , new Point(agent.getX(), agent.getY()));
	}
	
	public void paint(Graphics g, Rectangle rectangle) {
		g.setColor(Color.white);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		
		int sizeX = rectangle.width/getWidth();
		int sizeY = rectangle.height/getHeight();
		
		for (int x = 0 ; x < getWidth(); x++) {
			for (int y = 0 ; y < getHeight() ; y++) {
				if(this.dijkstra[x][y] != -1) {
					g.setColor(new Color((float)this.dijkstra[x][y]/(float)getHeight(), (float)this.dijkstra[x][y]/(float)getHeight(), (float)this.dijkstra[x][y]/(float)getHeight()));
					g.fillRect(x*sizeX, sizeY*y, sizeX, sizeY);
				}
			}
		}
	}

	public void deleteTarget(Agent agent) {
		if(this.targets.containsKey(agent))
			this.targets.remove(agent, new Point(agent.getX(), agent.getY()));
	}

}
