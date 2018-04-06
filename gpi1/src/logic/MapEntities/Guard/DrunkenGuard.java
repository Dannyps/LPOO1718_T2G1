package logic.MapEntities.Guard;

import java.util.Random;

import logic.Direction;
import logic.Levels.Map;
import logic.MapEntities.GenericMapEntity;
import logic.MapEntities.Hero;

public class DrunkenGuard extends Guard {
	// flag to tell if the guard is sleeping
	private boolean sleeping;
	
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public DrunkenGuard(int x, int y, Map map) {
		super(x, y, map);
		this.sleeping = false;
	}
	

	/**
	 * Randomly wakes up the guard or set it to sleep
	 * @return true if the guard is now sleeping, false otherwise
	 */
	protected boolean randomGuardSleep() {
		return this.sleeping = new Random().nextBoolean();
	}
	
	/**
	 * Randomly reverts the guard route
	 */
	protected void randomRevertRoute() {
		if (new Random().nextBoolean()) {
			// reverse route
			for (int i = 0; i < route.length / 2; i++) {
				char temp = route[i];
				route[i] = route[route.length - i - 1];
				route[route.length - i - 1] = temp;
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean tick() {
		/**
		 * *** If now is sleeping ...
		 * ****** Do nothing
		 * *** If now is awake ...
		 * ***** and was sleeping
		 * ******** maybe revert the route
		 * ***** move the guard
		 */

		// previous sleeping state
		boolean wasSleeping = this.sleeping;
		
		// decide if the guard is sleeping on this iteration
		if(randomGuardSleep()) {
			// guard is sleeping, do nothing
			return true;
		} else {
			// if the guard was sleeping, and now is awake, maybe revert the route?
			if(wasSleeping) {
				randomRevertRoute();
			}
		}
		
		// move the guard
		this.moveGuard(route[index]);
		if (index == route.length - 1)
			index = 0;
		else
			index++;

		// check if guard has caught the hero.
		for (Direction dir : Direction.values()) {
			if (this.getNeighbor(dir) instanceof Hero) {
				// caught!
				this.map.setGameIsOver(true);
			}
		}

		return true;
	}
	
	/**
	 * {@inheritDoc}
	 * @return "g" if the guard is sleeping, else "G"
	 */
	@Override
	public String toString() {
		if (sleeping) {
			return "g";
		} else
			return "G";
	}

	protected void randomRevertRoute() {
		if (new Random().nextBoolean()) {
			// reverse route
			for (int i = 0; i < route.length / 2; i++) {
				char temp = route[i];
				route[i] = route[route.length - i - 1];
				route[route.length - i - 1] = temp;
			}
		}
	}

	public boolean isSleeping() {
		return sleeping;
	}

}
