package core;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Agent {
	
	protected Environment environment;
	
	protected int x;
	protected int y;
	
	private boolean isAlive;
	
	public Agent(Environment environment, int x, int y) {
		this.environment = environment;
		this.isAlive = true;
		this.x = x;
		this.y = y;
	}
	
	public void initialize() {
		this.environment.setAgent(this, this.x, this.y);
	}
	
	public abstract void execute(Engine engine);
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void die() {
		this.isAlive = false;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public abstract void paint(Graphics g, Rectangle r);
}
