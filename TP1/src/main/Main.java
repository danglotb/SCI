package main;

import java.util.Random;

import javax.swing.JFrame;

import agent.Ball;
import core.Engine;
import environment.Environment;
import gui.Render;

public class Main {
	
	private static String usage = "Usage : <board_width> <board_height> <case_size> <ball_number>";
	public static void main(String[] args) {
		
		if(args.length < 4) {
			System.out.println(usage);
			return;
		}
		
		int boardWidth = 0;
		int boardHeight = 0;
		int caseSize = 0;
		int ballNumber = 0;
		int maxVelocity = 2;
		int sleepTime = 15;
		
		try {
			boardWidth = Integer.parseInt(args[0]);
			boardHeight = Integer.parseInt(args[1]);
			caseSize = Integer.parseInt(args[2]);
			ballNumber = Integer.parseInt(args[3]);
		}
		catch(NumberFormatException e) {
			System.out.println(usage);
			return;
		}
		try {sleepTime = Integer.parseInt(args[4]);}
		catch (IndexOutOfBoundsException e){}
		
		Engine engine = new Engine(sleepTime);
		
		//object random to init velocity of balls
		Random random = new Random();
		Environment environment = new Environment(boardWidth, boardHeight);
		
		for(int i=0; i<ballNumber; i++)
			engine.addAgent(new Ball(environment, random.nextInt(boardWidth), random.nextInt(boardHeight), random.nextInt(maxVelocity*2+1)-maxVelocity, random.nextInt(maxVelocity*2+1)-maxVelocity));
		
		Render render = new Render(engine, environment, caseSize);
		engine.addObserver(render);
		
		JFrame frame = new JFrame();
		frame.setContentPane(render);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		engine.run(0);
	}

}
