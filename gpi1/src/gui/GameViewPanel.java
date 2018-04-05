package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import cli.Gameplay;
import logic.MapEntities.GenericMapEntity;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	// Coordinates of the ellipse “bounding rectangle”
	private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

	Gameplay game = null;

	int qs;

	Image IHero, IWall, IGuard, IOgre, IClub, IKey, ILever;

	public void updateGame(Gameplay game) {
		this.game = game;
	}

	// Constructor, adding mouse and keyboard listeners
	public GameViewPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		loadImages();
	}

	private void loadImages() {
		IHero = new ImageIcon("assets/hero.png").getImage();
		IWall = new ImageIcon("assets/wall.png").getImage();
		IOgre = new ImageIcon("assets/ogre.png").getImage();

	}

	// Redraws the panel, only when requested by SWING
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo …
		if (game != null)
			drawGrid(g, 10, 10);
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
	}

	/**
	 * Draws the gameboard on the specified coordinates
	 * 
	 * @param g Graphics
	 * @param x int
	 * @param y int
	 *
	 */
	private void drawGrid(Graphics g, int x, int y) {
		int ss = game.getLevel().getMap().length; // squareSide
		qs = (int) (Integer.min(getSize().width, getSize().height) / (ss + 1));

		makeGrid(g, x, y, qs, ss);
		for (GenericMapEntity[] mapRows : game.getLevel().getMap()) {
			for (GenericMapEntity e : mapRows) {
				int ex = e.getCoordinates().x;
				int ey = e.getCoordinates().y;
				
				drawOnQuadricule(g, ey, ex, e.getClass().getName());
			}
		}
	}

	/**
	 * Draws Hero to g
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawHero(Graphics g, int x, int y) {
		double ratio = IHero.getWidth(null) / IHero.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IHero, x + 12, y + 10, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws Wall to g
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawWall(Graphics g, int x, int y) {
		double ratio = IWall.getWidth(null) / IWall.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IWall, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}
	
	/**
	 * Draws Wall to g
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawOgre(Graphics g, int x, int y) {
		double ratio = IWall.getWidth(null) / IWall.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IOgre, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws the specified entity onto the grid
	 * @param g Graphics
	 * @param x int grid x
	 * @param y int grid y
	 * @param ent String representing the Entity's class name.
	 */
	void drawOnQuadricule(Graphics g, int x, int y, String ent) {
		int coords[] = calcCoordByQuadricule(x, y);
		switch (ent.substring(ent.lastIndexOf(".") + 1)) {
		case "Hero":
			drawHero(g, coords[0], coords[1]);
			break;
		case "Wall":
			drawWall(g, coords[0], coords[1]);
			break;
		case "Ogre":
			drawOgre(g, coords[0], coords[1]);
			break;
		default:
			break;
		}

	}

	private int[] calcCoordByQuadricule(int x, int y) {
		return new int[] { x * qs, y * qs };
	}

	/**
	 * @param g  Graphics
	 * @param x  int
	 * @param y  int
	 * @param qs int
	 * @param ss int
	 */
	private void makeGrid(Graphics g, int x, int y, int qs, int ss) {
		for (int i = 0; i <= ss; i++) {
			g.drawLine(x + i * qs, y, x + i * qs, y + ss * qs);
		}

		for (int i = 0; i <= ss; i++) {
			g.drawLine(x, y + i * qs, x + ss * qs, y + i * qs);
		}
	}

	// Handling keyboard and mouse events
	public void mousePressed(MouseEvent e) {
		x2 = x1 = e.getX();
		y2 = y1 = e.getY();
		repaint(); // notifies SWING that it needs repainting
	}

	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			x1--;
			x2--;
			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			x1++;
			x2++;
			repaint();
			break;
		case KeyEvent.VK_UP:
			y1--;
			y2--;
			repaint();
			break;
		case KeyEvent.VK_DOWN:
			y1++;
			y2++;
			repaint();
			break;
		}
	}

	// Remaining listeners (not used)
	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}