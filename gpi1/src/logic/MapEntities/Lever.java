package logic.MapEntities;

import logic.Levels.Map;

/**
 * Levers spawn in some levels and are use to immediately open a gateway door.
 *
 */
public class Lever extends GenericMapEntity {

	private boolean open = false;

	public Lever(int x, int y, Map map) {
		super(x, y, map);
	}

	@Override
	public String toString() {
		return "K";
	}
	
	public void setOpen() {
		open  = true;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	
}
