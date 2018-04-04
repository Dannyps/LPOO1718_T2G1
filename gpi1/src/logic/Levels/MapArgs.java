package logic.Levels;

import java.io.Serializable;

/**
 * A JavaBean to store Map Arguments
 * @param nOgres int The number of ogres passed to the map, should it require it. Defaults to 0.
 * @param guardType int The type of guard passed to the map, should it require it. Defaults to 0.
 *
 */
public final class MapArgs implements Serializable {
	
	public MapArgs(int nOgres, int guardType) {
		this.nOgres = nOgres;
		this.guardType = guardType;
	}

	private int nOgres=0;
	
	public int getnOgres() {
		return nOgres;
	}
	
	private int guardType=0;
	
	public int getGuardType() {
		return guardType;
	}
	
}
