package logic.MapEntities;

import java.util.Random;

import logic.Coordinates;
import logic.Direction;
import logic.Levels.Map;

/**
 * The Ogre is a relentless foe that will swing its club and kill the Hero on contact.
 */
public class Ogre extends GenericMapEntity {

	/**
	 * Should only be set if the ogre is over a key.
	 */
	private Key oldKey = null;
	public OgreClub club = null;
	boolean stunned = false;
	int stunnedCount = 0;
	private boolean hasClub = true;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param map
	 *            reference to the map the ogre is in.
	 */
	public Ogre(int x, int y, Map map) {
		super(x, y, map);
		if (map.toInt() == 1) {
			hasClub = false;
		}
	}

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
			this.map.getMap()[next.x][next.y] = this.club;
		}
	}

	private void moveOgre() {
		GenericMapEntity futurePos = null; // the desired position's current occupier
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

}
