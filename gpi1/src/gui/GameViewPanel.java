package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cli.Gameplay;
import logic.MapEntities.GenericMapEntity;

@SuppressWarnings("serial")
public class GameViewPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	// Coordinates of the ellipse “bounding rectangle”
	private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

	Gameplay game = null;

	public void updateGame(Gameplay game) {
		this.game = game;
	}

	// Constructor, adding mouse and keyboard listeners
	public GameViewPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	// Redraws the panel, only when requested by SWING
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // limpa fundo …
		if (game != null)
			drawGrid(g, 10, 10, 30);
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
		// System.out.println(getSize());
	}

	/**
	 * Draws the gameboard on the specified coordinates
	 * 
	 * @param g  Graphics
	 * @param x  int
	 * @param y  int
	 * @param qs int quadricule size
	 *
	 */
	private void drawGrid(Graphics g, int x, int y, int qs) {
		int ss = game.getLevel().getMap().length; // squareSide
		qs = (int) ((getSize().width)/(ss+1)*1.05);

		makeWalls(g, x, y, qs, ss);

		/*
		 * for(GenericMapEntity[] mapRows : game.getLevel().getMap()) {
		 * for(GenericMapEntity e : mapRows) {
		 * 
		 * } }
		 */
	}

	/**
	 * @param g Graphics 
	 * @param x int
	 * @param y int
	 * @param qs int
	 * @param ss int
	 */
	private void makeWalls(Graphics g, int x, int y, int qs, int ss) {
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