package agent;

import environment.Environment;

public abstract class Agent {
	
	protected Environment environment;
	
	public Agent(Environment environment) {
		this.environment = environment;
	}
	
	public void initialize() {
		
	}
	
	public abstract void execute();
	
}
