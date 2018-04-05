package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import logic.Levels.MapArgs;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.Rectangle;

public class MainWindow {

	private JFrame frame;
	private JTextField ogreNo;
	JPanel gamePanel = new GameViewPanel();
	Gameplay game;
	private JPanel footer_panel;

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
	 * @return int 0 on yes, on no.
	 * 
	 */
	private int askYesOrNo(String message, String title) {
		Object[] options = { "Yes", "No" };
		int n = JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.DEFAULT_OPTION, null, options, options[1]);

		return n;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// MAIN FRAME
		frame = new JFrame();
		frame.setBounds(100, 100, 976, 909);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * TOP PANEL for number of ogres input
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 393, 0 };
		gridBagLayout.rowHeights = new int[] { 38, 0, 86, 27, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
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

		try {
			// UIManager.setLookAndFeel("NIMBUS");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.WEST;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.VERTICAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		frame.getContentPane().add(panel_2, gbc_panel_2);

		JLabel lblTyeOfGuard = new JLabel("Type of Guard");
		panel_2.add(lblTyeOfGuard);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Rookie", "Drunken", "Suspicious" }));
		comboBox.setToolTipText("Select the type of Guard");
		panel_2.add(comboBox);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.anchor = GridBagConstraints.NORTH;
		gbc_splitPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 2;
		frame.getContentPane().add(splitPane, gbc_splitPane);

		JPanel moveButtonsPanel = new JPanel();
		splitPane.setLeftComponent(moveButtonsPanel);
		moveButtonsPanel.setLayout(new BorderLayout(0, 0));

		JButton btnMoveRight = new JButton("Right");
		btnMoveRight.setEnabled(false);
		btnMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('d');
				gamePanel.repaint();
			}
		});
		moveButtonsPanel.add(btnMoveRight, BorderLayout.EAST);

		JButton btnMoveLeft = new JButton("Left");
		btnMoveLeft.setEnabled(false);
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('a');
				gamePanel.repaint();
			}
		});
		moveButtonsPanel.add(btnMoveLeft, BorderLayout.WEST);

		JButton btnMoveDown = new JButton("Down");
		btnMoveDown.setEnabled(false);
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('s');
				gamePanel.repaint();
			}
		});
		moveButtonsPanel.add(btnMoveDown, BorderLayout.SOUTH);

		JButton btnMoveUp = new JButton("Up");
		btnMoveUp.setEnabled(false);
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('w');
				gamePanel.repaint();
			}
		});
		moveButtonsPanel.add(btnMoveUp, BorderLayout.NORTH);

		JButton btnStartGame = new JButton("Start Game");

		splitPane.setRightComponent(btnStartGame);
		// frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new
		// Component[]{label1, panel, ogreNo}));

		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (game != null) {
						if (askYesOrNo("You'll lose your previous game. Are you sure?", "New Game") != 0) { // not yes
							return;
						}
					}

					game = new Gameplay(new MapArgs(parseOgreNumber(), comboBox.getSelectedIndex()));
					((GameViewPanel) gamePanel).updateGame(game);
				} catch (Exception e1) {
					// TODO care bad map (internal error)
					e1.printStackTrace();
				}
				enableButtons(moveButtonsPanel);
				refreshTextArea();

			}

			/**
			 * @return the number on the textBox, or 2 if no value can be read.
			 */
			private int parseOgreNumber() {
				int nOgres;
				try {
					nOgres = Integer.parseInt(ogreNo.getText());
				} catch (NumberFormatException e1) {
					nOgres = 2; // default value
				}
				return nOgres;
			}
		});

		
		GridBagConstraints gbc_footer_panel = new GridBagConstraints();
		gbc_footer_panel.insets = new Insets(0, 0, 5, 0);
		gbc_footer_panel.gridheight = 2;
		gbc_footer_panel.fill = GridBagConstraints.BOTH;
		gbc_footer_panel.gridx = 0;
		gbc_footer_panel.gridy = 3;
		frame.getContentPane().add(gamePanel, gbc_footer_panel);
		
		footer_panel = new JPanel();
		footer_panel.setBounds(new Rectangle(10, 0, 0, 0));
		footer_panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		footer_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GridBagConstraints gbc_footer_panel1 = new GridBagConstraints();
		gbc_footer_panel1.fill = GridBagConstraints.BOTH;
		gbc_footer_panel1.gridx = 0;
		gbc_footer_panel1.gridy = 5;
		frame.getContentPane().add(footer_panel, gbc_footer_panel1);
		footer_panel.setLayout(new BoxLayout(footer_panel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Loaded Successfully");
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		footer_panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		//panel_1.add(btnNewButton, gbc_btnNewButton);

	}

	public void refreshTextArea() {
		if (game.gameEnd) {
			if (game.gameWon) {
				showAlertMessage("You won!", JOptionPane.CLOSED_OPTION);
			} else {
				// TODO update the draw area with the current game state so that the user
													// understands why they lost.
				showAlertMessage("You lost!", JOptionPane.ERROR_MESSAGE);
			}
			System.exit(0);
		}
	}

	/**
	 * Display an alert-like message to the user.
	 */
	private void showAlertMessage(String msg, int messageType) {
		JOptionPane.showMessageDialog(frame, msg, msg, messageType);
	}

	private void enableButtons(JPanel panel_1) {
		for (Component c : panel_1.getComponents()) {
			if (c instanceof JButton) {
				c.setEnabled(true);
			}
		}
	}

}
