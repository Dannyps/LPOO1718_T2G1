package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import cli.Gameplay;

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
		if(game!=null)
			drawGrid(g);
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
		//System.out.println(getSize());
	}

	private void drawGrid(Graphics g) {
		
		g.drawString(game.getMapString(), 0, 0);		
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