package CrandallGridWorld;

import java.util.ArrayList;

import info.gridworld.actor.Actor;

public class GeekFreezerBug extends TeamBug {

	public GeekFreezerBug(int num, boolean bred) {
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
						((TeamBug) a).freeze(4);
					}
				}
			moves = 0;
		}
	}

	private int moves;

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		GeekFreezerBug shyam = new GeekFreezerBug(teamNum, hasBred);
		return shyam;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return false;
	}
}
