package environment;

import agent.Ball;

public class Environment {
	
	private Ball[][] board;
	private int width;
	private int height;

	public Environment(int boardWidth, int boardHeight) {
		this.board = new Ball[boardWidth][boardHeight];
		this.width = boardWidth;
		this.height = boardHeight;
	}
	
	public Ball getAgent(int x, int y) {
		return this.board[x][y];
	}

	public void setAgent(Ball ball, int x, int y) {
		this.board[x][y] = ball;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
