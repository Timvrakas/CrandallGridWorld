package CrandallGridWorld;

import java.util.*;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class BugFinder {

	public static ArrayList<Location> getAllBugs(String bugType, Grid grid) {
		ArrayList<Location> locs2 = new ArrayList<Location>();
		ArrayList<Location> locs = grid.getOccupiedLocations();
		for (Location e : locs) {
			if (grid.get(e).getClass().getName().endsWith(bugType)) {
				locs2.add(e);

			}

		}
		return locs2;
	}
}