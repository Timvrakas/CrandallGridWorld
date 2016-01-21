package CrandallGridWorld;


public class NerdVirusBug extends NerdTeamBug {

	public NerdVirusBug(int tN, boolean hasBred) {
		super(tN,hasBred);
		int r = ((int) (Math.random() * 8)) * 45;
		setDirection(r);
	}

	@Override
	public void interact(TeamBug bug) {
		if (!sameTeam(bug) && !isInfected()) {
			bug.infect(4);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdVirusBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

}

