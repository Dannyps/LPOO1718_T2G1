package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import logic.Coordinates;
import logic.Levels.*;

class TestLogic {

	
	private char getRandomDirection() {
		int random = new Random().nextInt(4);
		char[] chars = {'a', 's', 'd', 'w'};
		return chars[random];

	} 
	@Test
	public void testHeroMovementLv1() throws Exception {
		Map lv = new Level1();
		Coordinates oldHeroPos = lv.getHero().getCoordinates();
		lv.input('l');
		Coordinates newHeroPos = lv.getHero().getCoordinates();
		assertEquals(oldHeroPos, newHeroPos); // hero couldn't have moved to the left because there was a wall in that
												// position
		
		// move right
		lv.input('d');
		Coordinates heroPos = lv.getHero().getCoordinates();
		assertEquals(heroPos, new Coordinates(1, 2));
		
	}

	@Test
	public void testHeroandLeverLv1() throws Exception {
		Map lv;
		char[] seq;
		lv = new Level1();
		// test game ends when hero activates the lever and goes for the exit, avoiding the guard
		seq = "ddsswwsswssswwsssdddddssawwaaaaassaaaawwaaa".toCharArray(); // game should end
		for(char c : seq) {
			lv.input(c);
		}
		assertTrue(lv.isLevelOver());
	}
	
	@Test
	public void testHeroAndGuardLv1() throws Exception {
		Map lv;
		lv = new Level1();
		// test game ends when hero reaches the Guard
		char[] seq = "ddssssddddd".toCharArray(); // game should end
		for(char c : seq) {
			lv.input(c);
		}
		assertTrue(lv.isGameOver());
	}
	
	/**
	 * This test assumes that playing randomly will get the hero killed.
	 * @throws Exception if the map is invalid
	 */
	@Test
	public void testHeroAndOgreV2() throws Exception {
		Map lv;
		lv = new Level2();
		while(!lv.isGameOver()) {
			lv.input(getRandomDirection());
		}
	}
}
