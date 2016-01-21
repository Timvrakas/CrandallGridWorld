package CrandallGridWorld.Nerd;


import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;

public class NerdGhostBug extends NerdTeamBug {
	private static int turnsUntilInvisible;
	private int counter;
	private NerdInvisibleGhostBug ghost;
	private NerdGhostBug original;

	/**
	 * Constructs a new GhostBug.
	 * 
	 * @param the
	 *            amt of turns until the bug becomes invisible
	 */
	public NerdGhostBug(int teamName, boolean hasBred) {
		super(teamName, hasBred);
		original = this;
		setColor(null);
	}

	/**
	 * Constructs a new GhostBug.
	 * 
	 * @param the
	 *            amt of turns until the bug becomes invisible
	 */
	public NerdGhostBug(int teamName, boolean hasBred, int turns) {
		super(teamName, hasBred);
		turnsUntilInvisible = turns;
		counter = turns;
		ghost = new NerdInvisibleGhostBug(getTeam(), hasBred, this);
		original = this;
		setColor(null);
	}

	/**
	 * Returns turns until the bug becomes invisible.
	 * 
	 * @return amt of turns until the bug becomes invisible
	 */
	public static int getTurnsUntilInvisible() {
		return turnsUntilInvisible;
	}

	/**
	 * Returns the counter for keeping track of whether the bug is visible or
	 * not.
	 * 
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Returns whether the bug can be killed.
	 * 
	 * @return true if the bug can be killed
	 */
	@Override
	public boolean canKill(TeamBug bugToKill) {
		return false;
	}

	/**
	 * Sets the counter to a value.
	 * 
	 * @param count
	 *            the number the counter is being set to
	 */
	public void setCounter(int count) {
		counter = count;
	}

	/**
	 * Decrements the counter.
	 */
	public void count() {
		counter--;
	}

	/**
	 * Makes the bug invisible
	 * 
	 * Precondition: The bug has to be visible.
	 */
	private void turnInvisible() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		ghost.putSelfInGrid(gr, getLocation());
		ghost.setDirection(getDirection());
	}

	/**
	 * Makes the bug visible.
	 * 
	 * Precondition: The bug has to be invisible.
	 */
	public void turnVisible() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		original.putSelfInGrid(gr, this.getLocation());
		original.setDirection(this.getDirection());
	}

	/**
	 * Interacts with other bugs.
	 */
	@Override
	public void interact(TeamBug bug) {
	}

	/**
	 * Acts-checks if it is time to be invisible.
	 */
	public void act() {
		counter--;
		if (counter == 0) {
			counter = turnsUntilInvisible;
			turnInvisible();
		} else {
			super.act();
		}
	}

	public void act(int bleh) {
		super.act();
	}

	/**
	 * Creates a new bug of this type and returns it.
	 * 
	 * @return the bug that is constructed
	 */
	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		ghost.setSterile();
		NerdGhostBug bug = new NerdGhostBug(teamNum, true, getTurnsUntilInvisible());
		return bug;
	}

	/**
	 * Returns whether this bug can be killed by a certain bug.
	 * 
	 * @return whether this bug can be killed
	 */
	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}

	public void setBug(NerdGhostBug b) {
		original = b;
	}

	public NerdGhostBug getBug() {
		return original;
	}
}




