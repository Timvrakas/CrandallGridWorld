package CrandallGridWorld.Geek;

import java.util.ArrayList;

import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;

public class GeekDynamiteBug extends TeamBug{
	

	public GeekDynamiteBug(int num, boolean bred) {
		super(num, bred);
	}
	
	public boolean canKill(TeamBug bugToKill){
		if(getTurnsToDeath()==0)
			return true;
		return false;
	}
	public void interact(TeamBug bug) {
	
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new GeekDynamiteBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}
	public void removeSelfFromGrid() {
		ArrayList<Actor> dynamite = getActors();
		for(Actor a: dynamite) {
			if(a instanceof TeamBug) {
				kill((TeamBug) a);
			}
		}
		super.removeSelfFromGrid();
	}
	
	
}
