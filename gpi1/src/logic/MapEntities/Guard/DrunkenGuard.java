package logic.MapEntities.Guard;

import java.util.Random;

import logic.Coordinates;

public class DrunkenGuard extends Guard {
	private boolean sleeping;

	public DrunkenGuard(int x, int y, String route) {
		super(x, y, route);
		this.sleeping = false;
	}

	public Coordinates nextCoordinates() {
		// previous sleeping state
		boolean wasSleeping = this.sleeping;

		// decide if the guard is going to / keeps sleeping on this iteration
		if (randomGuardSleep()) {
			// guard is sleeping, don't move
			return this.coordinates;
		} else if (wasSleeping) {
			// if the guard was sleeping, and now is awake, maybe revert the route?
			this.randomRevertRoute();
		}
		
		// move the guard
		return this.moveGuard();
	}
	
	/**
	 * Randomly wakes up the guard or set it to sleep
	 * It updates the sleeping flag accordingly
	 * 
	 * @return true if the guard is now sleeping, otherwise returns false
	 */
	protected boolean randomGuardSleep() {
		return this.sleeping = new Random().nextBoolean();
	}
	
	@Override
	public String toString() {
		if (sleeping) {
			return "g";
		} else
			return "G";
	}
	
	/*
	@Override
	public boolean tick() {

		// previous sleeping state
		boolean wasSleeping = this.sleeping;

		// decide if the guard is sleeping on this iteration
		if (randomGuardSleep()) {
			// guard is sleeping, do nothing
			return true;
		} else {
			// if the guard was sleeping, and now is awake, maybe revert the route?
			if (wasSleeping) {
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
	 */
	
}
