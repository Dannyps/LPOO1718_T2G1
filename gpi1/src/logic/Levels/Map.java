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

/**
 * All levels must be extensions of this.
 */
public abstract class Map {
	protected GenericMapEntity[][] map; // matrix of entities
	private int gridSize; // assuming the grid is a square, this represents the side length
	private char buffer; // a buffer for the user input (used to control the hero)
	
	//
	// Flags for game status
	boolean isHeroCaptured; 
	boolean isHeroOnStairs;
	boolean gameIsOver;
	boolean levelIsOver;
	
	protected MapArgs args; // A class representing the user options on GUI
	public GenericMapEntity[][] getMap() {
		return map;
	}

	// 
	// References to entities on map
	protected Hero hero; // A direct reference to the Hero
	protected List<Ogre> ogres; // A direct reference to the Ogres (if any).
	protected List<Guard> guards; // A direct reference to the Ogres (if any).
	protected PickableClub pickableClub; // A direct reference to the Ogre (if any).
	protected List<Door> exitDoors; // A direct reference to all doors on map
	protected Lever lever; // A direct reference to lever
	protected Key key; // A direct reference to key

	
	/**
	 * Constructor
	 * 
	 * @param str A string that represents the game map
	 * @throws Exception If the string is not a valid map
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
	 * @see Map#Map(String)
	 * @param args The arguments defined by user on GUI
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
	 * Constructs the map, filling it with the entities
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
	 * This method is responsible of interpreting the map string and create the entities
	 * 
	 * @param coords Array of the entity coordinates [x,y]
	 * @param c The entity char representation
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
	 * Sets a lever/key on the map
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
	 * Sets the hero pickable club on the map
	 * @param line
	 * @param column
	 */
	private void makePickableClub(int line, int column) {
		this.pickableClub = new PickableClub(line, column, this);
		map[line][column] = this.pickableClub;
	}

	/**
	 * Sets the ogre on the map
	 * @param line
	 * @param column
	 */
	private void makeOgre(int line, int column) {
		Ogre o = new Ogre(line, column, this);
		this.ogres.add(o);
		map[line][column] = o;
	}

	/**
	 * Sets the guard on the map
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
	 * Sets doors on the map
	 * @param line
	 * @param column
	 */
	private void makeDoor(int line, int column) {
		map[line][column] = new Door(line, column, this);
	}

	/**
	 * Sets the hero on the map
	 * @param line
	 * @param column
	 */
	private void makeHero(int line, int column) {
		this.hero = new Hero(line, column, this);
		map[line][column] = this.hero;
	}

	/**
	 * Sets walls on the map
	 * @param line
	 * @param column
	 */
	private void makeWall(int line, int column) {
		map[line][column] = new Wall(line, column, this);
	}
	
	/// Handlers return true or false, indicating whether the first entity should be
	/// moved to the futurePosition or not.
	
	/**
	 * Handler called when the hero reaches the key. It updates the hero status and backs-up a reference to the key
	 * @return
	 */
	public boolean heroMetKeyHandler() {
		hero.hasKey = true;
		Coordinates cKC = key.getCoordinates(); // current Key Coordinates
		map[cKC.x][cKC.y] = new Empty(cKC.x, cKC.y, this); // make the old Key position become empty
		return true;
	}
	
	/**
	 * Handler called when the hero reaches the lever. It opens all exit doors
	 * @return
	 */
	public boolean heroMetLeverHandler() {
		getExitDoors().forEach(door -> door.setOpen(true));
		lever.setOpen();
		return false;
	}
	
	/**
	 * Handler called when the hero reaches a door. 
	 * @param door
	 * @return
	 */
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
	
	/// functions to build a string representing the map
	
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
	 * Handles user input (to control the hero)
	 * @param input
	 */
	public void input(char input) {
		this.setBuffer(input);
		hero.tick();
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
	
	/**
	 * A list of all exit doors on this map
	 * @return List<Door> of exit doors
	 */
	public List<Door> getExitDoors() {
		return this.exitDoors;
	}
	
	/**
	 * Returns a reference to the hero
	 * @return
	 */
	public GenericMapEntity getHero() {
		return hero;
	}
	
	/**
	 * Returns a list of all guards
	 * @return
	 */
	public List<Guard> getGuards() {
		return guards;
	}

	/**
	 * Returns a reference to lever
	 * @return
	 */
	public Lever getLever() {
		return lever;
	}
	
	/**
	 * Returns the char on the buffer (hero next move direction)
	 * @return hero next move direction from buffer
	 */
	public char getBuffer() {
		return buffer;
	}
	
	/**
	 * Sets the char on the buffer (hero next move direction)
	 * @param buffer Next hero move direction
	 */
	public void setBuffer(char buffer) {
		this.buffer = buffer;
	}
	
	/**
	 * Returns an object with GUI user defined settings for this game
	 * @return
	 */
	public MapArgs getArgs() {
		return args;
	}
	
	/**
	 * Set this map's user defined settings on GUI
	 * @param args
	 */
	public void setArgs(MapArgs args) {
		this.args = args;
	}

	/**
	 * @return an integer representing the level name.
	 */
	public int toInt() {
		String str = this.getClass().getName();
		return Integer.parseInt(str.substring(str.length() - 1));
	}
	
	/**
	 * Returns the map sides length (the square side length)
	 * @return
	 */
	public int getGridSize() {
		return gridSize;
	}
	
	/**
	 * Tells this level is over
	 * @return True if level is over, false otherwise
	 */
	public boolean isLevelOver() {
		return levelIsOver;
	}
	
	/**
	 * Sets the level status (over or not over)
	 * @param levelIsOver True if over, else false
	 */
	public void setLevelIsOver(boolean levelIsOver) {
		this.levelIsOver = levelIsOver;
	}

	/**
	 * Sets the game status (if it's over or not)
	 * @param gameIsOver True if game is over, false otherwise
	 */
	public void setGameIsOver(boolean gameIsOver) {
		this.gameIsOver = gameIsOver;
	}

	/**
	 * Tells if the game is over
	 * @return True if it's over, else false
	 */
	public boolean isGameOver() {
		return gameIsOver;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract Map getNextLevel() throws Exception;
	
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


}
