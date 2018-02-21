package gpi1;

import java.io.IOException;

public class Gameplay {
	// TODO add the second level to array
	private String[] lvls = {"XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX",
			"XXXXXXXXXXI   O  KXX       XX       XX       XX       XX       XXH      XXXXXXXXXX"
			};
	private Map map;
	private int currentlvl;
	public boolean gameEnd=false;
	
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
		System.out.println(map);
		char input = (char) System.in.read();
		if(input == 'a' || input == 'd' || input == 'w' || input == 's') {
			map.input(input);
			System.out.println(map);
			gameEnd=map.gameIsOver;				
		}
		if(input == 'i') {
			System.exit(0);
		}
		
		// TODO check if hero was caught, reached level, reached door, and so on
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
