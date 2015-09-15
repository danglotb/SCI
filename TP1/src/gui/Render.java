package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import environment.Environment;

public class Render extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private Environment environment;
	private int caseSize;
	
	public Render(Environment environment, int caseSize) {
		this.environment = environment;
		this.caseSize = caseSize;
		
		this.setSize(this.environment.getWidth()*this.caseSize, this.environment.getHeight()*this.caseSize);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.environment.getWidth()*this.caseSize, this.environment.getHeight()*this.caseSize);
		g.setColor(Color.blue);
		for(int i=0; i<this.environment.getWidth(); i++) {
			for(int j=0; j<this.environment.getHeight(); j++) {
				if(this.environment.getAgent(i, j) != null)
					g.fillOval(i*this.caseSize, j*this.caseSize, this.caseSize, this.caseSize);
			}
		}
	  }

	public void update(Observable o, Object arg) {
		this.repaint();
	}


}
