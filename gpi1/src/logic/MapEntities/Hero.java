package logic.MapEntities;

import logic.Coordinates;

/**
 * The Hero, aka The Player, must go through all the levels in order to win the game.
 * On some levels the hero must reach a lever
 * On others it needs to grab the key
 * Hero can also be armed. If so, where it's adjacent to an ogre it becomes stun for a while
 */
public class Hero extends GenericMapEntity {
	
	private boolean hasClub = false;
	private boolean hasKey = false; // needed because changes the representation
	
	/**
	 * @see logic.MapEntities#GenericMapEntity(int x, int y)
	 */
	public Hero(int x, int y) {
		super(x, y);
	}
	
	public Hero(int x, int y, boolean hasClub) {
		super(x, y);
		this.hasClub = hasClub;  
	}
	
	/**
	 * Returns the next hero desired position based on user input
	 * @param c 
	 * @return
	 */
	public Coordinates nextCoordinates(char c) {
		Coordinates next = this.coordinates.clone();		
		next.move(c);
		return next;
	}
	
	/**
	 * Changes the flag that tells if the hero has the key or not
	 * @param hasKey
	 */
	public void setHasKey() {
		this.hasKey = true;
	}
	
	/**
	 * Changes the flag that tells if the hero has a club, i.e, it's armed
	 * @param hasKey
	 */
	public void setHasClub(boolean hasClub) {
		this.hasClub = hasClub;
	}
	
	public boolean hasClub() {
		return hasClub;
	}

	public boolean hasKey() {
		return hasKey;
	}

	@Override
	public String toString() {
		if (hasKey)
			return "K"; 
		else if(hasClub)
			return "A";
		else
			return "H";
	}

	
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 * @return boolean whether the hero was indeed moved or not.
	 */
	/*
	private boolean move(char c) {
		GenericMapEntity futurePos; // the desired position's current occupier
		switch (c) {
		case 'w':
			futurePos = this.getNeighbor(Direction.TOP);
			break;
		case 's':
			futurePos = this.getNeighbor(Direction.BOTTOM);
			break;
		case 'a':
			futurePos = this.getNeighbor(Direction.LEFT);
			break;
		case 'd':
			futurePos = this.getNeighbor(Direction.RIGHT);
			break;
		default:
			futurePos = null;
			return false;
		}

		if (futurePos instanceof Empty) {

			moveTo(futurePos);
			return true;
		} else if (futurePos instanceof Lever) {
			map.heroMetLeverHandler();
			return false;
		} else if (futurePos instanceof Key) {
			map.heroMetKeyHandler();
			moveTo(futurePos);
			return true;
		} else if (futurePos instanceof PickableClub) {
			hasClub=true;
			moveTo(futurePos);
			return true;
		} else if (futurePos instanceof Door) {
			// the player has bumped into a closed door.
			if (map.heroMetDoorHandler((Door) futurePos)) {
				moveTo(futurePos);
			}
			return false;
		} else {
			return false;
		}
	}

	private void moveTo(GenericMapEntity futurePos) {
		Coordinates curr, next;
		curr = this.getCoordinates();
		next = futurePos.getCoordinates();

		this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
		this.map.map[next.x][next.y] = this;
		this.setCoordinates(next);
	}
	*/
}
