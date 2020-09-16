package roguefairy;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class RogueFairy{
	
	public static final int _WIDTH = 500;
	public static final int _HEIGHT = 500;
	
	
	public GamePanel gamepanel = new GamePanel();
	protected JFrame frame = new JFrame();
	
	RogueFairy(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(_WIDTH, _HEIGHT);
		frame.setVisible(true);
		frame.add(gamepanel);
		frame.addKeyListener(gamepanel);
	}
	

}
