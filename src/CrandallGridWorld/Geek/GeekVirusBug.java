package CrandallGridWorld.Geek;

import java.util.ArrayList;

import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;

public class GeekVirusBug extends TeamBug {
	// VirusBug Yellow- If it gets next to another bug, it infects them with a
	// virus
	// that kills them after four (4) moves.

	public GeekVirusBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
	}

	@Override
	public boolean canKill() {
		return true;
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		if (sameTeam(bugToKill)) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void act() {
		if (getTurnsToDeath() == 0) {
			removeSelfFromGrid();
		}
		super.act();
	}

	public void processActors(ArrayList<Actor> actors) {
		ArrayList<TeamBug> prepareToDie = new ArrayList<TeamBug>();
		for (Actor a : actors) {
			if (a instanceof TeamBug && canKill((TeamBug) a)) {
				prepareToDie.add((TeamBug) a);
			}
			if (prepareToDie.size() == 0) {
				return;
			}
			for (int i = 0; i < prepareToDie.size(); i++) {
				interact(prepareToDie.get(i));
			}
		}
	}

	@Override
	public void interact(TeamBug bug) {
		infect(4);
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new GeekVirusBug(2, true);

	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}

}
