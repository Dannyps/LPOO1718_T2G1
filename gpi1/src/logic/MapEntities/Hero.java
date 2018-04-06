package logic.MapEntities;

import logic.Coordinates;
import logic.Levels.Map;

/**
 * The Hero, aka The Player, must go through all the levels in order to win the
 * game.
 */
public class Hero extends GenericMapEntity {

	public boolean hasClub = false;
	public boolean hasKey = false;

	public Hero(int x, int y, Map map) {
		super(x, y, map);
	}

	@Override
	public String toString() {
		if (hasKey) {
			return "K";
		} else if (hasClub) {
			return "A";
		} else {
			return "H";
		}
	}

	public boolean tick() {
		this.move(map.getBuffer());
		return true;
	}

	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 * @return boolean whether the hero was indeed moved or not.
	 */
	private boolean move(char c) {
		GenericMapEntity futurePos = getFuturePosOccupier(c);

		if (futurePos == null)
			return false;

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
			hasClub = true;
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

		this.map.getMap()[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
		this.map.getMap()[next.x][next.y] = this;
		this.setCoordinates(next);
	}

}
