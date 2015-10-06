package experiment.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Agent;
import core.Engine;
import core.Environment;

public class Hunter extends Agent {
	
	private Agent target;
	
	public Hunter(Environment environment, int x, int y, Agent target) {
		super(environment, x, y);
		this.target = target;
	}

	public void execute(Engine engine) {
		int [][] dTarget = Dijkstra.compute(environment.getAgents(), target.getX(), target.getY());
		int minX = -1, minY = -1, minValue = Integer.MAX_VALUE;
		for (int x = this.x-1 ; x < this.x+1 ; x++) {
			for (int y = this.y-1 ; y < this.y+1 ; y++) {
				if ( ((x >= 0 && x < environment.getWidth()) && (y >= 0 && y < environment.getHeight())) && (dTarget[x][y] != -1)) {
					if (dTarget[x][y] < minValue) {
						minValue = dTarget[x][y];
						minX = x;
						minY = y;
					}
				}
			}
		}
		if(minValue != Integer.MAX_VALUE) {
			if(environment.getAgent(minX, minY) != null) {
				Agent a = environment.getAgent(minX, minY);
				a.die();
				engine.stop();
			}
			environment.moveAgent(this, minX, minY);
		}
	}
	
	public void paint(Graphics g, Rectangle r) {
		g.setColor(Color.red);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

}
