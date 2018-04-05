package logic.Levels;

import java.util.ArrayList;
import java.util.List;

import logic.Coordinates;
import logic.MapEntities.*;
import logic.MapEntities.Guard.Guard;

public abstract class Map {
	public GenericMapEntity[][] map;
	private int gridSize;
	
	/**
	 * Flags for game status
	 */
	boolean isHeroCaptured;
	boolean isHeroOnStairs;
	boolean gameIsOver;
	boolean levelIsOver;
	
	/**
	 * References to entities (null references means they don't exist on this level)
	 */
	
	// Hero (can't be null)
	protected Hero hero = null;
	
	// List of all ogres (might be empty)
	protected List<Ogre> ogres;
	
	// The guard (might be null)
	protected Guard guard = null; // Guard (if any)
	
	// The hero club (might be null)
	protected PickableClub pickableClub = null; //?
	
	// The exit doors (not necessarily all the doors)
	protected List<Door> exitDoors;

	// Lever (might be null)
	protected Lever lever = null;

	// A direct reference to key (might be null)
	protected Key key = null;

	
	/**
	 * Constructor
	 * 
	 * @param str A string that represents the game map
	 * @throws Exception
	 */
	public Map(String str) throws Exception {
		int strlen = str.length();
		// Assuming the map is a square
		this.gridSize = (int) Math.sqrt(strlen);
		map = new GenericMapEntity[this.gridSize][this.gridSize];

		// check if n is perfect square number
		this.gridSize = (int) Math.sqrt(strlen);
		if (this.gridSize * this.gridSize != strlen) {
			throw new Exception("str doen't represent a valid map!");
		}

		// initialize lists
		this.exitDoors = new ArrayList<Door>();
		this.ogres = new ArrayList<Ogre>();
		
		// Call function to build the map
		buildMapFromString(str);
	}
	
	/**
	 * Constructs the map, filling it with the entities
	 * 
	 * @param str A string representing the map
	 * @throws Exception 
	 * 
	 */
	private void buildMapFromString(String str) throws Exception {
		int line = 0, column = 0;

		// fill matrix
		for (char c : str.toCharArray()) {
			if (column >= this.gridSize) {
				line++;
				column = 0;
			}
			switch (c) {
			case 'X':
				map[line][column] = new Wall(line, column);
				break;
			case 'H':
				if(this.hero == null) {
					this.hero = new Hero(line, column);
					map[line][column] = this.hero;
				}
				else throw new Exception("Invalid map! Defined multiple heroes");
				break;
			case 'I':
				map[line][column] = new Door(line, column);
				break;
			case 'G':
				map[line][column] = parseGuard(line, column);
				break;
			case 'O':
				Ogre o = new Ogre(line, column);
				this.ogres.add(o);
				map[line][column] = o;
				break;
			case 'K':
				map[line][column] = parseK(line, column);
				break;
			case ' ':
				map[line][column] = new Empty(line, column);
				break;
			default:
				System.err.println("Unrecognized char read!");
				System.exit(-2);
				break;
			}
			column++;
			
		}

	}
	
	/**
	 * Returns an entity to represent 'K', which can be a Key or a Lever depending on the game level
	 * It also changes the map attribute 'key' or 'lever' accordingly 
	 * @param line Coordinate x
	 * @param column Coordinate y
	 * @return A Key or Lever entity
	 */
	protected abstract GenericMapEntity parseK(int line, int column);
	
	/**
	 * Returns the guard for this level. Its personality can be a rookie, Suspicious, or Drunken. 
	 * It also sets the map attribute guard
	 * @param line Coordinate x
	 * @param column Coordinate y
	 * @return 
	 */
	protected abstract Guard parseGuard(int line, int column);
	
	/**
	 * 
	 * @param c
	 */
	public void tick(char c) {

		// Move hero
		moveHero(c);
		
		// Move guard and check if hero is captured
		if(this.guard != null) {
			moveGuard();
		
			// Verificar se hero e guard estão adjacentes
			if(areEntitiesAdj(this.hero, this.guard)) {
				this.gameIsOver = true;
			}
		}
		
		// Move ogres
		moveOgres();
		
		
	}
	
