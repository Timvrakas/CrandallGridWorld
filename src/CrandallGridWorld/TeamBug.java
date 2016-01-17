package ourTeam;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

//Version 1.07
//@Author Suchir/Billy/Tim, and everyone who helped figure this out on the whiteboard
//Any edits/improvements are appreciated
//Not sure if we need final for the methods, but it seemed useful
public abstract class TeamBug extends Critter {
	private int team;
	private int turnsToDeath;
	private int turnsToUnfreeze;
	private boolean isSterile;
	private boolean isInfected;
	private boolean canKill;

	public static final int GEEK = 1;
	public static final int NERD = 2;

	public TeamBug() {
		super();
		team = GEEK;
		turnsToDeath = -1;
		isSterile = true;
		isInfected = false;
	}

	public TeamBug(int teamNum, boolean hasBred) {
		super();
		team = teamNum;
		turnsToDeath = -1;
		isSterile = hasBred;
		isInfected = false;
	}

	public int getTurnsToDeath() {
		return turnsToDeath;
	}

	public boolean isInfected() {
		return isInfected;
	}

	public boolean canKill() {
		return canKill;
	}

	public int getTurnsToUnfreeze() {
		return turnsToUnfreeze;
	}

	public int getTeam() {
		return team;
	}

	// Determines if the two bugs are on the same team
	public boolean sameTeam(TeamBug bug) {
		return team == bug.team;
	}

	// Your bug (this) kills the other bug (bugToBeKilled) if it can
	public final void kill(TeamBug bugToBeKilled) {
		if (canKill(bugToBeKilled)) {
			bugToBeKilled.removeSelfFromGrid();
			turnsToDeath = 0;
		}
	}

	public final void resurrect(TeamBug bugToResurrect) {
		bugToResurrect.turnsToDeath = -1;
	}

	// Heals the parameter TeamBug
	public final void heal(TeamBug bug) {
		bug.turnsToDeath = -1;
	}

	// Your bug infects the other bug so that it has i turns left before it dies
	public final void infect(TeamBug bug, int i) {
		bug.isInfected = true;
		bug.turnsToDeath = i;
	}

	// Your bug freezes the other bug so that it has i turns left before it can
	// move
	public final void freeze(TeamBug bug, int i) {
		bug.turnsToUnfreeze = i;
	}

	public void act() {
		super.act();
		breed();
	}

	// Breeds your bug, to be run during every act method
	public final void breed() {
		if (isSterile)
			return;
		int random = (int) (Math.random() * 10);
		if (random == 1) {
			isSterile = true;
			cloneSelf();
		}
	}

	public void processActors(ArrayList<Actor> actors) {
		for (Actor a : actors) {
			if (a instanceof TeamBug)
				interact((TeamBug) a);
		}
	}

	public final void makeMove(Location loc) {
		if (turnsToUnfreeze >= 0)
			turnsToUnfreeze--;
		if (turnsToDeath > 0)
			turnsToDeath--;
		if (turnsToDeath == 0) {
			removeSelfFromGrid();
			return;
		}
		if (loc == null) {
			removeSelfFromGrid();
			return;
		}
		if (turnsToUnfreeze >= 0)
			return;
		moveTo(loc);
	}

	public String toString() {
		return super.toString() + ", Team: " + team + ", isInfected: "
				+ isInfected + ", Turns to Death: " + turnsToDeath
				+ ", Turns to Unfreeze: " + turnsToUnfreeze;
	}

	// How your bug interacts with other bugs
	public abstract void interact(TeamBug bug);

	// Determines whether your bug (this) can kill the other bug (bugToKill)
	public abstract boolean canKill(TeamBug bugToKill);

	// Used in breeding, just make a new bug of the same type as yours in an
	// adjacent square
	/*
	 * Base code if (getGrid() != null) { ArrayList<Location> locs =
	 * getGrid().getEmptyAdjacentLocations( getLocation()); (new <INSERT YOUR
	 * CLASS>(getTeam(), true)).putSelfInGrid(getGrid(), locs.get((int)
	 * Math.random() * locs.size()));
	 */
	public abstract void cloneSelf();
}
