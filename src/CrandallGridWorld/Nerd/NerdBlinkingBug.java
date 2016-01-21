package CrandallGridWorld.Nerd;

import java.util.ArrayList;
import java.util.Random;

import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class NerdBlinkingBug extends NerdTeamBug {
	public NerdBlinkingBug(int team, boolean hasBred) {
		super(team, hasBred);
		generator = new Random();
	}

	public Location selectMoveLocations() {
		Location newLoc = null;
		boolean done = false;
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return newLoc;
		while (!done) {
			newLoc = new Location(generator.nextInt(getGrid().getNumCols()), generator.nextInt(getGrid().getNumRows()));
			done = gr.isValid(newLoc) && gr.get(newLoc) == null;
		}
		return newLoc;
	}

	@Override
	public void act() {
		if (getGrid() == null)
			return;

		ArrayList<Actor> actors = getActors();
		processActors(actors);
		Location loc = selectMoveLocations();
		makeMove(loc);
		breed();
	}

	@Override
	public void processActors(ArrayList<Actor> allActors) 
	{
		ArrayList<Actor> actorsToKill = new ArrayList<Actor>();
		int count = 0;
		for (Actor a : allActors) 
		{
			if (a instanceof TeamBug) 
			{
				count++;
				actorsToKill.add(a);
			}
		}

		int x = count / 2;
		for (int i = 0; i < x; i++) 
		{
			int a = generator.nextInt(actorsToKill.size());
			Actor bug = actorsToKill.get(a);
			if (bug instanceof TeamBug) 
			{
				kill((TeamBug) bug);
				actorsToKill.remove(a);
			}
		}
	}

	@Override
	public void interact(TeamBug bug) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return new NerdBlinkingBug(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return true;
	}

	public Random generator;
}



