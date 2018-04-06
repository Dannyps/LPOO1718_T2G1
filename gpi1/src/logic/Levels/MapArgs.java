package logic.Levels;

import java.io.Serializable;

/**
 * A JavaBean to store Map Arguments
 * @param nOgres int The number of ogres passed to the map, should it require it. Defaults to 0.
 * @param guardType int The type of guard passed to the map, should it require it. Defaults to 0.
 *
 */
public final class MapArgs implements Serializable {
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
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

	private int nOgres=0;
	
	public int getnOgres() {
		return nOgres;
	}
	
	private int guardType=0;
	
	public int getGuardType() {
		return guardType;
	}
	
}
