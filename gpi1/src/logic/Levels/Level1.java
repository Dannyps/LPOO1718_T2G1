package logic.Levels;

import logic.MapEntities.*;
import logic.MapEntities.Guard.Guard;

/**
 * Level 1 (1 guard, no ogres)
 * 
 */
public class Level1 extends Map {

	Guard guard = guards.get(0); // there's only one guard in this level.
	
	MapArgs ma;

	
	public Level1(MapArgs ma) throws Exception {
		super("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX");
		this.ma = ma;
		// specify exit doors
		exitDoors.add((Door) this.map[5][0]);
		exitDoors.add((Door) this.map[6][0]);
	}

	@Override
	public boolean heroMetLeverHandler() {
		getExitDoors().forEach(door -> door.open = true);
		return false;
	}

	@Override
	public boolean heroMetKeyHandler() {
		// this should not happen!
		assert (1 == 2);
		return false;
	}

	@Override
	public void input(char input) {
		super.input(input);
		guard.tick();
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if (door.open)
			levelIsOver = true;
		return false;
	}

	@Override
	public Map getNextLevel() throws Exception {
		return new Level2(ma);
	}

}
