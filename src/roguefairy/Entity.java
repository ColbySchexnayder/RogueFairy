package roguefairy;

import java.util.ArrayList;

public class Entity {
	
	public String name;
	public char glyph;
	public int x;
	public int y;
	public int vision;
	public int hp = 10;
	public int physical = 5;
	public int mind = 5;
	public ArrayList<Item> itemsHeld = new ArrayList<Item>();
	public ArrayList<Item> itemsEquipped = new ArrayList<Item>();
	
	public Entity(String name, char glyph, int x, int y, int vision) {
		
		this.name = name;
		this.glyph = glyph;
		this.x = x;
		this.y = y;
		this.vision = vision;
	}
	
	
}
