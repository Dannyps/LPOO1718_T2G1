package logic.MapEntities;

import java.util.Random;

import logic.Coordinates;

/**
 * The Ogre's club is a dangerous tool it uses to kill the hero.
 */
public class OgreClub extends GenericMapEntity {
	private boolean overKey;
	
	public OgreClub(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Moves the ogre club randomly to an adjacent ogre position
	 * @return The new random coordinates
	 */
	private Coordinates moveOgreClub(Coordinates o) {
		Coordinates c = o.clone();
		int i = new Random().nextInt(4);

		switch (i) {
		case 0:
			c.moveUp();
			break;
		case 1:
			c.moveDown();
			break;
		case 2:
			c.moveLeft();
			break;
		case 3:
			c.moveRight();
			break;
		}
		return c;
	}
	
	/**
	 * 
	 * @param o Ogre coordinates
	 * @return
	 */
	public Coordinates nextCoordinates(Coordinates o) {
		return moveOgreClub(o);
	}
	
	@Override
	public String toString() {
		if(overKey)
			return "$";
		else 
			return "*";
	}

	public boolean isOverKey() {
		return overKey;
	}

	public void setOverKey(boolean overKey) {
		this.overKey = overKey;
	}

}
