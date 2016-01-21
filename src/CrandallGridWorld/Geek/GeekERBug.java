package CrandallGridWorld.Geek;

import CrandallGridWorld.TeamBug;

public class GeekERBug extends TeamBug {
	public GeekERBug(int teamNum, boolean hasBred) {
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
	
		return false;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return (new GeekERBug(teamNum, hasBred));
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		
		return true;
	}
}
