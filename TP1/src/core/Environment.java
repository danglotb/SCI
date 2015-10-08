package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Environment {

	private Agent[][] board;
	private int width;
	private int height;

	public Environment(int boardWidth, int boardHeight) {
		this.board = new Agent[boardWidth][boardHeight];
		this.width = boardWidth;
		this.height = boardHeight;
	}
	
	public Agent[][] getAgents() {
		return this.board;
	}

	public Agent getAgent(int x, int y) {
		return this.board[x][y];
	}

	public void setAgent(Agent agent, int x, int y) {
		this.board[x][y] = agent;
	}
	
	public void moveAgent(Agent agent, int x, int y) {
		this.board[agent.getX()][agent.getY()] = null;
		this.board[x][y] = agent;
		agent.setPosition(x, y);
	}
	
	public void setAgent(Agent agent, Point p) {
		this.board[p.x][p.y] = agent;
	}
	
	public void moveAgent(Agent agent, Point p) {
		this.board[agent.getX()][agent.getY()] = null;
		this.board[p.x][p.y] = agent;
		agent.setPosition(p.x, p.y);
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public List<Agent> getNeighborHood(Agent a) {
		List<Agent> neighbors = new ArrayList<Agent>();
		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if ( (x != 0 || y != 0) && a.getX()+x >= 0 && a.getX()+x < this.width 
						&& a.getY()+y >= 0 && a.getY()+y < this.height && board[a.getX()+x][a.getY()+y] != null)
					neighbors.add(board[a.getX()+x][a.getY()+y]);
			}
		}
		return neighbors;
	}
	
	public List<Point> getEmptyNeighborHood(Agent a) {
		List<Point> neighbors = new ArrayList<Point>();
		for (int x = -1 ; x < 2 ; x++) {
			for (int y = -1 ; y < 2 ; y++) {
				if( (x != 0 || y != 0) && (a.getX()+x >= 0) && (a.getX()+x < this.width) 
						&& (a.getY()+y >= 0) && (a.getY()+y < this.height) && (this.board[a.getX()+x][a.getY()+y] == null)) {
					neighbors.add(new Point(a.getX()+x, a.getY()+y));
				}
			}
		}
		return neighbors;
	}

	public List<Point> getEmptyCell() {
		List<Point> cells = new ArrayList<Point>();
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				if (this.board[x][y] == null) {
					cells.add(new Point(x, y));
				}
			}
		}
		return cells;
	}

	public void paint(Graphics g, Rectangle rectangle) {
		g.setColor(Color.white);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);

	}
}
