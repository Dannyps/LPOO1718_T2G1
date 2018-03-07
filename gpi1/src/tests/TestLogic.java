package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cli.Gameplay;
import logic.Coordinates;
import logic.Levels.*;

class TestLogic {

	@Test
	public void testMapGen() throws Exception {
		Map lv = new Level1();
		Coordinates oldHeroPos = lv.getHero().getCoordinates();
		lv.input('l');
		Coordinates newHeroPos = lv.getHero().getCoordinates();

		assertEquals(oldHeroPos, newHeroPos); // hero couldn't have moved to the right because there was a wall in that
												// position
	}

}
