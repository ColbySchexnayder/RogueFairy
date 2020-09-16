package roguefairy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{

	//Updating Window Size
	Rectangle windowSize;
	//Frame rate
	Timer frameDraw;
	
	//Fonts
	Font mapFont;
	
	GamePanel(){
		mapFont = new Font("Courier New", Font.PLAIN, 15);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
	}
	
	public void update() {
		windowSize = this.getBounds();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, windowSize.width, windowSize.height);
		g.setColor(Color.white);
		int mapPanelWidth = windowSize.width*2/3;
		int mapPanelHeight = windowSize.height*2/3;
		//map panel
		g.drawRect(5, 5, mapPanelWidth, mapPanelHeight);
		
		//status panel
		g.drawRect(5, mapPanelHeight+10, windowSize.width-10, windowSize.height-15);
		
		//dialog panel
		g.drawRect(mapPanelWidth+10, 5, windowSize.width-10, mapPanelHeight);
		
		g.setFont(mapFont);
		//g.drawString("TEST", windowSize.width/2, windowSize.height/2);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		update();
		repaint();
	}

}
