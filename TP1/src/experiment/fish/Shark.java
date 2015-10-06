package experiment.fish;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import core.Agent;
import core.Engine;
import core.Environment;

public class Shark extends Agent {

	private int timeForStarving;
	private int timeForReproduction;

	private int timeSinceFeed;
	private int timeSinceReproduce;

	public Shark(Environment environment, int x, int y, int timeForStarving,
			int timeForReproduction) {
		super(environment, x, y);
		this.timeForReproduction = timeForReproduction;
		this.timeForStarving = timeForStarving;
		this.timeSinceFeed = 0;
		this.timeSinceReproduce = 0;
	}

	public void execute(Engine engine) {
		
		if (this.timeSinceFeed > this.timeForStarving) {// die for starving
			this.die();
		} else {
			List<Agent> neighbors = this.environment.getNeighborHood(this);
			Agent target = getTuna(neighbors);
			if (target != null) {// eat the tuna
				target.die();// the tuna is dead
				this.environment.moveAgent(this, target.getX(), target.getY());
				this.timeSinceFeed = 0;// the shark is feed
			} else {
				List<Point> list = this.environment.getEmptyNeighborHood(this);
				if (list.size() > 0) {
					Random r = new Random();
					this.environment.moveAgent(this,
							list.get(r.nextInt(list.size())));//move
				}
			}
			if (this.timeSinceReproduce > this.timeForReproduction) {
				List<Point> list = this.environment.getEmptyNeighborHood(this);
				if (list.size() > 0) {
					Random r = new Random();
					Point p = list.get(r.nextInt(list.size()));
					engine.addAgent(new Shark(this.environment, p.x, p.y,
							this.timeForStarving, this.timeForReproduction));// make a baby
					this.timeSinceReproduce = 0;
				}
			}
		}
		this.timeSinceReproduce++;
		this.timeSinceFeed++;
	}

	private Agent getTuna(List<Agent> neighbors) {
		Collections.shuffle(neighbors);
		for (Agent a : neighbors) {
			if (a instanceof Tuna) {
				return a;
			}
		}
		return null;
	}

	public void paint(Graphics g, Rectangle r) {
		g.setColor(Color.red);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

}
