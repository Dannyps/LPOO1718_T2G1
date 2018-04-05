package logic.MapEntities;

import java.util.Random;

import logic.Coordinates;

/**
 * The Ogre is a relentless foe that will swing its club and kill the Hero on contact.
 * On some levels, the hero is armed and the ogre can become stunned
 */
public class Ogre extends GenericMapEntity {

	private boolean stunned = false;
	private boolean overKey;
	final int STUNNED_TURNS = 2;
	private int stunnedTurnsCount = 0;
	
	
	//private Key oldKey = null; //?
	//public OgreClub club = null; //?
	
	

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Ogre(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Moves the ogre randomly, step by step
	 * @return The new random coordinates
	 */
	private Coordinates moveOgre() {
		Coordinates current = this.coordinates.clone();
		int i = new Random().nextInt(4);

		switch (i) {
		case 0:
			current.moveUp();
			break;
		case 1:
			current.moveDown();
			break;
		case 2:
			current.moveLeft();
			break;
		case 3:
			current.moveRight();
			break;
		}
		return current;
	}
	
	public Coordinates nextCoordinates() {
		if(stunned) {
			if(stunnedTurnsCount == 0) {
				// ogre is no longer stunned
				this.stunned = false;
			}
			else {
				stunnedTurnsCount--;
				return this.coordinates;
			}
		}
		
		// move the ogre
		return this.moveOgre();
	}
	
	/**
	 * Changes the ogre stun state
	 * @param stunned
	 */
	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}
	
	/**
	 * Sets if the ogre is over a key on the map
	 * @param overKey
	 */
	public void setOverKey(boolean overKey) {
		this.overKey = overKey;
	}

	@Override
	public String toString() {
		if (stunned) 
			return "8";
		else if (overKey)
			return "$";
		else
			return "O";
	}
	
	/*
	public boolean tick() {
		stunnedCount--;
		if (stunnedCount == 0) {
			stunned = false;
		}
		if (!stunned)
			moveOgre();
		else {

		}
		
		// check if ogre has caught the hero.
		for (Direction dir : Direction.values()) {
			if (this.getNeighbor(dir) instanceof Hero) {
				Hero h = (Hero) this.getNeighbor(dir);
				if (h.hasClub) {
					this.stunned = true;
					this.stunnedCount = 3;
				} else {
					// caught!
					this.map.setGameIsOver(true);
				}
			}
		}

		if (hasClub) {
			generateClub();
			// when club lands near the user, the game is over.
			if (this.club == null)
				return true;
			for (Direction dir : Direction.values()) {
				if (this.club.getNeighbor(dir) instanceof Hero) {
					// caught!
					this.map.setGameIsOver(true);
				}
			}
		}

		return true;
	}

	private void generateClub() {
		GenericMapEntity futurePos = null; // the club's next position
		int i = new Random().nextInt(4);
		switch (i) {
		case 0:
			futurePos = this.getNeighbor(Direction.TOP);
			break;
		case 1:
			futurePos = this.getNeighbor(Direction.BOTTOM);
			break;
		case 2:
			futurePos = this.getNeighbor(Direction.LEFT);
			break;
		case 3:
			futurePos = this.getNeighbor(Direction.RIGHT);
			break;
		}
		if (futurePos instanceof Empty) {
			Coordinates next;
			next = futurePos.getCoordinates();
			this.club = new OgreClub(next.x, next.y, map);
			this.map.map[next.x][next.y] = this.club;
		}
	}
	*/



}
