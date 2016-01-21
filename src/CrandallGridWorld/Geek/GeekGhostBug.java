package CrandallGridWorld.Geek;

import java.awt.Color;

import CrandallGridWorld.TeamBug;

public class GeekGhostBug extends TeamBug {

	private int moves;
	private boolean canBeKilled;

	public GeekGhostBug(int teamNum, boolean hasBred, boolean kill) {
		super(teamNum, hasBred);
		canBeKilled = kill;
		setColor(Color.DARK_GRAY);
		// TODO Auto-generated constructor stub
	}

	public void act() {
		moves++;
		if (moves > 1) {
			setColor(Color.GRAY);
			canBeKilled = false;
			if (moves > 15) {
				moves = 0;
				canBeKilled = true;
				setColor(Color.DARK_GRAY);
			}
		}
		super.act();
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return new GeekGhostBug(getTeam(), true, canBeKilled);
	}

	@Override
	public void interact(TeamBug bug) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return canBeKilled;
	}

}
