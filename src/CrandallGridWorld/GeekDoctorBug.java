package CrandallGridWorld;

public class GeekDoctorBug extends TeamBug {

	public GeekDoctorBug(int num, boolean hasBred) {
		super(num, hasBred);
	}

	@Override
	public void interact(TeamBug bug) {
		if (sameTeam(bug) && bug.isInfected())
			bug.heal();
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new GeekDoctorBug(getTeam(), true);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}
}
