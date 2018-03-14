package cli;

import java.io.IOException;

import logic.Levels.*;

public class Gameplay {
	private Map map;
	public boolean gameEnd = false;

	/**
	 * Constructor
	 * 
	 * @throws Exception
	 */
	public Gameplay() throws Exception {
		this.map = new Level2();
		System.out.println(map);
	}

	/**
	 * Refreshes game
	 * 
	 * @return false if game over, true otherwise
	 * @throws IOException
	 */
	public boolean refresh(char input) throws IOException {
		if (input == 'a' || input == 'd' || input == 'w' || input == 's') {
			// Pass the char to the game
			map.input(input);

			// update internal variable from map
			gameEnd = map.isGameOver();

			// Print the map
			System.out.println(map);
			
			if(map.isLevelOver()) {
				try {
					loadNextLevel();
				} catch (Exception e) {
					// TODO understand this exception
					e.printStackTrace();
					
				}
			}
		}
		if (input == 'i') { // interrupt
			System.exit(0);
		}
		return true;
	}

	/**
	 * This is supposed to load the next level, meh
	 * 
	 * @throws Exception
	 *             TODO use this
	 */
	private void loadNextLevel() throws Exception {
		this.map = this.map.getNextLevel();
		if(this.map==null) {
			System.out.println("Game finished!");
		}
	}

}
