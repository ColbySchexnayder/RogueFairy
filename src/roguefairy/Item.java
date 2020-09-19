package roguefairy;

public class Item extends Entity{

	public int healthBonus;
	public int dmgBonus;
	
	
	public Item(String name, char glyph, int x, int y, int vision) {
		super(name, glyph, x, y, vision);
		// TODO Auto-generated constructor stub
	}


	public Item(String name, char glyph, int x, int y, int vision, int healthBonus, int dmgBonus) {
		super(name, glyph, x, y, vision);
		this.healthBonus = healthBonus;
		this.dmgBonus = dmgBonus;
	}

	
}
