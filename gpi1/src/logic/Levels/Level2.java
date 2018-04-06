package logic.Levels;
import logic.MapEntities.*;

/**
 * Level 2 (1 ogre, no guards)
 * 
 */
public class Level2 extends Map {

	MapArgs ma;

	Ogre ogre = ogres.get(0); // there's only one ogre in this level.
	/**
	 * Constructor
	 * @param args The arguments defined by user on GUI
	 * @throws Exception Exception If the pre-defined is not a valid map
	 */
	public Level2(MapArgs ma) throws Exception {
		super("XXXXXXXXXI   O  KXX       XX       XX       XX       XX       XXH      XXXXXXXXXX");
		exitDoors.add((Door)map[1][0]);
		this.ma = ma;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void input(char input) {
		// check club position
		if(ogre.club!=null)
			ogre.club.tick();
		
		// move hero
		super.input(input);
		
		// move ogre
		ogre.tick();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map getNextLevel() {
		try {
			return new Level3(ma);
		} catch (Exception e) {
			return null;
		}
	}

}
