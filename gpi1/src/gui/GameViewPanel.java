package gui;

import java.awt.*;
import java.awt.event.*;
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

		makeGrid(g, new int[] { x, y }, qs, ss);
		for (GenericMapEntity[] mapRows : game.getLevel().getMap()) {
			for (GenericMapEntity e : mapRows) {
				int ex = e.getCoordinates().x;
				int ey = e.getCoordinates().y;

				drawOnQuadricule(g, new int[] { ey, ex }, e);
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
	private void drawHero(Graphics g, int c[]) {
		double ratio = IHero.getWidth(null) / IHero.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IHero, c[0] + 12, c[1] + 10, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws Club to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawClub(Graphics g, int c[]) {
		double ratio = IClub.getWidth(null) / IClub.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IClub, c[0] + 12, c[1] + 10, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws Wall to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawWall(Graphics g, int c[]) {
		double ratio = IWall.getWidth(null) / IWall.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IWall, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws Ogre to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawOgre(Graphics g, int c[]) {
		double ratio = IWall.getWidth(null) / IWall.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IOgre, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws Stunned Ogre to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawSOgr(Graphics g, int c[]) {
		double ratio = ISOgr.getWidth(null) / ISOgr.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(ISOgr, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a Pickable Club to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawPClb(Graphics g, int c[]) {
		double ratio = IPClb.getWidth(null) / IPClb.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IPClb, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a Pickable Key to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawPKey(Graphics g, int c[]) {
		double ratio = IPKey.getWidth(null) / IPKey.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IPKey, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a Guard to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawGuard(Graphics g, int c[]) {
		double ratio = IGuard.getWidth(null) / IGuard.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IGuard, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws a closed (red) lever to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawCLever(Graphics g, int c[]) {
		double ratio = ICLever.getWidth(null) / ICLever.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(ICLever, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws an open (green) lever to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawOLever(Graphics g, int c[]) {
		double ratio = IOLever.getWidth(null) / IOLever.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IOLever, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws an open door to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawODoor(Graphics g, int c[]) {
		double ratio = IODoor.getWidth(null) / IODoor.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(IODoor, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws an open door to g
	 * 
	 * @param g Graphics
	 * @param x int x coordinate
	 * @param y int y coordinate
	 */
	private void drawCDoor(Graphics g, int c[]) {
		double ratio = ICDoor.getWidth(null) / ICDoor.getHeight(null);
		double lqs = qs * 0.95;

		g.drawImage(ICDoor, c[0] + 14, c[1] + 12, (int) (lqs), (int) (lqs * ratio), null);
	}

	/**
	 * Draws the specified entity onto the grid
	 * 
	 * @param g   Graphics
	 * @param x   int grid x
	 * @param y   int grid y
	 * @param ent String representing the Entity's class name.
	 */
	void drawOnQuadricule(Graphics g, int c[], GenericMapEntity ent) {
		int coords[] = calcCoordByQuadricule(c);
		// ent.substring(ent.lastIndexOf(".") + 1)) {

		switch (ent.getClass().toString().substring(ent.getClass().toString().lastIndexOf(".") + 1)) {
		case "Hero":
			drawHero(g, coords);
			break;
		case "Wall":
			drawWall(g, coords);
			break;
		case "Ogre":
			drawOgre(g, ent, coords);
			break;
		case "OgreClub":
			drawClub(g, coords);
			break;
		case "PickableClub":
			drawPClb(g, coords);
			break;
		case "Key":
			drawPKey(g, coords);
			break;
		case "DrunkenGuard":
			drawGuard(g, coords);
			break;
		case "RookieGuard":
			drawGuard(g, coords);
			break;
		case "SuspiciousGuard":
			drawGuard(g, coords);
			break;
		case "Guard":
			drawGuard(g, coords);
			break;
		case "Lever":
			drawLever(g, ent, coords);
			break;
		case "Door":
			drawDoor(g, ent, coords);
			break;
		}

	}

	/**
	 * @param g
	 * @param ent
	 * @param coords
	 */
	private void drawDoor(Graphics g, GenericMapEntity ent, int[] coords) {
		if (((Door) ent).isOpen()) {
			drawODoor(g, coords);
		} else {
			drawCDoor(g, coords);
		}
	}

	/**
	 * @param g
	 * @param ent
	 * @param coords
	 */
	private void drawLever(Graphics g, GenericMapEntity ent, int[] coords) {
		if (((Lever) ent).isOpen()) {
			drawOLever(g, coords);
		} else {
			drawCLever(g, coords);
		}
	}

	/**
	 * @param g
	 * @param ent
	 * @param coords
	 */
	private void drawOgre(Graphics g, GenericMapEntity ent, int[] coords) {
		if (((Ogre) ent).isStunned()) {
			drawSOgr(g, coords);
		} else {
			drawOgre(g, coords);
		}
	}

	private int[] calcCoordByQuadricule(int c[]) {
		return new int[] { c[0] * qs, c[1] * qs };
	}

	/**
	 * @param g  Graphics
	 * @param x  int
	 * @param y  int
	 * @param qs int
	 * @param ss int
	 */
	private void makeGrid(Graphics g, int c[], int qs, int ss) {
		int x = c[0], y = c[1];
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