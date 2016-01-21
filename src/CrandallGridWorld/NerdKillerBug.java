package CrandallGridWorld;

import info.gridworld.actor.Actor;

import java.util.ArrayList;

public class NerdKillerBug extends NerdTeamBug {
	public NerdKillerBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		setColor(null);
	}

	public void act() {
		super.act();
		int rKill = (int) (Math.random() * 6);
		if (rKill == 1) {
			ArrayList<Actor> actors = this.getActors();
			for (Actor a : actors) {
				if (a instanceof TeamBug)
					interact((TeamBug) a);
				break;
			}
		}
	}

	@Override
	public void interact(TeamBug bug) {
		if (!sameTeam(bug)) {
			this.kill(bug);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		if (!sameTeam(bugToKill))
			return true;
		else if (bugToKill instanceof GeekAssassinBug)
			return false;
		else
			return false;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdKillerBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		if (!sameTeam(byWhom)) {
			return true;
		}
		return false;
	}
}
