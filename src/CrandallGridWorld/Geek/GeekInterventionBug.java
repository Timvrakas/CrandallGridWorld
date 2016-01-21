package CrandallGridWorld.Geek;

import java.util.ArrayList;

import CrandallGridWorld.TeamBug;
import CrandallGridWorld.Nerd.NerdCultBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class GeekInterventionBug extends TeamBug {

	public GeekInterventionBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return (new GeekInterventionBug(teamNum, hasBred));
	}

	@Override
	public void interact(TeamBug bug) {
		// TODO Auto-generated method stub
		if (canKill(bug))
			kill(bug);
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		// TODO Auto-generated method stub
		if (bugToKill instanceof NerdCultBug)
			return true;
		return false;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		if (byWhom instanceof NerdCultBug)
			return false;
		return true;
	}

	public Location selectMoveLocation(ArrayList<Location> locs) {
		ArrayList<Location> enemy = getGrid().getOccupiedLocations();
		for (int i = 0; i < enemy.size(); i++) {
			Actor a = getGrid().get(enemy.get(i));
			if (!(a instanceof TeamBug))
				continue;
			TeamBug b = (TeamBug) a;
			if (sameTeam(b) || !(b instanceof NerdCultBug))
				enemy.remove(i);
		}

		Location close = null;
		double distance = -1;
		for (Location l : enemy) {
			double curDistance = Math.sqrt((l.getRow() - getLocation().getRow()) * (l.getRow() - getLocation().getRow())
					+ (l.getCol() - getLocation().getCol()) * (l.getCol() - getLocation().getCol()));
			if (close == null || distance > curDistance) {
				distance = curDistance;
				close = l;
			}
		}
		Location move = getLocation().getAdjacentLocation(getLocation().getDirectionToward(close));
		if (!getGrid().isValid(move) || (getGrid().get(move) instanceof Actor)) {
			ArrayList<Location> moves = getGrid().getEmptyAdjacentLocations(getLocation());
			move = null;
			double minDis = -1;
			for (Location l : moves) {
				double curDistance = Math
						.sqrt((l.getRow() - getLocation().getRow()) * (l.getRow() - getLocation().getRow())
								+ (l.getCol() - getLocation().getCol()) * (l.getCol() - getLocation().getCol()));
				if (move == null || minDis < curDistance) {
					minDis = curDistance;
					move = l;
				}
			}
		}
		return move == null ? super.selectMoveLocation(locs) : move;
	}

}
