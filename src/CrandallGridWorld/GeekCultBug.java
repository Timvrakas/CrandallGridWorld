package CrandallGridWorld;

import java.awt.Color;

import info.gridworld.grid.Location;

public class GeekCultBug extends TeamBug {

	public GeekCultBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		setColor(Color.RED);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return new GeekCultBug(teamNum, hasBred);
	}

	@Override
	public void interact(TeamBug bug) {
		// TODO Auto-generated method stub
		Location hisLoc = bug.getLocation();
		if (!bug.sameTeam(this) && !(bug instanceof NerdInterventionBug)) {
			bug.removeSelfFromGrid();
			(new GeekCultBug(getTeam(), true)).putSelfInGrid(getGrid(), hisLoc);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

}
