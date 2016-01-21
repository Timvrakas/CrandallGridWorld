package CrandallGridWorld.Geek;



import java.util.ArrayList;

import CrandallGridWorld.TeamBug;

public abstract class GeekTeamBug extends TeamBug{

	private static ArrayList<GeekTeamBug> fam;
	
	public GeekTeamBug(Integer teamNum, Boolean hasBred) {
		super(teamNum, hasBred);
		addToTeam();
	}
	
	protected ArrayList<GeekTeamBug> getTeamList(){
		return fam;
	}
	
	private void addToTeam(){
		if(fam == null){
			fam = new ArrayList<GeekTeamBug>();
		}
		fam.add(this);
	}
}
