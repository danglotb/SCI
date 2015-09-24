package experiment.bille;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import core.Agent;
import core.Engine;
import core.Environment;

public class Ball extends Agent {

	private int velocityX;
	private int velocityY;
	private Color color;
	
	public Ball(Environment environment, int x, int y, int velocityX, int velocityY, Color color) {
		super(environment, x, y);
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.color = color;
	}
	
	public void execute(Engine engine) {
		this.environment.setAgent(null, this.x, this.y);
		
		int oldX = this.x;
		int oldY = this.y;
		
		//Move on X
		this.x += this.velocityX;
		if(this.x < 0) {
			this.x = -this.x;
			this.velocityX = -this.velocityX;
		} else if(this.x >= this.environment.getWidth()) {
			this.x = 2*this.environment.getWidth()-this.x-1;
			this.velocityX = -this.velocityX;
		}
		//Move on Y
		this.y += this.velocityY;
		if(this.y < 0) {
			this.y = -this.y;
			this.velocityY = -this.velocityY;
		} else if(this.y >= this.environment.getHeight()) {
			this.y = 2*this.environment.getHeight()-this.y-1;
			this.velocityY = -this.velocityY;
		}
		
		//Collision with an other ball
		if(this.environment.getAgent(this.x, this.y) != null) {	
			Ball other = (Ball)this.environment.getAgent(this.x, this.y);
			if (other.velocityX == 0)
				other.velocityX = this.velocityX;
			else
				other.velocityX = -other.velocityX;
			if (other.velocityY == 0)
				other.velocityY = this.velocityY;
			else
				other.velocityY = -other.velocityY;
			
			this.velocityX = -this.velocityX;
			this.velocityY = -this.velocityY;
			
			this.x = oldX;
			this.y = oldY;	
		}
		
		this.environment.setAgent(this, this.x, this.y);
	}

	public void paint(Graphics g, Rectangle r) {
		g.setColor(this.color);
		g.fillOval(r.x, r.y, r.width, r.height);
	}

}
