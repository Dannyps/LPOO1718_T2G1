package logic.Levels;

import java.util.List;
import java.util.Random;

import logic.Coordinates;
import logic.MapEntities.*;

/**
 * Level 3 (several ogres, no guard)
 * 
 */
public class Level3 extends Map {
	
	MapArgs ma;

	public Level3(MapArgs ma) throws Exception {
		super("XXXXXXXXXI      KXX       XX       XX       XX       XX       XXH  A   XXXXXXXXXX");
		exitDoors.add((Door) map[1][0]);
		distributeOgres(ma.getnOgres());
		this.ma = ma;
	}

	/**
	 * Distributes Ogres randomly in the map, preventing, however, that they spawn adjacently to the player.
	 * @param getnOgres int the number of ogres to distribute.
	 */
	private void distributeOgres(int n) {
		
		List<Empty> emptySpaces = getEmptyPositions();
		
		if(n>emptySpaces.size()) {
			n=emptySpaces.size(); // this is our maximum number of ogres. Good luck tho
		}
		
		for(int i = 0; i< n;i++) {
			Random r = new Random();
			Empty chosen = emptySpaces.get(r.nextInt(emptySpaces.size())); // pick a random position
			emptySpaces.remove(chosen);
			Ogre newOgre = new Ogre(chosen.getCoordinates().x, chosen.getCoordinates().y, this);
			map[chosen.getCoordinates().x][chosen.getCoordinates().y] = newOgre;
			ogres.add(newOgre);
		}
		
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
		if (!door.isOpen() && hero.hasKey) {
			door.setOpen(true);
			return false;
		} else if (door.isOpen()) {
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
		for (GenericMapEntity o : ogres) {
			o.tick();
		}
	}
	
	@Override
	public Map getNextLevel() {
		return null;
	}
}
