package roguefairy;

public class Tile {
	
	public char glyph;
	public boolean blockLOS;
	public boolean hasSeen= false;
	public boolean inLOS = false;
	public boolean passable;
		
	public Tile(char glyph, boolean blockLOS, boolean passable) {

		this.glyph = glyph;
		this.blockLOS = blockLOS;
		this.passable = passable;
		
	}
	
}
