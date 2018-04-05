package gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import cli.Gameplay;
import logic.MapEntities.Door;
import logic.MapEntities.GenericMapEntity;
import logic.MapEntities.Lever;
import logic.MapEntities.Ogre;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	// Coordinates of the ellipse “bounding rectangle”
	private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

	Gameplay game = null;

	int qs;
	MainWindow parent;
	Image IHero, IWall, IGuard, IOgre, IClub, IKey, ICLever, IOLever, IPClb, ISOgr, IPKey, IODoor, ICDoor;

	public void updateGame(Gameplay game) {
		this.game = game;
	}

	// Constructor, adding mouse and keyboard listeners
	public GameViewPanel(MainWindow mainWindow) {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		parent = mainWindow;
		loadImages();
	}

	private void loadImages() {
		IHero = new ImageIcon("assets/hero.png").getImage();
		IWall = new ImageIcon("assets/wall.png").getImage();
		IOgre = new ImageIcon("assets/ogre.png").getImage();
		IClub = new ImageIcon("assets/club.png").getImage();
		IPClb = new ImageIcon("assets/pickableClub.png").getImage();
		ISOgr = new ImageIcon("assets/stunnedOgre.png").getImage();
		IPKey = new ImageIcon("assets/key.png").getImage();
		IGuard = new ImageIcon("assets/guard.png").getImage();
		ICLever = new ImageIcon("assets/lever_closed.png").getImage();
		IOLever = new ImageIcon("assets/lever_open.png").getImage();
		ICDoor = new ImageIcon("assets/door_closed.png").getImage();
		IODoor = new ImageIcon("assets/door_open.png").getImage();

	}

	// Redraws the panel, only when requested by SWING
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo …
		if (game != null) {
			drawGrid(g, 10, 10);
			requestFocusInWindow();
		}

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

				drawOnQuadricule(g, ey, ex, e);
			}
		}
	}

	/**
	 * Draws Hero to g
	 * 
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
	 * Draws Club to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawClub(Graphics g, int x, int y) {
		double ratio = IClub.getWidth(null) / IClub.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IClub, x + 12, y + 10, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws Wall to g
	 * 
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
	 * Draws Ogre to g
	 * 
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
	 * Draws Stunned Ogre to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawSOgr(Graphics g, int x, int y) {
		double ratio = ISOgr.getWidth(null) / ISOgr.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(ISOgr, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a Pickable Club to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawPClb(Graphics g, int x, int y) {
		double ratio = IPClb.getWidth(null) / IPClb.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IPClb, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a Pickable Key to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawPKey(Graphics g, int x, int y) {
		double ratio = IPKey.getWidth(null) / IPKey.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IPKey, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a Guard to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawGuard(Graphics g, int x, int y) {
		double ratio = IGuard.getWidth(null) / IGuard.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IGuard, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a closed (red) lever to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawCLever(Graphics g, int x, int y) {
		double ratio = ICLever.getWidth(null) / ICLever.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(ICLever, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws an open (green) lever to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawOLever(Graphics g, int x, int y) {
		double ratio = IOLever.getWidth(null) / IOLever.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IOLever, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws an open door to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawODoor(Graphics g, int x, int y) {
		double ratio = IODoor.getWidth(null) / IODoor.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IODoor, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws an open door to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawCDoor(Graphics g, int x, int y) {
		double ratio = ICDoor.getWidth(null) / ICDoor.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(ICDoor, x + 14, y + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws the specified entity onto the grid
	 * 
	 * @param g   Graphics
	 * @param x   int grid x
	 * @param y   int grid y
	 * @param ent String representing the Entity's class name.
	 */
	void drawOnQuadricule(Graphics g, int x, int y, GenericMapEntity ent) {
		int coords[] = calcCoordByQuadricule(x, y);
		// ent.substring(ent.lastIndexOf(".") + 1)) {

		switch (ent.getClass().toString().substring(ent.getClass().toString().lastIndexOf(".") + 1)) {
		case "Hero":
			drawHero(g, coords[0], coords[1]);
			break;
		case "Wall":
			drawWall(g, coords[0], coords[1]);
			break;
		case "Ogre":
			if (((Ogre) ent).isStunned()) {
				drawSOgr(g, coords[0], coords[1]);
			} else {
				drawOgre(g, coords[0], coords[1]);
			}
			break;
		case "OgreClub":
			drawClub(g, coords[0], coords[1]);
			break;
		case "PickableClub":
			drawPClb(g, coords[0], coords[1]);
			break;
		case "Key":
			drawPKey(g, coords[0], coords[1]);
			break;
		case "Guard":
			drawGuard(g, coords[0], coords[1]);
			break;
		case "Lever":
			if (((Lever) ent).isOpen()) {
				drawOLever(g, coords[0], coords[1]);
			} else {
				drawCLever(g, coords[0], coords[1]);
			}
			break;

		case "Door":
			if (((Door) ent).isOpen()) {
				drawODoor(g, coords[0], coords[1]);
			} else {
				drawCDoor(g, coords[0], coords[1]);
			}
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
	public void keyPressed(KeyEvent e) {
		switch (KeyEvent.getKeyText(e.getKeyCode())) {
		case "Right":
		case "D":
			game.refresh('d');
			break;
		case "Left":
		case "A":
			game.refresh('a');
			break;
		case "Up":
		case "W":
			game.refresh('w');
			break;
		case "Down":
		case "S":
			game.refresh('s');
			break;

		}
		parent.refreshDisplay();
	}

	// Remaining listeners (not used)

	public void mousePressed(MouseEvent e) {
		requestFocusInWindow();
	}

	public void mouseDragged(MouseEvent e) {
	}

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