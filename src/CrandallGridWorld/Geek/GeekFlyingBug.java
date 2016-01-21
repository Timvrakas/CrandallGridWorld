package CrandallGridWorld.Geek;

import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class GeekFlyingBug extends TeamBug {

	public GeekFlyingBug(int n, Boolean hasBred) {
		super(n, hasBred);
		setColor(null);
	}

	public void act() {
		if (canJump())
			jump();
		else
			turn();
	}

	public void interact(TeamBug bug) {
		if (canKill(bug))
			kill(bug);
	}

	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}

	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new GeekFlyingBug(teamNum, hasBred);
	}

	public void turn() {
		setDirection(getDirection() + Location.HALF_RIGHT);
	}

	public void jump() {
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location twoAway = next.getAdjacentLocation(getDirection());
		Location threeAway = twoAway.getAdjacentLocation(getDirection());
		Location fourAway = threeAway.getAdjacentLocation(getDirection());
		Location fiveAway = fourAway.getAdjacentLocation(getDirection());
		Location sixAway = fiveAway.getAdjacentLocation(getDirection());
		if (sixAway != null && getGrid().isValid(sixAway))
			makeMove(sixAway);
		else
			removeSelfFromGrid();
	}

	public boolean canJump() {
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
}
