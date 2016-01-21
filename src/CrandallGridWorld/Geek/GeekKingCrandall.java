package CrandallGridWorld.Geek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.zip.DataFormatException;

import CrandallGridWorld.TeamBug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

public class GeekKingCrandall extends TeamBug {
	private int moves;
	private static HashSet<TeamBug> bugs = new HashSet<TeamBug>();
	private static HashSet<TeamBug> teamBugs = new HashSet<TeamBug>();
	private static HashSet<Actor> deadBugs = new HashSet<Actor>();
	private static HashSet<TeamBug> teamDeadBugs = new HashSet<TeamBug>();

	public GeekKingCrandall(int num, boolean hasBred) {
		super(num, hasBred);
	}

	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Location> curBugs = getGrid().getOccupiedLocations();

		// Adds all new bugs and registers teamBugs
		for (Location loc : curBugs) {
			if (getGrid().get(loc) instanceof TeamBug) {
				bugs.add((TeamBug) getGrid().get(loc));
				if (sameTeam((TeamBug) getGrid().get(loc))) {
					teamBugs.add((TeamBug) getGrid().get(loc));
				}
			}
		}

		// finds the differences between this and
		// last turns bugs
		for (TeamBug cur : bugs) {
			if (!curBugs.contains(cur.getLocation())) {
				deadBugs.add(cur);
				if (sameTeam(cur)) {
					teamDeadBugs.add(cur);
				}
			}
		}

		System.out.println(bugs.size() + ", " + teamBugs.size() + ", " + deadBugs.size() + ", " + teamDeadBugs.size());
		moves++;
		if (moves >= 3) {
			int curRev = 0;
			// deadBugs for total or teamDeadBugs for team
			int maxRev = (int) Math.ceil(teamDeadBugs.size() / 2.0);
			int rowMin = getLocation().getRow();
			int colMin = getLocation().getCol();

			for (Iterator<TeamBug> itr = teamDeadBugs.iterator(); itr.hasNext();) {
				if (curRev >= maxRev) {
					moves = 0;
					return;
				}
				TeamBug b = itr.next();
				if (b instanceof GeekKingCrandall) {
					try {
						b.putSelfInGrid(getGrid(), getSpawn(rowMin, colMin));
						b.heal();
						deadBugs.remove(b);
						itr.remove();
						System.out.println(b.getLocation() + "is a revived " + b.getClass() + "on team " + b.getTeam());
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
					curRev++;
				}
			}
			int element = -1;
			for (Iterator<TeamBug> itr = teamDeadBugs.iterator(); itr.hasNext();) {
				if (curRev >= maxRev) {
					moves = 0;
					return;
				}
				TeamBug b = itr.next();
				element++;
				if (element % 2 == 0) {
					try {
						b.putSelfInGrid(getGrid(), getSpawn(rowMin, colMin));
						b.heal();
						deadBugs.remove(b);
						itr.remove();
						System.out.println(b.getLocation() + "is a revived " + b.getClass() + "on team " + b.getTeam());
					} catch (DataFormatException e) {
						e.printStackTrace();
					}
					curRev++;
				}
			}

			// For the other team
			// for (Iterator<Actor> itr = deadBugs.iterator(); itr.hasNext();) {
			// if (curRev >= maxRev) {
			// moves = 0;
			// return;
			// }
			// Actor a = itr.next();
			// if (curRev % 2 == 0) {
			// if (a instanceof TeamBug) {
			// TeamBug b = (TeamBug) a;
			// try {
			// b.putSelfInGrid(getGrid(), getSpawn(rowMin, colMin));
			// resurrect(b);
			// itr.remove();
			// System.out.println(b.getLocation());
			// } catch (DataFormatException e) {
			// e.printStackTrace();
			// }
			// curRev++;
			// }
			// }
			// }
			moves = 0;
		}
	}

	public Location selectMoveLocation(ArrayList<Location> locs) {
		ArrayList<Location> enemy = getGrid().getOccupiedLocations();
		for (int i = 0; i < enemy.size(); i++) {
			Actor a = getGrid().get(enemy.get(i));
			if (!(a instanceof TeamBug))
				continue;
			TeamBug b = (TeamBug) a;
			if (sameTeam(b))
				enemy.remove(i);
		}

		Location close = null;
		double distance = -1;
		for (Location l : enemy) {
			double curDistance = Math.sqrt((l.getRow() - getLocation().getRow()) * (l.getRow() - getLocation().getRow())
					+ (l.getCol() - getLocation().getCol()) * (l.getCol() - getLocation().getCol()));
			if (close == null || distance > curDistance) {
				distance = curDistance;
				close = l;
			}
		}
		Location move = getLocation()
				.getAdjacentLocation(getLocation().getDirectionToward(close) + Location.HALF_CIRCLE);
		if (!getGrid().isValid(move) || (getGrid().get(move) instanceof Actor)) {
			ArrayList<Location> moves = getGrid().getEmptyAdjacentLocations(getLocation());
			move = null;
			double maxDis = -1;
			for (Location l : moves) {
				double curDistance = Math
						.sqrt((l.getRow() - getLocation().getRow()) * (l.getRow() - getLocation().getRow())
								+ (l.getCol() - getLocation().getCol()) * (l.getCol() - getLocation().getCol()));
				if (move == null || maxDis > curDistance) {
					maxDis = curDistance;
					move = l;
				}
			}
		}
		return move == null ? super.selectMoveLocation(locs) : move;
	}

	private Location getSpawn(int rowMin, int colMin) throws DataFormatException {
		if (getGrid().getOccupiedLocations().size() >= getGrid().getNumCols() * getGrid().getNumRows()) {
			throw new DataFormatException("There is not enough space in the grid to spawn.");
		}
		if (rowMin < getGrid().getNumRows() / 2.0) {
			int row = (int) (getGrid().getNumRows() - (Math.random() * (getGrid().getNumRows() - rowMin)));
			int col;
			if (row == rowMin) {
				col = (int) (getGrid().getNumCols() - (Math.random() * (getGrid().getNumCols() - colMin)));
			} else {
				col = (int) (Math.random() * getGrid().getNumCols());
			}
			if (!getGrid().isValid(new Location(row, col)))
				getSpawn(rowMin, colMin);
			return getGrid().get(new Location(row, col)) == null ? new Location(row, col) : getSpawn(rowMin, colMin);
		} else {
			int row = (int) (Math.random() * getGrid().getNumRows());
			int col = (int) (Math.random() * getGrid().getNumCols());
			if (!getGrid().isValid(new Location(row, col)))
				getSpawn(rowMin, colMin);
			return getGrid().get(new Location(row, col)) == null ? new Location(row, col) : getSpawn(rowMin, colMin);
		}
	}

	@Override
	public void interact(TeamBug bug) {
		return;
	}

	@Override
	public boolean canKill(TeamBug bugToKill) {
		return false;
	}

	@Override
	public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
		return new GeekKingCrandall(teamNum, hasBred);
	}

	@Override
	public boolean canBeKilled(TeamBug byWhom) {
		return true;
	}

}
