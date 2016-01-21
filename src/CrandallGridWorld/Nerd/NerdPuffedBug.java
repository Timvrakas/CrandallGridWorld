package CrandallGridWorld.Nerd;


public class NerdPuffedBug extends NerdPufferBug {
	
	private NerdPuffedBug secondary;
	private final int TURNS_PUFFED = 3;
	
	public NerdPuffedBug(int teamName, boolean hasBred)
	{
		super(teamName, hasBred);
		setCounter(TURNS_PUFFED);
	}
	public void act()
	{
		count();
		if(getCounter() == 0);
		{
			deflate();
		}
		super.act();
	}

}
