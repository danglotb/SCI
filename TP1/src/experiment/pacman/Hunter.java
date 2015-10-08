package experiment.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Agent;
import core.Engine;

public class Hunter extends Agent {

	public Hunter(Environment environment, int x, int y) {
		super(environment, x, y);
	}

	public void execute(Engine engine) {
		int minX = -1, minY = -1, minValue = Integer.MAX_VALUE;
		for (int x = this.x-1 ; x <= this.x+1 ; x++) {
			for (int y = this.y-1 ; y <= this.y+1 ; y++) {
				if (((x >= 0 && x < environment.getWidth()) && (y >= 0 && y < environment.getHeight())) && (((Environment)environment).getDijkstra()[x][y] != -1)) {
					if (((Environment)environment).getDijkstra()[x][y] < minValue) {
						minValue = ((Environment)environment).getDijkstra()[x][y];
						minX = x;
						minY = y;
					}
				}
			}
		}
		if(minValue != Integer.MAX_VALUE) {
			if(environment.getAgent(minX, minY) != null) {
				Agent a = environment.getAgent(minX, minY);
				
				if(a instanceof Attractor)
					a.die();
				
				System.out.println(a);
				//engine.stop();
			}
			environment.moveAgent(this, minX, minY);
		}
	}
	
	public void paint(Graphics g, Rectangle r) {
		g.setColor(Color.red);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

}
