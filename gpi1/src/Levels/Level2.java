package Levels;
import MapEntities.*;

public class Level2 extends Map {

	boolean heroHasKey=false;
	
	public Level2() throws Exception {
		super("XXXXXXXXXI   O  KXX       XX       XX       XX       XX       XXH      XXXXXXXXXX");
		
		exitDoors.add((Door)map[1][0]);
	}

	@Override
	public void heroMetLeverHandler() {
		// this should not happen!
		assert(1==2);
		return;
	}

	@Override
	public void heroMetKeyHandler() {
		heroHasKey=true;		
	}

}
