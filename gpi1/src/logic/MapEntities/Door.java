package logic.MapEntities;

import logic.Levels.Map;
/**
 * A door that can be opened by interacting with the map, or with itself. Some doors are gateways to other levels.
 *
 */
public class Door extends GenericMapEntity {

	private boolean open;

	public Door(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if(this.isOpen())
			return "S";
		else
			return "I";
	}

	public boolean isOpen() {
		return open;
	}

	public boolean setOpen(boolean open) {
		this.open = open;
		return open;
	}
}
