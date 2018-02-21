package Levels;
import MapEntities.*;

public class Level2 extends Map {

	public Level2(String str) throws Exception {
		super("XXXXXXXXXI   O  KXX       XX       XX       XX       XX       XXH      XXXXXXXXXX");
		
		exitDoors.add((Door)map[1][0]);
	}

}
