package experiment.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import core.Agent;
import core.Engine;
import core.Environment;

public class Tuna extends Agent {

	private int timeForReproduction;
	private int timeSinceReproduce;

	public Tuna(Environment environment, int x, int y, int time) {
		super(environment, x, y);
		this.timeForReproduction = time;
		this.timeSinceReproduce = 0;
	}

	public void execute(Engine engine) {
		List<Point> list = this.environment.getEmptyNeighborHood(this);
		if (list.size() > 0) {
			Random r = new Random();
			this.environment.moveAgent(this, list.get(r.nextInt(list.size())));// move
		}
		if (this.timeSinceReproduce > this.timeForReproduction) {
			list = this.environment.getEmptyNeighborHood(this);
			if (list.size() > 0) {
				Random r = new Random();
				Point p = list.get(r.nextInt(list.size()));
				engine.addAgent(new Tuna(this.environment, p.x, p.y,
						this.timeForReproduction));// make a baby
			}
			this.timeSinceReproduce = 0;
		}
		
		this.timeSinceReproduce++;
	}

	public void paint(Graphics g, Rectangle r) {
		g.setColor(Color.blue);
		g.fillRect(r.x, r.y, r.width, r.height);	
	}

}
