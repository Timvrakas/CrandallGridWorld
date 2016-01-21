package CrandallGridWorld.Nerd;
import java.util.ArrayList;

import CrandallGridWorld.TeamBug;
import CrandallGridWorld.Geek.GeekKillerBug;
import info.gridworld.actor.Actor;

public class NerdAssassinBug extends NerdTeamBug {
    // 1 = geek, 2 = nerd
    private boolean bred;
    private int kills;

    public NerdAssassinBug(int teamNum, boolean hasBred) {
   	 super(teamNum, hasBred);
   	 bred = hasBred;
   	 setColor(null);
    }

    @Override
    public void interact(TeamBug bug) {
   	 // Does nothing to other bugs   	 
    }

    @Override
    public boolean canKill(TeamBug bugToKill) {
   	 if (bugToKill instanceof GeekKillerBug) {
   		 return true;
   	 }
   	 return false;
    }
    
    public void processActors(ArrayList<Actor> actors) {
   	int n = actors.size();
    	if (n == 0) {
   		 return;
    	}
    	int r = (int) (Math.random() * n);

    	Actor other = actors.get(r);
   	 
    	if (other instanceof GeekKillerBug && getTeam() == 1) {
   		 other.removeSelfFromGrid();
   		 kills++;
    	}
}
    
    public boolean hasBred() {
   	 return bred;
    }
    
    public int getKillCount() {
   	 return kills;
    }

    @Override
    public TeamBug makeNewBug(Integer teamNum, Boolean hasBred) {
   	 return new NerdAssassinBug(2, true);
    }

    @Override
    public boolean canBeKilled(TeamBug byWhom) {
   	 if (byWhom instanceof GeekKillerBug || byWhom instanceof NerdTeamBug) {
   		 return false;
   	 }
   	 else {
   		 return true;
   	 }
    }
}
