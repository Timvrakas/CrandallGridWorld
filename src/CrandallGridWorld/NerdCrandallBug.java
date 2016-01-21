package CrandallGridWorld;



import java.util.ArrayList;
import java.util.Random;

import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class NerdCrandallBug extends NerdTeamBug {

	private ArrayList<NerdTeamBug> teamList;
	int actCount = 0;
	Random rand;
	public static final int scanSize = 9;
	public static final int bugFear = 10;

	public NerdCrandallBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		setColor(null);
		teamList = getTeamList();
		rand = new Random();
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return false;
	}

	@Override
	public void interact(TeamBug bug) {
		// No interaction
	}

	@Override
	public void cloneSelf() {
		super.cloneSelf();
		if (child instanceof NerdCrandallBug) {
			NerdCrandallBug childBug = (NerdCrandallBug) child;
			childBug.setCounter(actCount);
		}
	}

	public void setCounter(int num) {
		actCount = num;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}

	@Override
	public void act() {
		super.act();
		if (isDead()) {
			return;
		}
		actCount++;
		if (actCount == 3) {
			actCount = 0;

			ArrayList<NerdTeamBug> deadBugs = new ArrayList<NerdTeamBug>();
			for (NerdTeamBug bug : teamList) {
				if (bug.isDead()) {
					deadBugs.add(bug);
				}
			}
			for (int i = 0; i < deadBugs.size(); i += 2) {
				TeamBug dead = deadBugs.get((int) (Math.random() * deadBugs.size()));
				dead.heal();
				boolean found = false;
				Location newLoc;
				do {// Can't handle unbounded grid
					int row = rand.nextInt(getGrid().getNumRows());
					int col = rand.nextInt(getGrid().getNumCols());
					newLoc = new Location(row, col);
					found = getGrid().isValid(newLoc) && getGrid().get(newLoc) == null;
				} while (!found);
				dead.putSelfInGrid(getGrid(), newLoc);
				deadBugs.remove(dead);
			}
		}
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdCrandallBug(teamNum, hasBred);
	}

	public ArrayList<Location> getEnemies(int size) {
		Location loc = getLocation();
		ArrayList<Location> risks = new ArrayList<Location>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Location test = new Location(loc.getRow() + i - ((int) (size / 2)),
						loc.getCol() + j - ((int) (size / 2)));
				if (getRisk(test) >= 1) {
					risks.add(test);
				}
			}
		}
		return risks;
	}

	private int getRisk(Location test) {
		if (!getGrid().isValid(test)) {
			return 1;
		}
		if (getGrid().get(test) instanceof Actor) {
			if (getGrid().get(test) instanceof TeamBug) {
				TeamBug t = (TeamBug) getGrid().get(test);
				if (sameTeam(t)) {
					return 0;
				} else {
					return bugFear;
				}
			}
			return 1;
		}
		return 0;
	}

	public int getMoveDirection(int size) {
		ArrayList<Location> risks = getEnemies(size);
		double xRisk = 0;
		double yRisk = 0;

		for (Location loc : risks) {
			int delX = loc.getCol() - getLocation().getCol();
			int delY = getLocation().getRow() - loc.getRow();
			double distX;
			double distY;

			if (delX != 0)
				distX = 1.0 / delX;
			else
				distX = 0;

			if (delY != 0)
				distY = 1.0 / delY;
			else
				distY = 0;

			xRisk += getRisk(loc) * distX;
			yRisk += getRisk(loc) * distY;
		}
		double atan = Math.atan2(-yRisk, -xRisk); // the angle should be the opposite direction from the risk!
		int angle = (int) (((360 * atan / (2 * Math.PI)) + 360) % 360); // Convert +/- radians to + degrees
		angle = ((90 - angle) + 360) % 360; // Invert and translate 90 deg
		return angle;
	}

	@Override
	public Location selectMoveLocation(ArrayList<Location> moveLocs) {
		int angle = getMoveDirection(scanSize);
		Location bestLoc = getLocation().getAdjacentLocation(angle);
		if (!moveLocs.contains(bestLoc)) {
			System.out.println("Error!");
			return super.selectMoveLocation(moveLocs);
		}
		return bestLoc;
	}

	public String toString() {
		return super.toString() + ", actCount=" + actCount;
	}
}
