package logic.MapEntities;

import java.util.Random;

import logic.Coordinates;
import logic.Direction;
import logic.Levels.Map;

/**
 * The Ogre is a relentless foe that will swing its club and kill the Hero on contact.
 */
public class Ogre extends GenericMapEntity {

	// reference to the key. It's only set if the ogre is over the key
	private Key oldKey = null;
	
	// reference to this ogre club
	public OgreClub club = null;
	private boolean hasClub = true;
	
	// tells if the ogre is stunned
	boolean stunned = false;
	
	// number of game iterations until ogre becomes normal
	int stunnedCount = 0;

	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public Ogre(int x, int y, Map map) {
		super(x, y, map);
		if (map.toInt() == 1) {
			hasClub = false;
		}
	}
	
	/** 
	 * Tries to move the ogre randomly on the map. It also updates the ogre status (over the key, etc..)
	 */
	private void move() {
		GenericMapEntity futurePos = getRandomFuturePosOccupier();
		
		if (futurePos instanceof Empty) {
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();

			if (oldKey == null)
				this.map.getMap()[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			else {
				this.map.getMap()[curr.x][curr.y] = oldKey;
				oldKey = null;
			}
			this.map.getMap()[next.x][next.y] = this;
			this.setCoordinates(next);
		}

		if (futurePos instanceof Key) {
			oldKey = (Key) futurePos;
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();

			this.map.getMap()[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.getMap()[next.x][next.y] = this;
			this.setCoordinates(next);
		}
	}
	
	/**
	 * Inspects if the ogre is adjacent to the hero. If so, updates the game status (game over)
	 */
	private void checkOgreCaughtHero_adj() {
		for (Direction dir : Direction.values()) {
			if (this.getNeighbor(dir) instanceof Hero) {
				Hero h = (Hero) this.getNeighbor(dir);
				if (h.hasClub) {
					setStunned();
				} else {
					// caught!
					this.map.setGameIsOver(true);
				}
			}
		}
	}
	
	/**
	 * Randomly determines the ogre's club next position (adjacent to the ogre) and updates the map
	 */
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
			this.map.getMap()[next.x][next.y] = this.club;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean tick() {
		stunnedCount--;
		if (stunnedCount == 0) {
			stunned = false;
		}
		if (!stunned)
			move();
		else {

		}
		
		// check if ogre has caught the hero.
		checkOgreCaughtHero_adj();

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
	
	/**
	 * {@inheritDoc}
	 * @return "8" if stunned, "$" if over the key, else "O"
	 */
	@Override
	public String toString() {
		if (stunned) {
			return "8";
		}
		if (oldKey == null)
			return "O";
		else
			return "$";
	}
	
	/**
	 * Tells if the ogre is stunned
	 * @return True if stunned, false otherwise
	 */
	public boolean isStunned() {
		return !(stunnedCount <= 0);
	}
	
	/**
	 * Sets the ogre as stunned
	 */
	public void setStunned() {
		this.stunned = true;
		this.stunnedCount = 3;	
	}
	
	/**
	 * Inform  if the ogre has a club
	 * @return True if ogre has a club
	 */
	public boolean hasClub() {
		return hasClub;
	}
	
	/**
	 * Returns a reference to the key overlapped by the ogre
	 * @return If there's no overlapped key, returns NULL. Otherwise, a reference to that key
	 */
	public Key getOldKey() {
		return oldKey;
	}

}
