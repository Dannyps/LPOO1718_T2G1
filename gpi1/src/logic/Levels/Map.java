package logic.Levels;

import java.util.ArrayList;
import java.util.List;

import logic.Coordinates;
import logic.Direction;
import logic.MapEntities.*;
import logic.MapEntities.Guard.DrunkenGuard;
import logic.MapEntities.Guard.Guard;
import logic.MapEntities.Guard.RookieGuard;
import logic.MapEntities.Guard.SuspiciousGuard;

public abstract class Map {
	protected GenericMapEntity[][] map;
	
	public GenericMapEntity[][] getMap() {
		return map;
	}

	private int gridSize;

	public abstract Map getNextLevel() throws Exception;

	/**
	 * 
	 * @return an integer representing the level name.
	 */
	public int toInt() {
		String str = this.getClass().getName();
		return Integer.parseInt(str.substring(str.length() - 1));
	}

	public int getGridSize() {
		return gridSize;
	}

	protected MapArgs args;

	private char buffer;
	boolean isHeroCaptured;
	boolean isHeroOnStairs;
	boolean gameIsOver;
	boolean levelIsOver;

	public boolean isLevelOver() {
		return levelIsOver;
	}

	public void setLevelIsOver(boolean levelIsOver) {
		this.levelIsOver = levelIsOver;
	}

	public void setGameIsOver(boolean gameIsOver) {
		this.gameIsOver = gameIsOver;
	}

	public boolean isGameOver() {
		return gameIsOver;
	}

	/**
	 * A direct reference to the Hero.
	 */
	protected Hero hero;

	public GenericMapEntity getHero() {
		return hero;
	}

	/**
	 * A direct reference to the Ogres (if any).
	 */
	protected List<Ogre> ogres;

	/**
	 * A direct reference to the Ogres (if any).
	 */
	protected List<Guard> guards;

	/**
	 * A direct reference to the Ogre (if any).
	 */
	protected PickableClub pickableClub;

	/**
	 * A direct reference to all doors on map
	 */
	protected List<Door> exitDoors;

	/**
	 * A direct reference to lever
	 */
	protected Lever lever;

	/**
	 * A direct reference to key
	 */
	protected Key key;

	/**
	 * Constructor
	 * 
	 * @param str A string that represents the game map
	 * @throws Exception
	 */
	public Map(String str) throws Exception {
		//load arguments
		
		
		int strlen = str.length();
		// Assuming the map is a square
		this.gridSize = (int) Math.sqrt(strlen);
		map = new GenericMapEntity[this.gridSize][this.gridSize];

		// check if n is perfect square number
		this.gridSize = (int) Math.sqrt(strlen);
		if (this.gridSize * this.gridSize != strlen) {
			throw new Exception("str doen't represent a square!");
		}

		// initialize lists
		this.exitDoors = new ArrayList<Door>();
		this.ogres = new ArrayList<Ogre>();
		this.guards = new ArrayList<Guard>();

		buildMapFromString(str);
	}
	
	/**
	 * Constructor
	 * 
	 * @param str A string that represents the game map
	 * @throws Exception
	 */
	public Map(String str, MapArgs args) throws Exception {
		this.args = args;
		int strlen = str.length();
		// Assuming the map is a square
		this.gridSize = (int) Math.sqrt(strlen);
		map = new GenericMapEntity[this.gridSize][this.gridSize];

		// check if n is perfect square number
		this.gridSize = (int) Math.sqrt(strlen);
		if (this.gridSize * this.gridSize != strlen) {
			throw new Exception("str doen't represent a square!");
		}

		// initialize lists
		this.exitDoors = new ArrayList<Door>();
		this.ogres = new ArrayList<Ogre>();
		this.guards = new ArrayList<Guard>();

		buildMapFromString(str);
	}
	
	
	/**
	 * @return List<Empty> A list of all the positions suitable to deploy a foe.
	 */
	public List<Empty> getEmptyPositions() {
		List<Empty> emptySpaces = new ArrayList<Empty>();
		
		for (GenericMapEntity[] mapRow : map) {
			for (GenericMapEntity ent : mapRow) {
				if(ent instanceof Empty) {
					boolean redFlag = false;
					for (Direction dir : Direction.values()) {
						//System.out.println(ent.getNeighbor(dir));
						if(ent.getNeighbor(dir) instanceof Hero) {
							redFlag = true;
						}
					}
					
					if(!redFlag) {
						// this Empty position is adjacent-player-free.
						emptySpaces.add((Empty) ent);	
						//System.out.println(ent.getCoordinates().x + ", " + ent.getCoordinates().y);
					}
				}
			}
		}
		return emptySpaces;
	}

	/**
	 * Constructs the map, filling it with the entities
	 * 
	 * @param str A string representing the map
	 */
	private void buildMapFromString(String str) {
		int line = 0, column = 0;

		// fill matrix
		for (char c : str.toCharArray()) {
			if (column >= this.gridSize) {
				line++;
				column = 0;
			}
			buildMapFromStringSwitch(new int[] {line, column}, c);
			column++;
		}

	}

