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

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

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
		frame.setBounds(100, 100, 225, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * TOP PANEL for number of ogres input
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 393, 0 };
		gridBagLayout.rowHeights = new int[] { 38, 0, 86, 27, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
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
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveRight, BorderLayout.EAST);

		JButton btnMoveLeft = new JButton("Left");
		btnMoveLeft.setEnabled(false);
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('a');
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveLeft, BorderLayout.WEST);

		JButton btnMoveDown = new JButton("Down");
		btnMoveDown.setEnabled(false);
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('s');
				refreshTextArea();
			}
		});
		moveButtonsPanel.add(btnMoveDown, BorderLayout.SOUTH);

		JButton btnMoveUp = new JButton("Up");
		btnMoveUp.setEnabled(false);
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.refresh('w');
				refreshTextArea();
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

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 393, 0 };
		gbl_panel_1.rowHeights = new int[] { 27, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		ta = new JTextArea();
		GridBagConstraints gbc_ta = new GridBagConstraints();
		gbc_ta.insets = new Insets(0, 0, 5, 0);
		gbc_ta.fill = GridBagConstraints.BOTH;
		gbc_ta.gridx = 0;
		gbc_ta.gridy = 0;
		panel_1.add(ta, gbc_ta);
		ta.setFont(new Font("Courier New", Font.PLAIN, 13));
		ta.setEditable(false);
		ta.setLineWrap(true);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(btnNewButton, gbc_btnNewButton);

	}

	public void refreshTextArea() {
		if (game.gameEnd) {
			if (game.gameWon) {
				showAlertMessage("You won!", JOptionPane.CLOSED_OPTION);
			} else {
				ta.setText(game.getMapString()); // update the text area with the current game state so that the user
													// understands why they lost.
				showAlertMessage("You lost!", JOptionPane.ERROR_MESSAGE);
			}
			System.exit(0);
		}
		ta.setText(game.getMapString());
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
