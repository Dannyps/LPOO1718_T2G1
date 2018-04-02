package logic.MapEntities;

/**
 * Levers spawn in some levels and are use to immediately open a gateway door.
 *
 */
public class Lever extends GenericMapEntity {

	public Lever(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "K";
	}
}
