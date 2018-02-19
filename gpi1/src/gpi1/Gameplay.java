package gpi1;

import java.io.IOException;

public class Gameplay {
	// TODO add the second level to array
	private String[] lvls = {"XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX"};
	private Map map;
	private int currentlvl;
	
	/**
	 * @brief Constructor
	 * @throws Exception
	 */
	public Gameplay() throws Exception {
		this.map = new Map(lvls[0]);
		this.currentlvl = 0;
	}
	
	/**
	 * @brief Refreshes game
	 * @return false if game over, true otherwise
	 * @throws IOException 
	 */
	public boolean refresh() throws IOException {
		char input = (char) System.in.read();
		map.input(input);
		System.out.println(map);
		
		// TODO check if hero was catched, reached level, reached door, and so on
		return true;
	}
	
	
	/**
	 * This is suppose to load the next level, meh
	 * @throws Exception
	 */
	private void loadNextLevel() throws Exception {
		this.map = new Map(lvls[++currentlvl]);
	}
	
	
	
}
