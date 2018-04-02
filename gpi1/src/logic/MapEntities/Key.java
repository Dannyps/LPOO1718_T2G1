package logic.MapEntities;

/**
 * Keys spawn in some levels and are used to unlock the door to the next level.
 */
public class Key extends GenericMapEntity {

	public Key(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "K";
	}

}
