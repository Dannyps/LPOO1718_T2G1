package gpi1;

import java.io.IOException;
import Levels.*;

public class Gameplay {
	private Map map;
	private int currentlvl;
	public boolean gameEnd=false;
	
	/**
	 * @brief Constructor
	 * @throws Exception
	 */
	public Gameplay() throws Exception {
		this.map = new Level2();
		this.currentlvl = 1;
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
	 * TODO use this
	 */
	@SuppressWarnings("unused")
	private void loadNextLevel() throws Exception {
		currentlvl=2;
		this.map = new Level2();
	}
	
	
	
}
