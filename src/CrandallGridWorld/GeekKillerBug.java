package CrandallGridWorld;

import java.util.ArrayList;

import info.gridworld.actor.Actor;

public class GeekKillerBug extends TeamBug {

	int setTeam = 0;

	public GeekKillerBug(int team, boolean breedable) {
		super(team, breedable);
		setTeam = team;
	}

	public void processActors(ArrayList<Actor> actors) {
		a: for (Actor arc : actors) {
			if (arc instanceof TeamBug && !sameTeam((TeamBug) arc)) {
				kill((TeamBug) arc);
				// arc.removeSelfFromGrid();
				break a;
			}

		}
	}

	@Override
	public void interact(TeamBug bug) {

	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new GeekKillerBug(setTeam, true);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}

}