	/**
	 * @param line
	 * @param column
	 * @param c
	 */
	private void buildMapFromStringSwitch(int coords[], char c) {
		int line = coords[0], column = coords[1];
		switch (c) {
		case 'X':
			makeWall(line, column);
			break;
		case 'H':
			makeHero(line, column);
			break;
		case 'I':
			makeDoor(line, column);
			break;
		case 'G':
			makeGuard(line, column);
			break;
		case 'O':
			makeOgre(line, column);
			break;
		case 'A':
			makePickableClub(line, column);
			break;
		case 'K':
			makeLeverOrKey(line, column);
			break;
		case ' ':
			map[line][column] = new Empty(line, column, this);
			break;
		default:
			System.err.println("Unrecognized char read!");
			System.exit(-2);
			break;
		}
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makeLeverOrKey(int line, int column) {
		if (this instanceof Level1) {
			this.lever = new Lever(line, column, this);
			map[line][column] = this.lever;
		} else {
			this.key = new Key(line, column, this);
			map[line][column] = this.key;
		}
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makePickableClub(int line, int column) {
		this.pickableClub = new PickableClub(line, column, this);
		map[line][column] = this.pickableClub;
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makeOgre(int line, int column) {
		Ogre o = new Ogre(line, column, this);
		this.ogres.add(o);
		map[line][column] = o;
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makeGuard(int line, int column) {
		switch (args.getGuardType()) {
		case 0:
			this.guards.add(new RookieGuard(line, column, this));
			break;
		case 1:
			this.guards.add(new DrunkenGuard(line, column, this));
			break;
		case 2:
			this.guards.add(new SuspiciousGuard(line, column, this));
			break;
		}
		map[line][column] = this.guards.get(0);
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makeDoor(int line, int column) {
		map[line][column] = new Door(line, column, this);
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makeHero(int line, int column) {
		this.hero = new Hero(line, column, this);
		map[line][column] = this.hero;
	}

	/**
	 * @param line
	 * @param column
	 */
	private void makeWall(int line, int column) {
		map[line][column] = new Wall(line, column, this);
	}

	/**
	 * return a string representation of the map.
	 */
	public String toString() {

		String ret = new String();
		ret = makeStringMapHeader(ret);
		ret = makeStringMapBody(ret);
		ret = makeStringMapFooter(ret);
		return ret;
	}

	/**
	 * @param ret
	 * @return
	 */
	private String makeStringMapFooter(String ret) {
		// Make Footer
		{
			ret += "└─";
			for (int i = 0; i < this.gridSize - 1; i++) {
				ret += "┴─";
			}
			ret += "┘\n";
		}
		return ret;
	}

	/**
	 * @param ret
	 * @return
	 */
	private String makeStringMapBody(String ret) {
		// Make Body
		{

			for (int i = 0; i < this.gridSize; i++) {
				ret += "│";
				for (int j = 0; j < this.gridSize; j++) {
					ret += map[i][j];
					ret += "│";
				}
				ret += "\n";
				// -- new line
				if (i == this.gridSize - 1)
					break; // do not draw ------ under last line
				ret += "├─";
				for (int j = 0; j < this.gridSize - 1; j++) {
					ret += "┼─";
				}
				ret += "┤\n";
			}

		}
		return ret;
	}

	/**
	 * @param ret
	 * @return
	 */
	private String makeStringMapHeader(String ret) {
		// Make Header
		{
			ret += "┌─";
			for (int i = 0; i < this.gridSize - 1; i++) {
				ret += "┬─";
			}
			ret += "┐\n";
		}
		return ret;
	}

	/**
	 * 
	 * @param x x Coordinate
	 * @param y y Coordinate
	 * @return Returns the string that represents the entity at some position
	 */
	public String getEntityAtPos(int x, int y) {
		if (map[y][x] != null)
			return map[y][x].toString();
		else
			return "";
	}

	public void input(char input) {
		this.setBuffer(input);
		hero.tick();
	}

	public List<Door> getExitDoors() {
		return this.exitDoors;
	}

	/// Handlers return true or false, indicating whether the first entity should be
	/// moved to the futurePosition or not.

	public char getBuffer() {
		return buffer;
	}

	public void setBuffer(char buffer) {
		this.buffer = buffer;
	}

	public MapArgs getArgs() {
		return args;
	}

	public void setArgs(MapArgs args) {
		this.args = args;
	}

	public boolean heroMetKeyHandler() {
		hero.hasKey = true;
		Coordinates cKC = key.getCoordinates(); // current Key Coordinates
		map[cKC.x][cKC.y] = new Empty(cKC.x, cKC.y, this); // make the old Key position become empty
		return true;
	}

	public boolean heroMetLeverHandler() {
		getExitDoors().forEach(door -> door.setOpen(true));
		lever.setOpen();
		return false;
	}

	public boolean heroMetDoorHandler(Door door) {
		if(!door.isOpen() && hero.hasKey) {
			door.setOpen(true);
			return false;
		}else if(door.isOpen()) {
			this.levelIsOver=true;
			return true;
		}else {
			return false;
		}
	}

	public List<Guard> getGuards() {
		return guards;
	}

	public Lever getLever() {
		return lever;
	}

}
