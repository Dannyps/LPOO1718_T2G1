package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import logic.Coordinates;
import logic.Direction;
import logic.Levels.*;
import logic.MapEntities.*;

public class TestLogic {

	public TestLogic() {

	}

	private char getRandomDirection() {
		int random = new Random().nextInt(4);
		char[] chars = { 'a', 's', 'd', 'w' };
		return chars[random];

	}

	@Test
	public void testHeroMovementLv1() throws Exception {
		Map lv = new Level1(new MapArgs(2, 0));
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
		lv = new Level1(new MapArgs(2, 0));
		assertFalse(lv.getExitDoors().get(0).isOpen());
		assertFalse(lv.getExitDoors().get(1).isOpen());
		// test game ends when hero activates the lever and goes for the exit, avoiding
		// the guard
		seq = "ddsswwsswssswwsssdddddssawwaaaaassaaaawwaaa".toCharArray(); // game should end
		for (char c : seq) {
			lv.input(c);
		}
		assertTrue(lv.isLevelOver());
		assertTrue(lv.getExitDoors().get(0).isOpen());
		assertTrue(lv.getExitDoors().get(1).isOpen());
	}

	@Test
	public void testHeroAndGuardLv1() throws Exception {
		Map lv;
		lv = new Level1(new MapArgs(2, 0));
		// test game ends when hero reaches the Guard
		char[] seq = "ddssssddddd".toCharArray(); // game should end
		for (char c : seq) {
			lv.input(c);
		}
		assertTrue(lv.isGameOver());
	}

	/**
	 * This test assumes that playing randomly will get the hero killed.
	 * 
	 * @throws Exception if the map is invalid
	 */
	@Test
	public void testHeroAndOgreV2() throws Exception {
		Map lv;
		lv = new Level2(new MapArgs(2, 0));
		while (!lv.isGameOver()) {
			lv.input(getRandomDirection());
		}
	}

	/**
	 * This test ensures the drunken guard eventually falls asleep.
	 * 
	 * @throws Exception if the map is invalid
	 */
	@Test
	public void testDrunkenGuardFallsAsleep() throws Exception {
		int c = 0;
		Map lv;
		lv = new Level1(new MapArgs(2, 1));
		Coordinates lastC = new Coordinates(0, 0);
		Coordinates currC = lv.getGuards().get(0).getCoordinates();

		boolean guardStopped = false;
		while (!guardStopped && c < 1000) {
			lv.input('a'); // do not leave the spawn point
			currC = lv.getGuards().get(0).getCoordinates();
			if (currC == lastC) {
				assertTrue(true);
				return;
			}
			lastC = currC;
			c++;
		}

		fail("Could not verify the guard stops after 1000 iterations.");
	}

	/**
	 * This test ensures the drunken guard eventually falls asleep.
	 * 
	 * @throws Exception if the map is invalid
	 */
	@Test
	public void testSuspiciousGuardLooksBack() throws Exception {
		int c = 0;
		Map lv;
		lv = new Level1(new MapArgs(2, 2));
		Coordinates lastC = new Coordinates(0, 0);
		Coordinates lastC2 = new Coordinates(0, 0);
		Coordinates currC = lv.getGuards().get(0).getCoordinates();

		boolean guardStopped = false;
		while (!guardStopped && c < 1000) {
			lv.input('a'); // do not leave the spawn point
			currC = lv.getGuards().get(0).getCoordinates();
			if (currC == lastC2) {
				assertTrue(true);
				return;
			}
			lastC = currC;
			lastC2 = lastC;
			c++;
		}

		fail("Could not verify the guard looks back after 1000 iterations.");
	}

	@Test
	public void testCoordinates() {
		Coordinates c1 = new Coordinates(5, 6);
		Coordinates c2 = new Coordinates(5, 7);
		Coordinates c3 = new Coordinates(5, 6);
		Coordinates c4 = new Coordinates(9, 6);

		assertEquals(c1, c3);
		assertEquals(c1.toString(), "{5,6}");
		assertNotEquals(c2, c3);
		assertNotEquals(c4, c3);
		assertNotEquals(c2, null);
		assertNotEquals(c2, new MapArgs(2, 2));
		
		assertEquals(Coordinates.distance(c4, c4), 0, 0.00001);
		assertEquals(Coordinates.distance(c1, c2), 1, 0.00001);
	}

