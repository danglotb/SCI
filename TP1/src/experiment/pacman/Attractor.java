package experiment.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import core.Agent;
import core.Engine;
import core.Environment;

public class Attractor extends Agent {

	public Attractor(Environment environment, int x, int y) {
		super(environment, x, y);
	}

	public void execute(Engine engine) {
		List<Point> list = this.environment.getEmptyNeighborHood(this);
		if (list.size() > 0) {
			Random r = new Random();
			this.environment.moveAgent(this, list.get(r.nextInt(list.size())));// move
		}
	}

	public void paint(Graphics g, Rectangle r) {
		g.setColor(Color.yellow);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

}
