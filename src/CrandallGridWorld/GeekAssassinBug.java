package CrandallGridWorld;

public class GeekAssassinBug extends TeamBug {
	public GeekAssassinBug(int teamNum, boolean hasBred) {
		super(teamNum, hasBred);
	}

	@Override
	public void interact(TeamBug bug) {
		if (canKill(bug))
			kill(bug);
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		if (sameTeam(bugToKill))
			return false;
		if (bugToKill instanceof NerdKillerBug)
			return true;
		return false;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return (new GeekAssassinBug(teamNum, hasBred));
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		if (byWhom instanceof NerdKillerBug)
			return false;
		return true;
	}
}
