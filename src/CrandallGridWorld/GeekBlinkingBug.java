package CrandallGridWorld;

import java.util.ArrayList;
import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class GeekBlinkingBug extends TeamBug {
	public GeekBlinkingBug(int team, boolean hasBred) {
		super(team, hasBred);
		generator = new Random();
	}

	// How your bug interacts with other bugs
	public void interact(TeamBug bug) {
		return;
	}

	// Determines whether your bug (this) can kill the other bug (bugToKill)
	public boolean canKill(TeamBug bugToKill) {
		return true;
	}

	// Used in breeding, just make a new bug of the same type as yours in an
	// adjacent square

	public Location selectMoveLocation() {
		boolean done = false;
		Location l = null;
		while (!done) {
			l = new Location(generator.nextInt(getGrid().getNumRows()), generator.nextInt(getGrid().getNumCols()));
			if (getGrid().isValid(l) && getGrid().get(l) == null) {
				done = true;
			}
		}
		return l;
	}

	public ArrayList<Actor> getActors() {
		ArrayList<Actor> a = getGrid().getNeighbors(getLocation());
		ArrayList<Actor> b = new ArrayList<Actor>();
		for (Actor x : a) {
			if (x instanceof TeamBug) {
				b.add(x);
			}
		}
		return b;
	}

	public void processActors(ArrayList<Actor> actors) {
		if (actors != null) {
			int x = actors.size() / 2;
			while (actors.size() > x) {
				int y = generator.nextInt(actors.size());
				kill((TeamBug) actors.get(y));
				actors.remove(y);
			}
		}
	}

	public void act() {
		if (getGrid() == null)
			return;
		ArrayList<Actor> actors = getActors();
		processActors(actors);
		Location loc = selectMoveLocation();
		makeMove(loc);
	}

	private Random generator;

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		// TODO Auto-generated method stub
		return (new GeekBlinkingBug(teamNum, hasBred));
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		// TODO Auto-generated method stub
		return false;
	}

}