	/**
	 * Moves entities on map unless there's an obstacle such wall or closed door 
	 * @param e The entity to move
	 * @param nextPos The new entity coordinates
	 */
	protected void moveEntity(GenericMapEntity e, Coordinates nextPos) {
		Coordinates currPos = e.getCoordinates();
		GenericMapEntity next = this.map[nextPos.getX()][nextPos.getY()];
		if(next instanceof Door) {
			// if it's not a closed door
			if (!((Door) next).isOpen())
				return;
		}
		else if(!(next instanceof Wall)) {
			// and it's not a wall, move
			this.map[currPos.getX()][currPos.getY()] = e.getOverlappedEntity();
			e.setOverlappedEntity(this.map[nextPos.getX()][nextPos.getY()]);
			e.setCoordinates(nextPos);
			this.map[nextPos.getX()][nextPos.getY()] = e;
		}
	}
	
	protected void moveHero(char c) {
		Coordinates nextPos = this.hero.nextCoordinates(c);
		GenericMapEntity nextEntity = map[nextPos.getX()][nextPos.getY()];
		
		if (nextEntity instanceof Door)
			heroMetDoorHandler((Door) nextEntity);
		else if (nextEntity instanceof Lever)
			heroMetLeverHandler();
		else if (nextEntity instanceof Key)
			heroMetKeyHandler();
		else if (nextEntity instanceof Wall)
			return;

		// empty space, lever or key, move the hero
		moveEntity(this.hero, nextPos);

		// if the hero is armed, look for any adjacent ogres and stun them
		if(this.hero.hasClub()) {
			for(Ogre o : this.ogres) {
				if(areEntitiesAdj(this.hero, o)) {
					o.setStunned(true);
				}
			}
		}
	}
	
	protected void moveGuard() {
		if(this.guard != null) {
			Coordinates nextPos = this.guard.nextCoordinates();
			moveEntity(this.guard, nextPos);
		}
	}
	
	protected void moveOgres() {
		for(Ogre o : this.ogres) {
			Coordinates nextPos = o.nextCoordinates();
			moveEntity(o, nextPos);
			
			if(o.getCoordinates().equals(key.getCoordinates()))
				o.setOverKey(true);
			else
				o.setOverKey(false);
			// TODO Move the club
		}
	}
	
	/// Getters and setters
	///
	public int getGridSize() {
		return gridSize;
	}

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
	
	public String getEntityAtPos(Coordinates c) {
		return getEntityAtPos(c.getX(), c.getY());
	}

	
	/**
	 * return a string representation of the map.
	 */
	public String toString() {
		
		String ret = new String();

		// Make Header
		{
			ret += "┌─";
			for (int i = 0; i < this.gridSize - 1; i++) {
				ret += "┬─";
			}
			ret += "┐\n";
		}

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
	 * 
	 * @return an integer representing the level name.
	 */
	public int toInt() {
		String str = this.getClass().getName();
		return Integer.parseInt(str.substring(str.length() - 1));
	}
	
	/**
	 * 
	 * @param e1
	 * @param e2
	 * @return Returns true if the two given entities are adjacent
	 */
	protected boolean areEntitiesAdj(GenericMapEntity e1, GenericMapEntity e2) {
		Coordinates e1_coord = e1.getCoordinates();
		Coordinates e2_coord = e2.getCoordinates();
		
		if(e1_coord.getX() == e2_coord.getX()) {
			if(Math.abs(e1_coord.getY() - e2_coord.getY()) == 1)
				return true;
		}
		else if(e1_coord.getY() == e2_coord.getY()) {
			if(Math.abs(e1_coord.getX() - e2_coord.getX()) == 1)
				return true;
		}
		
		return false;
	}

	/// Handlers return true or false, indicating whether the first entity should be
	/// moved to the futurePosition or not.
	
	/**
	 * Handler called when hero reaches the lever. As consequence it opens the exit doors
	 */
	public abstract void heroMetLeverHandler();
	
	/**
	 * Handler called when the hero grabs the key
	 */
	public abstract void heroMetKeyHandler();
	
	/**
	 * Handler called when the hero reaches a door
	 * @param door A reference to the door
	 * @return True if the door is open, false otherwise
	 */
	public abstract boolean heroMetDoorHandler(Door door);
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract Map getNextLevel() throws Exception;
}
