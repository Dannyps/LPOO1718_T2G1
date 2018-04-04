package cli;

import logic.Levels.*;

public class Gameplay {
	private Map level;

	public String getLevelString() {
		if (level == null)
			return null;
		else
			return level.toString();
	}
	
	public Map getLevel() {
		return level;
	}

	public boolean gameEnd = false;
	
	public boolean gameWon = false;


	/**
	 * Constructor
	 * 
	 * @throws Exception should the map be malformed
	 */
	public Gameplay(MapArgs ma) throws Exception {
		this.level = new Level3(ma);
		System.out.println(level);
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
			level.input(input);

			// update internal variable from map
			gameEnd = level.isGameOver();

			// Print the map
			System.out.println(level);

			if (level.isLevelOver()) {
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
	 */
	private void loadNextLevel() throws Exception {
		this.level = this.level.getNextLevel();
		if (this.level == null) {
			System.out.println("Game finished!");
			gameEnd = true;
			gameWon = true;
		}
	}

}
