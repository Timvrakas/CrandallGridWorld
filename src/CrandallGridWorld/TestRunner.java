package CrandallGridWorld;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class TestRunner {
	public static void main(String[] args) {

		ActorWorld world = new ActorWorld();
		Grid<Actor> grid = new BoundedGrid<Actor>(50, 50);
		world.setGrid(grid);
		NerdCultBug bug1 = new NerdCultBug(2, false);
		NerdBlinkingBug bug2 = new NerdBlinkingBug(2, false);
		NerdInterventionBug bug3 = new NerdInterventionBug(2, false);
		//NerdAssassinBug bug4 = new NerdAssassinBug(2, false);
		//NerdKillerBug bug5 = new NerdKillerBug(2, false);
		NerdFlyingBug bug6 = new NerdFlyingBug(2, false);
		//NerdGhostBug bug7 = new NerdGhostBug(2, false, 3);
		//NerdPufferBug bug8 = new NerdPufferBug(2, false);
		//NerdSleepingBug bug9 = new NerdSleepingBug(2, false);
		NerdVirusBug bug10 = new NerdVirusBug(2, false);
		//NerdCrandallBug bug11 = new NerdCrandallBug(2, false);

		world.add(new Location(0, 0), bug1);
		world.add(new Location(24, 24), bug2);
		world.add(new Location(2, 0), bug3);
		//world.add(new Location(0, 3), bug4);
		//world.add(new Location(0, 4), bug5);
		world.add(new Location(3, 0), bug6);
		//world.add(new Location(0, 6), bug7);
		//world.add(new Location(0, 7), bug8);
		//world.add(new Location(0, 8), bug9);
		world.add(new Location(4, 0), bug10);
		//world.add(new Location(0, 10), bug11);
	
		
		GeekKillerBug test1 = new GeekKillerBug(1, false);
		GeekInterventionBug test2 = new GeekInterventionBug(1, false);
		GeekDynamiteBug test3 = new GeekDynamiteBug(1, false);
		GeekCultBug test4 = new GeekCultBug(1, false);
		GeekVirusBug test5 = new GeekVirusBug(1, false);
		world.add(new Location(0, 49), test1);
		world.add(new Location(1, 49), test2);
		world.add(new Location(2, 49), test3);
		world.add(new Location(3, 49), test4);
		world.add(new Location(4, 49), test5);
//		GeekKillerBug test6 = new GeekKillerBug(1, false);
//		GeekKillerBug test7 = new GeekKillerBug(1, false);
//		GeekKillerBug test8 = new GeekKillerBug(1, false);
//		GeekKillerBug test9 = new GeekKillerBug(1, false);
//		GeekKillerBug test10 = new GeekKillerBug(1, false);
//		world.add(new Location(49, 5), test6);
//		world.add(new Location(49, 6), test7);
//		world.add(new Location(49, 7), test8);
//		world.add(new Location(49, 8), test9);
//		world.add(new Location(49, 9), test10);
//		GeekKillerBug test11 = new GeekKillerBug(1, false);
//		GeekKillerBug test12 = new GeekKillerBug(1, false);
//		GeekKillerBug test13 = new GeekKillerBug(1, false);
//		GeekKillerBug test14 = new GeekKillerBug(1, false);
//		GeekKillerBug test15 = new GeekKillerBug(1, false);
//		world.add(new Location(49, 10), test11);
//		world.add(new Location(49, 11), test12);
//		world.add(new Location(49, 12), test13);
//		world.add(new Location(49, 13), test14);
//		world.add(new Location(49, 14), test15);
		world.show();
	}
}
