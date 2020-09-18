package roguefairy;

import java.util.Random;

public class Map {
	// Level Map
	Tile levelMap[][];

	private int wallbirthLimit = 4;
	private int wallDeathLimit = 3;
	private int caveChance = 40;
	private Random rand = new Random();

	// Level Map Size
	public static final int _LEVELHEIGHT = 512;
	public static final int _LEVELWIDTH = 512;

	Entity player = new Entity('@', 10, 11);

	Map() {
		levelMap = new Tile[_LEVELHEIGHT][_LEVELWIDTH];

		// initialize map surrounding tiles should all be '#' walls
		// use caveChance is percent chance of spawning cave starting positions
		startCave();
		//Make caves more natural looking
		for (int i = 0; i < 2; i++) {
			excavate();
		}
	}

	private void startCave() {
		for (int i = 0; i < levelMap.length; i++) {
			for (int j = 0; j < levelMap[i].length; j++) {
				if (i == 0 || j == 0 || rand.nextInt(100) < caveChance) {
					levelMap[i][j] = new Tile('#', true, false);
				} else {
					levelMap[i][j] = new Tile('.', false, true);
				}
			}
		}
	}

	private void excavate() {
		Tile newMap[][] = new Tile[_LEVELHEIGHT][_LEVELWIDTH];

		for (int i = 0; i < levelMap.length; i++) {
			for (int j = 0; j < levelMap[i].length; j++) {
				int neighbors = countNeighborWalls(j, i);

				if (levelMap[j][i].glyph == '#') {
					if (neighbors < wallDeathLimit) {
						newMap[j][i] = new Tile('.', false, true);
					} else {
						newMap[j][i] = new Tile('#', true, false);
					}
				} else {
					if (neighbors > wallbirthLimit) {
						newMap[j][i] = new Tile('#', true, false);
					} else {
						newMap[j][i] = new Tile('.', false, true);
					}

				}
			}
		}
		
		levelMap = newMap;
	}

	private int countNeighborWalls(int x, int y) {
		int count = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int neighborX = j + x;
				int neighborY = i + y;

				if (i == 0 && j == 0) {

				} else if (neighborX < 0 || neighborY < 0 || neighborX > _LEVELWIDTH-1 || neighborY > _LEVELHEIGHT-1) {
					count++;
				} else if (levelMap[neighborY][neighborX].glyph == '#') {
					count++;
				}
			}
		}
		return count;
	}

	public boolean canMove(int x, int y) {
		return levelMap[y][x].passable;
	}

	public void playerMove(int deltaX, int deltaY) {
		int x = player.x + deltaX;
		int y = player.y + deltaY;
		if (canMove(x, y)) {
			player.x = x;
			player.y = y;
		}
	}
}
