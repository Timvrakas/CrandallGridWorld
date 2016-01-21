package CrandallGridWorld;

import java.util.ArrayList;

import info.gridworld.actor.Actor;

public class GeekSleepingBug extends TeamBug {

	public GeekSleepingBug(int num, boolean bred) {
		super(num, bred);
	}

	public boolean canKill(TeamBug bugToKill) {
		return false;
	}

	public void interact(TeamBug bug) {
		moves++;
		ArrayList<Actor> iceCube = getActors();
		if (moves == 5) {
			if (iceCube.size() > 0)
				for (Actor a : iceCube) {
					if (a instanceof TeamBug && !(sameTeam(bug))) {
						((TeamBug) a).freeze(15);
					}
				}
			moves = 0;
		}
	}

	private int moves;

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		GeekSleepingBug shyam = new GeekSleepingBug(teamNum, hasBred);
		return shyam;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return false;
	}
}
