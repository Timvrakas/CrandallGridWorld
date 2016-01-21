package CrandallGridWorld;


import java.util.Random;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class NerdPufferBug extends NerdTeamBug {
	
	public NerdPufferBug(int teamName, boolean hasBred) {
		super(teamName, hasBred);
		original = this;
		setColor(null);

	}
	public NerdPufferBug(int teamName, boolean hasBred, int turns) {
		super(teamName, hasBred);
		original = this;
		counter = turns;
		secondary = new NerdPuffedBug(teamName, hasBred);
		counter = TURNS_UNTIL_PUFF;
		setColor(null);

	}

	public int getCounter() {
		return counter;
	}

	public boolean canPuff() {
		if (counter == 0)
			return true;
		else
			return false;
	}

	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		NerdPufferBug bug = new NerdPufferBug(teamNum, true);
		return bug;
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return false;
	}

	public void count() {
		counter--;
	}
	public void setCounter(int num)
	{
		counter = num;
	}
	public boolean canKill(TeamBug bugToKill) {
		boolean canKill = true;
		Random generator = new Random();
		int n1 = generator.nextInt(2);
		if (n1 == 1 && canPuff()) {
			original.puff();
			canKill = false;
		}
		return canKill;
	}

	private void puff() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		secondary.putSelfInGrid(gr, original.getLocation());
		secondary.setDirection(original.getDirection());
	}

	public void deflate() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		original.putSelfInGrid(gr, secondary.getLocation());
		original.setDirection(secondary.getDirection());
	}

	@Override
	public void interact(TeamBug bug) {
	}

	private final static int TURNS_UNTIL_PUFF = 6;
	private static NerdPuffedBug secondary;
	private static NerdPufferBug original;
	private int counter;

}

