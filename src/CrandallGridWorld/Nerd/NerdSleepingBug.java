package CrandallGridWorld.Nerd;

import CrandallGridWorld.TeamBug;

public class NerdSleepingBug extends NerdTeamBug {

	public NerdSleepingBug(int tN, boolean hasBred) {
		super(tN, hasBred);
		int r = ((int) (Math.random() * 8)) * 45;
		setDirection(r);
	}

	@Override
	public void interact(TeamBug bug) {
		if (!sameTeam(bug) && !(getTurnsToUnfreeze() > 0)) {
			bug.freeze(5);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdSleepingBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

}
