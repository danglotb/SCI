package experiment.pacman;

import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import core.Agent;

public class Dijkstra {

	public static void compute(Agent[][] input, int[][] output, Collection<Point> targets) {
		
		Queue<Point> points = new LinkedList<Point>();
		
		for (int x = 0 ; x < output.length ; x++) {
			for (int y = 0 ; y < output[0].length ; y++) {
				output[x][y] = -1;
			}
		}
		
		
		for (Iterator<Point> it = targets.iterator(); it.hasNext();) {
			Point p = it.next();
			output[p.x][p.y] = 0;
			points.add(p);
		}
		
		while(!points.isEmpty()) {
			Point p = points.poll();
			
			for (int x = p.x - 1; x <= p.x + 1; x++) {
				for (int y = p.y - 1; y <= p.y + 1; y++) {
					if (((x >= 0 && x < output.length) && (y >= 0 && y < output[0].length)) && (input[x][y] == null && output[x][y] == -1)) {
						output[x][y] = output[p.x][p.y]+1;
						points.add(new Point(x, y));
					}
				}
			}
		}
	}

}
