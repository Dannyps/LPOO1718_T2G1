package logic.Levels;

import logic.Coordinates;
import logic.MapEntities.*;

/**
 * Level 3 (several ogres, no guard)
 * 
 */
public class Level3 {//extends Map {
	/*
	public Level3() throws Exception {
		super("XXXXXXXXXI      KXX       XX       XX       XX       XX       XXH  A   XXXXXXXXXX");
		exitDoors.add((Door) map[1][0]);
	}

	@Override
	public boolean heroMetLeverHandler() {
		// this should not happen!
		assert (1 == 2);
		return false;
	}

	@Override
	public boolean heroMetKeyHandler() {
		hero.hasKey = true;
		Coordinates cKC = key.getCoordinates(); // current Key Coordinates
		map[cKC.x][cKC.y] = new Empty(cKC.x, cKC.y, this); // make the old Key position become empty
		return true;
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if (!door.open && hero.hasKey) {
			door.open = true;
			return false;
		} else if (door.open) {
			this.levelIsOver = true;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void input(char input) {
		// move hero
			super.input(input);
			
		// check club position
		for (Ogre o : ogres) {
			if (o.club != null)
				o.club.tick();
		}
		// move ogres
		for (Ogre o : ogres) {
			o.tick();
		}
	}
	
	@Override
	public Map getNextLevel() {
		return null;
	}
	*/
}
