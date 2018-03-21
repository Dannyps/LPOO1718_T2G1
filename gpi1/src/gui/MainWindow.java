package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javax.swing.UnsupportedLookAndFeelException;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import cli.Gameplay;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainWindow {

	private JFrame frame;
	private JTextField ogreNo;
	private JTextArea ta;
	
	Gameplay game;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// MAIN FRAME
		frame = new JFrame();
		frame.setBounds(100, 100, 234, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{432, 0};
		gridBagLayout.rowHeights = new int[]{20, 20, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		/**
		 * TOP PANEL for number of ogres input
		 */
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		// Constraints to align panel with main frame
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.FIRST_LINE_START; // align top-left
		gbc_panel.insets = new Insets(5, 5, 5, 0); // padding
		gbc_panel.gridx = 0; // specify row
		gbc_panel.gridy = 0; // specify column
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		frame.getContentPane().add(panel, gbc_panel);
		
		// Label
		JLabel label1 = new JLabel("Number of ogres");
		panel.add(label1);
		
		// Textfield
		ogreNo = new JTextField();
		ogreNo.setHorizontalAlignment(SwingConstants.RIGHT);
		ogreNo.setText("2");
		ogreNo.setColumns(3);
		panel.add(ogreNo);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		frame.getContentPane().add(splitPane, gbc_splitPane);
		
		try {
			//UIManager.setLookAndFeel("NIMBUS");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JPanel moveButtonsPanel = new JPanel();
		splitPane.setLeftComponent(moveButtonsPanel);
		moveButtonsPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnMoveRight = new JButton("Right");
		btnMoveRight.setEnabled(false);
		btnMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.refresh('d');
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveRight, BorderLayout.EAST);
		
		JButton btnMoveLeft = new JButton("Left");
		btnMoveLeft.setEnabled(false);
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.refresh('a');
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveLeft, BorderLayout.WEST);
		
		JButton btnMoveDown = new JButton("Down");
		btnMoveDown.setEnabled(false);
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.refresh('s');
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveDown, BorderLayout.SOUTH);
		
		JButton btnMoveUp = new JButton("Up");
		btnMoveUp.setEnabled(false);
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game.refresh('w');
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveUp, BorderLayout.NORTH);
		
		JButton btnStartGame = new JButton("Start Game");
		
		splitPane.setRightComponent(btnStartGame);
		
		ta = new JTextArea();
		ta.setFont(new Font("Courier New", Font.PLAIN, 13));
		ta.setEditable(false);
		ta.setLineWrap(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		frame.getContentPane().add(ta, gbc_textArea);
		//frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{label1, panel, ogreNo}));
		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game = new Gameplay();
				} catch (Exception e1) {
					// TODO care bad map (internal error)
					e1.printStackTrace();
				}
				enableButtons(moveButtonsPanel);
				refreshTextArea();
				
				((JButton) e.getSource()).setEnabled(false); // disable start game button
			}
		});
		
	}
	public void refreshTextArea() {
		ta.setText(game.getMapString());
	}

	private void enableButtons(JPanel panel_1) {
		for(Component c : panel_1.getComponents()) {
			if( c instanceof JButton) {
				c.setEnabled(true);
			}
		}
	}
	
}
