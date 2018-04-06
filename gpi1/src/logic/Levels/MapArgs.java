package logic.Levels;

import java.io.Serializable;

/**
 * A JavaBean to store Map Arguments
 * 
 * @param nOgres    int The number of ogres passed to the map, should it require
 *                  it. Defaults to 0.
 * @param guardType int The type of guard passed to the map, should it require
 *                  it. Defaults to 0.
 *
 */
public final class MapArgs implements Serializable {

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		MapArgs other = (MapArgs) obj;
		if (guardType != other.guardType)
			return false;
		if (nOgres != other.nOgres)
			return false;
		return true;
	}

	private static final long serialVersionUID = -6589598135037878165L;

	/**
	 * 
	 * @param nOgres
	 * @param guardType
	 */
	public MapArgs(int nOgres, int guardType) {
		this.nOgres = nOgres;
		this.guardType = guardType;
	}

	/**
	 * The number of Ogres on the keep level
	 */
	private int nOgres = 0;

	public int getnOgres() {
		return nOgres;
	}

	/**
	 * The type of guard on the first level:<br>
	 * 0 - Rookie <br>
	 * 1 - Drunken <br>
	 * 2 - Suspicious
	 */
	private int guardType = 0;

	public int getGuardType() {
		return guardType;
	}

}
