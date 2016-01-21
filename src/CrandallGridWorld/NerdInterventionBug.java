package CrandallGridWorld;


public class NerdInterventionBug extends NerdTeamBug {

	public NerdInterventionBug(int tN, boolean hasBred) {
		super(tN,hasBred);
		int r = ((int) (Math.random() * 8)) * 45;
		setDirection(r);
		setColor(null);
	}

	@Override
	public void interact(TeamBug bug) {
		if (!sameTeam(bug) && bug instanceof GeekCultBug) {
			kill(bug);
		}
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new NerdInterventionBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

}

