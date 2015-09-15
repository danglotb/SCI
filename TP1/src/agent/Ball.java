package agent;

import environment.Environment;

public class Ball extends Agent {
	
	public int x;
	public int y;
	
	public int velocityX;
	public int velocityY;


	public Ball(Environment environment, int x, int y, int velocityX, int velocityY) {
		super(environment);
		this.x = x;
		this.y = y;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	public void initialize() {
		this.environment.setAgent(this, this.x, this.y);
	}

	public void execute() {
		this.environment.setAgent(null, this.x, this.y);
		
		this.x += this.velocityX;
		if(this.x < 0) {
			this.x = -this.x;
			this.velocityX = -this.velocityX;
		}
		else if(this.x >= this.environment.getWidth()) {
			this.x = 2*this.environment.getWidth()-this.x-1;
			this.velocityX = -this.velocityX;
		}
		this.y += this.velocityY;
		if(this.y < 0) {
			this.y = -this.y;
			this.velocityY = -this.velocityY;
		}
		else if(this.y >= this.environment.getHeight()) {
			this.y = 2*this.environment.getHeight()-this.y-1;
			this.velocityY = -this.velocityY;
		}
		
		this.environment.setAgent(this, this.x, this.y);
	}

}
