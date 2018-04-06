package logic.Levels;

import java.util.List;
import java.util.Random;

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
