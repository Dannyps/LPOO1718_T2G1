package cli;

import logic.Levels.*;

public class Gameplay {
	private Map map;
	public String getMapString(){
		return map.toString();
		// TODO if map == null (game finished...) 
	}
	public boolean gameEnd = false;

	/**
	 * Constructor
	 * 
	 * @throws Exception should the map be malformed
	 */
	public Gameplay() throws Exception {
		this.map = new Level1();
		System.out.println(map);
	}

	/**
	 * Refreshes game
	 * 
	 * @return false if game over, true otherwise
	 * @param input char w, a, s or d.
	 */
	public boolean refresh(char input) {
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
