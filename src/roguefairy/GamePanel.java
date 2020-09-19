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
	Font mapFont = new Font("Courier New", Font.PLAIN, 15);

	Map map = new Map();

	GamePanel() {

		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();

	}

	public void update() {
		windowSize = this.getBounds();
		map.update();
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
		int windowScale = 30;
		int startX = Math.max(0,
				Math.min(map.player.x - mapPanelWidth / windowScale, Map._LEVELWIDTH - mapPanelWidth / windowScale));
		int startY = Math.max(0,
				Math.min(map.player.y - mapPanelHeight / windowScale, Map._LEVELHEIGHT - mapPanelHeight / windowScale));

		// check player los
		map.updateLOS(map.player);
		// draw level in map panel
		for (int i = startY; i < map.levelMap.length; i++) {
			for (int j = startX; j < map.levelMap[i].length; j++) {

				int xScale = (j - startX) * 15 + 10;
				int yScale = (i - startY) * 15 + 20;

				if (xScale > 6 && xScale < mapPanelWidth && yScale > 6 && yScale < mapPanelHeight) {

					if (i == map.player.y && j == map.player.x) {
						g.drawString("" + map.player.glyph, xScale, yScale);
					} else {

						Tile tile = map.levelMap[i][j];

						if (tile.inLOS) {
							g.setColor(Color.white);
							g.drawString("" + tile.glyph, xScale, yScale);
							
							for (Entity e : map.entities) {
								if (e.x == j && e.y == i) {
									g.drawString("" + e.glyph, xScale, yScale);

								}
							}
						} else if (tile.hasSeen) {
							g.setColor(Color.darkGray);
							g.drawString("" + tile.glyph, xScale, yScale);
						}

					}
				}
			}
		}

		// draw player status in the status panel
		g.setColor(Color.white);
		g.drawString("Health: " + map.player.hp, mapPanelWidth + 15, 25);
		g.drawString("Attack: " + map.player.physical, mapPanelWidth + 15, 40);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		int key = arg0.getKeyCode();

		switch (key) {
		case KeyEvent.VK_W:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_NUMPAD8:
			map.playerMove(0, -1);
			break;

		case KeyEvent.VK_S:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_NUMPAD2:
			map.playerMove(0, 1);
			break;

		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_NUMPAD4:
			map.playerMove(-1, 0);
			break;

		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_NUMPAD6:
			map.playerMove(1, 0);
			break;

		case KeyEvent.VK_NUMPAD7:
			map.playerMove(-1, -1);
			break;

		case KeyEvent.VK_NUMPAD9:
			map.playerMove(1, -1);
			break;

		case KeyEvent.VK_NUMPAD1:
			map.playerMove(-1, 1);
			break;

		case KeyEvent.VK_NUMPAD3:
			map.playerMove(1, 1);
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
