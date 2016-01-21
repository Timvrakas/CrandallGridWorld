package CrandallGridWorld;

import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

//Version 1.02
//@Author Suchir/Billy, and everyone who helped figure this out on the whiteboard
//Any edits/improvements are appreciated
//Not sure if we need final for the methods, but it seemed useful
public class GeekJumperBug extends TeamBug {
	private int numKills;

	public GeekJumperBug() {
		super(GEEK, false);
		this.setColor(null);
		numKills = 0;
		this.setDirection(0);
	}

	public GeekJumperBug(int teamNum, boolean hasBreed) {
		super(teamNum, hasBreed);
	}

	public void pickNewDirection() {
		Random newNumN = new Random();
		int dirNum = newNumN.nextInt(4) * 90;

		this.setDirection(dirNum);

	}

	@Override
	public void interact(TeamBug bug) {
		if (canKill(bug)) {
			kill(bug);
			numKills++;
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	public int getNumKills() {
		return numKills;
	}

	public void act() {
		// super.act();
		// breed();

		if (canJump()) {
			jump();
		} else {
			pickNewDirection();
		}
		breed();

	}

	private void jump() {
		Location newLocFinal = null;
		Location JumpStart = this.getLocation();

		Location startLoc = JumpStart;
		Location newLoc1 = startLoc.getAdjacentLocation(getDirection());
		Location newLoc2 = newLoc1.getAdjacentLocation(getDirection());
		Location newLoc3 = newLoc2.getAdjacentLocation(getDirection());
		Location newLoc4 = newLoc3.getAdjacentLocation(getDirection());
		Location newLoc5 = newLoc4.getAdjacentLocation(getDirection());
		newLocFinal = newLoc5.getAdjacentLocation(getDirection());

		if (newLocFinal != null && getGrid().isValid(newLocFinal)) {

			makeMove(newLocFinal);
		} else {
			this.pickNewDirection();
		}

	}

	private boolean canJump() {
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location twoAway = next.getAdjacentLocation(getDirection());
		Location threeAway = twoAway.getAdjacentLocation(getDirection());
		Location fourAway = threeAway.getAdjacentLocation(getDirection());
		Location fiveAway = fourAway.getAdjacentLocation(getDirection());
		Location sixAway = fiveAway.getAdjacentLocation(getDirection());
		if (sixAway == null || !getGrid().isValid(sixAway))
			return false;

		Actor neighbor = getGrid().get(sixAway);
		return (neighbor == null || !sameTeam((TeamBug) neighbor));

	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		GeekJumperBug newBug = new GeekJumperBug(GEEK, true);

		return newBug;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}
}
