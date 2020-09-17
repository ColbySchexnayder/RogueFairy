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

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	// Updating Window Size
	Rectangle windowSize;
	// Frame rate
	Timer frameDraw;

	// Fonts
	Font mapFont;

	// Level Map
	char levelMap[][];
	// Level Map Size
	public static final int _LEVELHEIGHT = 512;
	public static final int _LEVELWIDTH = 512;

	// Player
	int playerX = 100;
	int playerY = 112;
	char playerChar = '@';

	GamePanel() {
		mapFont = new Font("Courier New", Font.PLAIN, 15);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();

		levelMap = new char[_LEVELWIDTH][_LEVELHEIGHT];

		// initialize map surrounding tiles should all be '#' walls
		for (int i = 0; i < levelMap.length; i++) {
			for (int j = 0; j < levelMap[i].length; j++) {
				if (i == 0 || j == 0 || i == j) {
					levelMap[i][j] = '#';
				} else {
					levelMap[i][j] = '.';
				}
			}
		}

	}

	public void update() {
		windowSize = this.getBounds();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, windowSize.width, windowSize.height);
		g.setColor(Color.white);
		int mapPanelWidth = windowSize.width * 2 / 3;
		int mapPanelHeight = windowSize.height * 2 / 3;
		int statusPanelWidth = windowSize.width / 3 - 15;
		int dialogPanelHeight = (windowSize.height / 3) - 15;

		// map panel
		g.drawRect(5, 5, mapPanelWidth, mapPanelHeight);

		// dialog panel
		g.drawRect(5, mapPanelHeight + 10, windowSize.width - 10, dialogPanelHeight);

		// status panel
		g.drawRect(mapPanelWidth + 10, 5, statusPanelWidth, mapPanelHeight);

		g.setFont(mapFont);
		// Start positions clamped between 0 and padding from the max value

		int startX = Math.max(0, Math.min(playerX - mapPanelWidth / 30, _LEVELWIDTH - mapPanelWidth / 30));
		int startY = Math.max(0, Math.min(playerY - mapPanelHeight / 30, _LEVELHEIGHT - mapPanelHeight / 30));

		// draw level in map panel
		for (int i = startY; i < levelMap.length; i++) {
			for (int j = startX; j < levelMap[i].length; j++) {

				int xScale = (j - startX) * 15 + 10;
				int yScale = (i - startY) * 15 + 20;

				if (xScale > 6 && xScale < mapPanelWidth && yScale > 6 && yScale < mapPanelHeight) {

					if (i == playerY && j == playerX) {
						g.drawString("" + playerChar, xScale, yScale);
					} else {
						g.drawString("" + levelMap[i][j], xScale, yScale);
					}
				}
			}
		}
		// g.drawString("TEST", windowSize.width/2, windowSize.height/2);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		int key = arg0.getKeyCode();

		switch (key) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_NUMPAD8:
			playerY--;
			break;
			
		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_NUMPAD2:
			playerY++;
			break;
			
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_NUMPAD4:
			playerX--;
			break;
			
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_NUMPAD6:
			playerX++;
			break;
			
		case KeyEvent.VK_NUMPAD7:
			playerX--;
			playerY--;
			break;
			
		case KeyEvent.VK_NUMPAD9:
			playerX++;
			playerY--;
			break;
		
		case KeyEvent.VK_NUMPAD1:
			playerX--;
			playerY++;
			break;
			
		case KeyEvent.VK_NUMPAD3:
			playerX++;
			playerY++;
			break;
		default:

		}
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
