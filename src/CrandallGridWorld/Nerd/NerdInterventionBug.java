package CrandallGridWorld.Nerd;

import java.util.ArrayList;

import CrandallGridWorld.BugFinder;
import CrandallGridWorld.TeamBug;
import CrandallGridWorld.Geek.GeekCultBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class NerdInterventionBug extends NerdTeamBug {

	public NerdInterventionBug(int tN, boolean hasBred) {
		super(tN, hasBred);
		int r = ((int) (Math.random() * 8)) * 45;
		setDirection(r);
		setColor(null);
	}

	@Override
	public void interact(TeamBug bug) {
		if (!sameTeam(bug) && bug instanceof GeekCultBug) {
			kill(bug);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdInterventionBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void act() {
		ArrayList<Location> locs = BugFinder.getAllBugs("GeekCultBug", getGrid());
		if (locs.isEmpty()) {
			System.out.println("error");
			super.act();
		} else {
			setDirection(getLocation().getDirectionToward(locs.get(0)));

			if (canMove())
				move();
			ArrayList<Location> targets = getGrid().getOccupiedAdjacentLocations(getLocation());

			for (Location x : targets) {
				TeamBug z = (TeamBug) getGrid().get(x);
				if (z.getClass().getName().endsWith("GeekCultBug")) {
					interact(z);
				} else {
					System.out.println("error");
				}
			}
			breed();

		}
	}

	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (gr.isValid(next))
			moveTo(next);
		else
			removeSelfFromGrid();

	}

	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return false;
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		if (!gr.isValid(next))
			return false;
		Actor neighbor = gr.get(next);
		return (neighbor == null);
		// ok to move into empty location or onto flower
		// not ok to move onto any other actor
	}

}