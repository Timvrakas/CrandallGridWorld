package CrandallGridWorld;



import java.util.ArrayList;

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
