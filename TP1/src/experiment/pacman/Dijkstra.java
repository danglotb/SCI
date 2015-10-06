package experiment.pacman;

import core.Agent;

public class Dijkstra {

	public static int[][] compute(Agent [][] grid, int xTarget, int yTarget) {
		int[][] ret = new int[grid.length][grid[0].length];
		for (int x = 0 ; x < grid.length ; x++) {
			for (int y = 0 ; y < grid[0].length ; y++) {
				ret[x][y] = -1;
			}
		} 
		ret[xTarget][yTarget] = 0;
		recurCompute(grid, xTarget, yTarget, ret, 0);
		return ret;
	}

	private static void recurCompute(Agent[][] grid, int xTarget, int yTarget, int[][] ret, int score) {
		for (int x = xTarget - 1; x < xTarget + 1; x++) {
			for (int y = yTarget - 1; y < yTarget + 1; y++) {
				if ( ((x >= 0 && x < grid.length) && (y >= 0 && y < grid[0].length)) && (grid[x][y] == null && ret[x][y] == -1)) {
					ret[x][y] = score;
					recurCompute(grid, x, y, ret, score + 1);
				}
			}
		}

	}

}
