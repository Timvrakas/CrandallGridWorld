package CrandallGridWorld;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

//Version 1.1.0
//@Author Suchir/Billy/Tim, and everyone who helped figure this out on the whiteboard
//Any edits/improvements are appreciated
//Not sure if we need final for the methods, but it seemed useful.

public abstract class TeamBug extends Critter {
	private int team;
	private int turnsToUnfreeze;
	private int turnsToDeath;
	private boolean isSterile;
	private boolean isInfected;
	private boolean canKill;
	
	public TeamBug child;

	public static final int GEEK = 1;
	public static final int NERD = 2;

	// Remember, your constuctors need to have a class-type Integer,Boolean
	// Constuctor

	public TeamBug(int teamNum, boolean hasBred) {
		super();
		team = teamNum;
		isSterile = hasBred;
		turnsToDeath = -1;
	}

	public abstract TeamBug makeNewBug(Integer teamNum, Boolean hasBred);

	// How your bug interacts with other bugs
	// Called once for each bug in the area around you
	public abstract void interact(TeamBug bug);

	// Determines whether your bug (this) can kill the other bug (bugToKill)
	public abstract boolean canKill(TeamBug bugToKill);

	// Used in breeding, just make a new bug of the same type as yours in an
	// adjacent square
	public void cloneSelf() {
		TeamBug child;
		ArrayList<Location> validLoc = getGrid().getEmptyAdjacentLocations(getLocation());
		if (validLoc.size() > 0) {
			Location newLoc = validLoc.get((int) (Math.random() * validLoc.size()));
			child = makeNewBug(team, true);
			child.putSelfInGrid(getGrid(), newLoc);
			this.child=child;
		}
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
	public void setSterile(){
		isSterile = true;
	}
	// Determines if the two bugs are on the same team
	public boolean sameTeam(TeamBug bug) {
		return team == bug.team;
	}

	// Your bug (this) kills the other bug (bugToBeKilled) if it can
	public final void kill(TeamBug bugToBeKilled) {
		if (canKill(bugToBeKilled) && bugToBeKilled.canBeKilled(this)) {
			bugToBeKilled.removeSelfFromGrid();
			bugToBeKilled.turnsToDeath = 0;
		}
	}

	public final boolean isDead() {
		return (turnsToDeath == 0);
	}

	public abstract boolean canBeKilled(TeamBug byWhom);

	// Heals the implicit TeamBug
	public final void heal() {
		turnsToDeath = -1;
	}

	// The bug is infected and countdown to death begins
	public final void infect(int i) {
		isInfected = true;
		if(turnsToDeath < 0){
		turnsToDeath = i;
		}
	}

	// Your bug cannot move for i turns
	public final void freeze(int i) {
		turnsToUnfreeze = i;
	}

	// Breeds your bug, to be run during every act method
	public final void breed() {
		if (isSterile)
			return;
		int random = (int) (Math.random() * 10);
		if (random == 3) {
			cloneSelf();
			isSterile = true;
		}
	}

	public void processActors(ArrayList<Actor> actors) {
		for (Actor a : actors) {
			if (a instanceof TeamBug)
				interact((TeamBug) a);
		}
	}

	public final void makeMove(Location loc) {
		turnsToUnfreeze--;
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

	public void act() {
		super.act();
		breed();
	}

	public int getTeam() {
		return team;
	}

	public String toString() {
		return super.toString() + ", team=" + team + ", isInfected=" + isInfected + ", turnsToDeath=" + turnsToDeath
				+ ", turnsToUnfreeze=" + turnsToUnfreeze + ", isSterile=" + isSterile;
	}
}
