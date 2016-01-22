package CrandallGridWorld.Nerd;

import java.util.ArrayList;
import java.util.Random;

import CrandallGridWorld.TeamBug;
import CrandallGridWorld.Geek.GeekAssassinBug;
import info.gridworld.actor.Actor;

public class NerdKillerBug extends NerdTeamBug {

	public NerdKillerBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		setColor(null);
	}

	@Override
	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Actor> toKill = new ArrayList<Actor>();
		for (Actor a : actors) {
			if (a instanceof TeamBug && ((TeamBug) a).canBeKilled(this) && this.canKill((TeamBug) a)){
				toKill.add(a);
			}
		}
		interact((TeamBug) toKill.get(new Random().nextInt(toKill.size())));
	}

	@Override
	public void interact(TeamBug bug) {
		this.kill(bug);
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		if (bugToKill instanceof GeekAssassinBug)
			return false;
		if (sameTeam(bugToKill))
			return false;
		else
			return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdKillerBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		if (sameTeam(byWhom)) {
			return false;
		}
		return true;
	}
}
