package logic.MapEntities;

import logic.Levels.Map;

/**
 * Levers spawn in some levels and are use to immediately open a gateway door.
 */
public class Lever extends GenericMapEntity {

	private boolean open = false;
	
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public Lever(int x, int y, Map map) {
		super(x, y, map);
	}
	
	/**
	 * {@inheritDoc}
	 * @return "K"
	 */
	@Override
	public String toString() {
		return "K";
	}
	
	/**
	 * Sets the lever as open
	 */
	public void setOpen() {
		open  = true;
	}
	
	/**
	 * Check the lever status
	 * @return True if its open, false otherwise
	 */
	public boolean isOpen() {
		return open;
	}
	
	
}
