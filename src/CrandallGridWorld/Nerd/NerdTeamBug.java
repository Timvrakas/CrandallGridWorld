package CrandallGridWorld.Nerd;



import java.util.ArrayList;

import CrandallGridWorld.TeamBug;

public abstract class NerdTeamBug extends TeamBug{

	private static ArrayList<NerdTeamBug> fam;
	
	public NerdTeamBug(Integer teamNum, Boolean hasBred) {
		super(teamNum, hasBred);
		addToTeam();
	}
	
	protected ArrayList<NerdTeamBug> getTeamList(){
		return fam;
	}
	
	private void addToTeam(){
		if(fam == null){
			fam = new ArrayList<NerdTeamBug>();
		}
		fam.add(this);
	}
}