	@Test
	public void testDoor() {
		Door d = new Door(0, 0, null);

		assertEquals(d.getCoordinates(), new Coordinates(0, 0));
		assertEquals(d.toString(), "I");
		d.setOpen(true);
		assertEquals(d.toString(), "S");
	}

	@Test
	public void testEmpty() {
		Empty e = new Empty(0, 0, null);

		assertEquals(e.toString(), " ");
	}
	
	@Test
	public void testPickableClub() {
		PickableClub e = new PickableClub(0, 0, null);

		assertEquals(e.toString(), "A");
	}
	
	@Test
	public void testOgre() throws Exception {
		
		Ogre e = new Ogre(0, 0, new Level1(new MapArgs(0,2)));
		assertFalse(e.hasClub());
		e = new Ogre(0, 0, new Level3(new MapArgs(0,2)));

		if(e.isStunned())
			assertEquals(e.toString(),"8");
		else if(e.getOldKey() == null)
			assertEquals(e.toString(),"O");
		else
			assertEquals(e.toString(),"$");
		
		e = new Ogre(0, 0, new Level3(new MapArgs(0,2)));
		e.setStunned();
		Coordinates oldpos=e.getCoordinates();
		assertTrue(e.tick());
		assertTrue(Coordinates.distance(e.getCoordinates(), oldpos) < Math.sqrt(2)); // it's got to be either 1 or 0
		assertTrue(e.isStunned());
		e.tick();
		assertTrue(e.isStunned());
		e.tick();
		assertFalse(e.isStunned());
			
	}
	
	@Test
	public void testOgreClub() throws Exception {
		Level3 l = new Level3(new MapArgs(0, 0));
		OgreClub e = new OgreClub(5, 5, l);
		assertTrue(e.tick());

		assertEquals(e.toString(), "*");
	}

	@Test
	public void testGenericMapEntity() throws Exception {
		Level1 l = new Level1(new MapArgs(0, 0));

		GenericMapEntity g = l.getMap()[0][0]; // upper-left corner entity

		assertEquals(g.getNeighbor(Direction.LEFT), null); // no neighbor
		assertEquals(g.getNeighbor(Direction.RIGHT).getClass(), Wall.class); // Wall
		assertEquals(g.getNeighbor(Direction.TOP), null); // no neighbor

		g = l.getMap()[1][1]; // upper-left corner entity

		assertEquals(g.getNeighbor(Direction.TOP).getClass(), Wall.class); // Wall

		g = l.getMap()[l.getMap().length - 1][l.getMap().length - 1]; // lower-right corner entity

		assertEquals(g.getNeighbor(Direction.LEFT).getClass(), Wall.class); // Wall
		assertEquals(g.getNeighbor(Direction.RIGHT), null); // no neighbor
		assertEquals(g.getNeighbor(Direction.TOP).getClass(), Wall.class); // Wall
		assertEquals(g.getNeighbor(Direction.BOTTOM), null); // no neighbor

		assertTrue(g.tick());

	}

	@Test
	public void testMap() throws Exception {
		Level1 l = new Level1(new MapArgs(0, 0));
		assertEquals(l.toInt(), 1);

		List<Empty> epl = l.getEmptyPositions();

		for (Empty ent : epl) {
			assertTrue((GenericMapEntity) ent instanceof Empty );
		}
		
		assertEquals(l.toString(),
				"┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐\n" + 
				"│X│X│X│X│X│X│X│X│X│X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│H│ │ │I│ │X│ │G│X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│X│X│ │X│X│X│ │ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│ │I│ │I│ │X│ │ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│X│X│ │X│X│X│ │ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│I│ │ │ │ │ │ │ │ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│I│ │ │ │ │ │ │ │ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│X│X│ │X│X│X│X│ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│ │I│ │I│ │X│K│ │X│\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│X│X│X│X│X│X│X│X│X│X│\n" + 
				"└─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘\n");
		
		assertEquals(l.getEntityAtPos(0, 0), "X");
		
		assertEquals(l.getArgs(), new MapArgs(0,0));

	}

}
