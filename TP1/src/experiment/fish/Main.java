package experiment.fish;

import gui.Graph;
import gui.Render;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import core.Engine;
import core.Environment;

public class Main {
	
	private static String usage = "Usage : <board_width> <board_height> <case_size> <sleep_delay>";
	public static void main(String[] args) {
		
		if(args.length < 4) {
			System.out.println(usage);
			return;
		}
		
		int nbTuna = 200;
		int nbShark = 150;
		int boardWidth = 100;
		int boardHeight = 100;
		int caseSize = 10;
		int sleepTime = 50;
		int tunaReproductionTime = 2;
		int sharkReproductionTime = 10;
		int sharkStarvingTime = 7;
		
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
		
		System.out.println("Tuna number : "+nbTuna);
		System.out.println("Shark number : "+nbShark);
		System.out.println("Tuna reproduction : "+tunaReproductionTime);
		System.out.println("Tuna reproduction : "+sharkReproductionTime);
		System.out.println("Shark starving : "+sharkStarvingTime);
		
		Engine engine = new Engine(sleepTime);
		
		Random random = new Random();
		Environment environment = new Environment(boardWidth, boardHeight);
		
		List<Point> emptyCell = environment.getEmptyCell();
		
		for(int i=0; i<nbTuna; i++) {
			Point currentCoord = emptyCell.remove(random.nextInt(emptyCell.size()));
			engine.addAgent(new Tuna(environment, currentCoord.x, currentCoord.y, tunaReproductionTime));
		}
		
		for(int i=0; i<nbShark; i++) {
			Point currentCoord = emptyCell.remove(random.nextInt(emptyCell.size()));
			engine.addAgent(new Shark(environment, currentCoord.x, currentCoord.y, sharkStarvingTime, sharkReproductionTime));
		}
		
		Render render = new Render(engine, environment, caseSize);
		engine.addObserver(render);
		
		JFrame frame = new JFrame();
		
		JPanel rootPane = new JPanel();
		rootPane.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		rootPane.add(render, gbc);
		
		gbc.gridx = 1;
		gbc.gridheight = 1;
		Graph graphPopulation = new PopulationGraph(600, 400);
		engine.addObserver(graphPopulation);
		rootPane.add(graphPopulation, gbc);
		
		gbc.gridy = 1;
		Graph graphRatio = new PopulationRatioGraph(600, 400);
		engine.addObserver(graphRatio);
		rootPane.add(graphRatio, gbc);
		
		
		frame.setContentPane(rootPane);
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		engine.run(0);
	}
}