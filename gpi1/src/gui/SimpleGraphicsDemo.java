package gui;

import java.awt.*;
import javax.swing.*;

public class SimpleGraphicsDemo {

	
	public static void main(String[] args) {
		JFrame f = new JFrame("a fancy title"); // TODO title
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(500, 500));
		JPanel panel = new SimpleGraphicsPanel();
		f.getContentPane().add(panel);
		f.pack();
		f.setVisible(true);
		panel.requestFocusInWindow(); // to handle keyboard events
	}
	
}