package experiment.pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Agent;
import core.Engine;
import core.Environment;

public class Wall extends Agent {

	public Wall(Environment environment, int x, int y) {
		super(environment, x, y);
	}

	public void execute(Engine engine) {
		return;
	}

	public void paint(Graphics g, Rectangle r) {
		g.setColor(Color.darkGray);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

}
