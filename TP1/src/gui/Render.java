package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import agent.Agent;
import agent.Ball;

import core.Engine;

import environment.Environment;

public class Render extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private Environment environment;
	private Engine engine;
	private int caseSize;
	
	public Render(Engine engine, Environment environment, int caseSize) {
		this.environment = environment;
		this.engine = engine;
		this.caseSize = caseSize;
		
		this.setSize(this.environment.getWidth()*this.caseSize, this.environment.getHeight()*this.caseSize);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.environment.getWidth()*this.caseSize, this.environment.getHeight()*this.caseSize);
		g.setColor(Color.blue);
		
		Iterator<Agent> it = this.engine.getAgents();
		
		while(it.hasNext()) {
			Ball current = (Ball)it.next();
			g.fillOval(current.getX()*this.caseSize, current.getY()*this.caseSize, this.caseSize, this.caseSize);
		}
	  }

	public void update(Observable o, Object arg) {
		final Render render = this;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				render.repaint();
			}
		});
		
	}


}
