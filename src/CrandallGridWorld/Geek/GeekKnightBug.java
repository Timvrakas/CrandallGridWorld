package CrandallGridWorld.Geek;

import java.util.ArrayList;

import CrandallGridWorld.TeamBug;
import CrandallGridWorld.Nerd.NerdCrandallBug;
import CrandallGridWorld.Nerd.NerdGhostBug;
import info.gridworld.grid.Location;

public class GeekKnightBug extends TeamBug {

	public GeekKnightBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return new GeekKnightBug(teamNum, hasBred);
	}

	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> moveLocs = new ArrayList<Location>();
		for (int i = 0; i < 4; i++) {
			moveLocs.add(getLocation().getAdjacentLocation(i * 90).getAdjacentLocation(45 + i * 90));
			moveLocs.add(getLocation().getAdjacentLocation(i * 90).getAdjacentLocation(315 + i * 90));
		}
		ArrayList<Location> answer = new ArrayList<Location>();
		for (Location loc : moveLocs)
			if (getGrid().isValid(loc) && getGrid().get(loc) == null)
				answer.add(loc);
		return answer;
	}

	@Override
	public void interact(TeamBug bug) {
		// TODO Auto-generated method stub
		boolean canKill = false;
		if (canKill(bug))
			canKill = true;
		if (!sameTeam(bug) && canKill)
			kill(bug);
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		if (sameTeam(bugToKill) || bugToKill instanceof NerdCrandallBug
				|| bugToKill instanceof NerdGhostBug)
			return false;
		return true;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

}
