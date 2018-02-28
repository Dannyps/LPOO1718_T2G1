package cli;

import java.io.IOException;

import logic.Levels.*;

public class Gameplay {
	private Map map;
	private int currentlvl;
	public boolean gameEnd=false;
	
	/**
	 * Constructor
	 * @throws Exception
	 */
	public Gameplay() throws Exception {
		this.map = new Level2();
		this.currentlvl = 1;
	}
	
	/**
	 * Refreshes game
	 * @return false if game over, true otherwise
	 * @throws IOException 
	 */
	public boolean refresh() throws IOException {
		System.out.println(map);
		
		char input = (char) System.in.read();
		if(input == 'a' || input == 'd' || input == 'w' || input == 's') {
			// Pass the char to the game
			map.input(input);
			
			// Print the map
			//System.out.println(map);
			
			// update internal variable from map
			gameEnd=map.gameIsOver;	
		}
		if(input == 'i') { // interrupt
			System.exit(0);
		}
		System.in.read();
		return true;
	}
	
	
	/**
	 * This is supposed to load the next level, meh
	 * @throws Exception
	 * TODO use this
	 */
	@SuppressWarnings("unused")
	private void loadNextLevel() throws Exception {
		currentlvl=2;
		this.map = new Level2();
	}
	
	
	
}
