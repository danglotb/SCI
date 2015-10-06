package experiment.pacman;

import gui.Graph;
import gui.Render;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Agent;
import core.Engine;
import core.Environment;

public class Main {
	
	private static String usage = "Usage : <board_width> <board_height> <case_size> <sleep_delay>";
	public static void main(String[] args) {
		
		if(args.length < 4) {
			System.out.println(usage);
			return;
		}
		
		int boardWidth = 100;
		int boardHeight = 100;
		int caseSize = 10;
		int sleepTime = 50;
		
		int nbWall = 100;
		
		int nbHunter = 1;
		int nbAttractor = 1;
		
		try {
			boardWidth = Integer.parseInt(args[0]);
			boardHeight = Integer.parseInt(args[1]);
			caseSize = Integer.parseInt(args[2]);
			sleepTime = Integer.parseInt(args[3]);
		}
		catch(NumberFormatException e) {
			System.out.println(usage);
			return;
		}
		
		Engine engine = new Engine(sleepTime);
		
		Random random = new Random();
		Environment environment = new Environment(boardWidth, boardHeight);
		
		List<Point> emptyCell = environment.getEmptyCell();
		
		for(int i=0; i<nbWall; i++) {
			Point currentCoord = emptyCell.remove(random.nextInt(emptyCell.size()));
			engine.addAgent(new Wall(environment, currentCoord.x, currentCoord.y));
		}
		
		Point currentCoord = emptyCell.remove(random.nextInt(emptyCell.size()));
		Agent t = new Attractor(environment, currentCoord.x, currentCoord.y);
		engine.addAgent(t);
		
		currentCoord = emptyCell.remove(random.nextInt(emptyCell.size()));
		engine.addAgent(new Hunter(environment, currentCoord.x, currentCoord.y, t));
		
		Render render = new Render(engine, environment, caseSize);
		engine.addObserver(render);
		
		JFrame frame = new JFrame();
		
		JPanel rootPane = new JPanel();
		
		rootPane.add(render);
		
		frame.setContentPane(rootPane);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		engine.run(0);
	}
}