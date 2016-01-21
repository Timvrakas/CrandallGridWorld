package CrandallGridWorld;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.Random;



public class NerdCultBug extends NerdTeamBug{

	public NerdCultBug( int teamNumber, boolean canBreed)
	{
		super(teamNumber,canBreed);
		setColor(null);
	}
	
	@Override
	public void act()
	{
		super.act();

		ArrayList<Actor> targets =super.getActors();
	
		for (Actor x :targets)
		{
			TeamBug z = (TeamBug)x;
			if(sameTeam(z)|| z instanceof GeekInterventionBug)
			{
				return;
			}
			else {
				Grid<Actor> gr = getGrid();
				Location imAt = z.getLocation();
				x.removeSelfFromGrid();
				NerdCultBug clonedCultBug = new NerdCultBug(2,true);
				clonedCultBug.putSelfInGrid(gr, imAt);
			}
		}
		
	}
	
	@Override
	public void interact(TeamBug bug) {
		
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		// TODO Auto-generated method stub
		return false;
	}

	

	

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
	return new NerdCultBug(2, true);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}



}
