package CrandallGridWorld;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class NerdFlyingBug extends NerdTeamBug {

	public NerdFlyingBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		setColor(null);

	}

	@Override
	public void interact(TeamBug bug) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canKill(TeamBug bugToKill) {

		return false;
	}

	@Override
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> list = new ArrayList<Location>();
		int d = 0;
		for (int o = 0; o < 8; o++) {
			list.addAll(AdjacentRow(d));
			d += 45;
		}
		return list;
	}

	public ArrayList<Location> AdjacentRow(int d) {
		ArrayList<Location> line = new ArrayList<Location>();
		for (int i = 6; i > 0; i--) {
			if (getGrid().isValid(JumpSpot(d, i))) {
				if (getGrid().get(JumpSpot(d, i)) != null) {
					if (((TeamBug) getGrid().get(JumpSpot(d, i))).getTeam() == (getTeam())) {

					} else {
						line.add(JumpSpot(d, i));
					}
				} else {
					line.add(JumpSpot(d, i));
				}
			}
		}
		return line;
	}

	public ArrayList<Location> baby() {
		return getGrid().getEmptyAdjacentLocations(getLocation());
	}

	public Location JumpSpot(int d, int n) {
		Location loc = getLocation();
		for (int i = 0; i < n; i++) {
			loc = loc.getAdjacentLocation(d);
		}
		return loc;
	}

	// Location loc = getLocation();
	// Location next =
	// loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return new NerdFlyingBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}
}
