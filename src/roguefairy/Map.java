package roguefairy;

public class Map {
	// Level Map
	Tile levelMap[][];
	// Level Map Size
	public static final int _LEVELHEIGHT = 512;
	public static final int _LEVELWIDTH = 512;

	Map() {
		levelMap = new Tile[_LEVELWIDTH][_LEVELHEIGHT];

		// initialize map surrounding tiles should all be '#' walls
		for (int i = 0; i < levelMap.length; i++) {
			for (int j = 0; j < levelMap[i].length; j++) {
				if (i == 0 || j == 0 || i == j) {
					levelMap[i][j] = new Tile('#', true, false);
				} else {
					levelMap[i][j] = new Tile('.', false, true);
				}
			}
		}
	}
	
	public boolean canMove(int x, int y) {
		return levelMap[y][x].passable;
	}
}