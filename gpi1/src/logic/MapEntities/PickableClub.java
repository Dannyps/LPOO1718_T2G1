package logic.MapEntities;

/**
 * Represents a club that the hero can pick, which will be used to stun nearby ogres.
 *
 */
public class PickableClub extends GenericMapEntity {

	public PickableClub(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "A";
	}

}
