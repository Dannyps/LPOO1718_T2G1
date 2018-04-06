package logic.MapEntities;

import logic.Levels.Map;
/**
 * A door that can be opened by interacting with the map, or with itself. Some doors are gateways to other levels.
 *
 */
public class Door extends GenericMapEntity {

	private boolean open;
	
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public Door(int x, int y, Map map) {
		super(x, y, map);
	}
	
	/**
	 * {@inheritDoc}
	 * @return If the door is open, returns "S", else the returned value is "I"
	 */
	@Override
	public String toString() {
		if(this.isOpen())
			return "S";
		else
			return "I";
	}
	
	/**
	 * Tells if the door state is closed or open
	 * @return True if the door is open, closed otherwise
	 */
	public boolean isOpen() {
		return open;
	}
	
	/**
	 * Defines the door state, either closed or open
	 * @param open True to open the door, false otherwise
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}
}
