package CrandallGridWorld.Nerd;

import CrandallGridWorld.TeamBug;

public class NerdElectricBug extends NerdTeamBug {
	private int counter = 0;

	public NerdElectricBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
		setColor(null);
	}

	public void act() {
		super.act();
		counter++;
		if (counter == 10) {
			counter = 0;
		}
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdElectricBug(teamNum, hasBred);
	}

	@Override
	public void interact(TeamBug bug) {
		if (!bug.sameTeam(bug)) {
			this.kill(bug);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

}
