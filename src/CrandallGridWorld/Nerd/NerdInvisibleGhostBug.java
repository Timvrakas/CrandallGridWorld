package CrandallGridWorld.Nerd;

import CrandallGridWorld.TeamBug;

public class NerdInvisibleGhostBug extends NerdGhostBug {
	public final int TURNS_INVISIBLE = 3;

	/**
	 * Constructs a new InvisbleGhostBug.
	 */
	public NerdInvisibleGhostBug(int teamName, boolean hasBred, NerdGhostBug b) {
		super(teamName, hasBred);
		setCounter(TURNS_INVISIBLE);
		setColor(null);
		setBug(b);
	}

	/**
	 * Acts-checks if it is time to be invisible.
	 */
	public void act() {
		count();
		if (getCounter() == 0) {
			setCounter(getTurnsUntilInvisible());
			turnVisible();
		} else {
			super.act(0);
		}
	}

	/**
	 * Returns whether this bug can be killed by a certain bug.
	 * 
	 * @return whether this bug can be killed
	 */
	public boolean canBeKilled(TeamBug byWhom) {
		return false;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		getBug().setSterile();
		NerdGhostBug bug = new NerdGhostBug(teamNum, true, getTurnsUntilInvisible());
		return bug;
	}

	@Override
	public void interact(TeamBug bug) {
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return false;
	}

}


