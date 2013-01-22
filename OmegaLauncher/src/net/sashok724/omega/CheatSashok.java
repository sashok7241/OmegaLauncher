package net.sashok724.omega;

import java.util.ArrayList;

public final class CheatSashok extends CheatProfile
{
	public ArrayList<String[]> servers = new ArrayList<String[]>();
	
	@Override
	public void onSearchFinished()
	{
		
	}

	@Override
	public void onSearchStarted()
	{
	}

	@Override
	public void onStringArrayFound(String field, String[] array)
	{
		for(String current : array)
		{
			System.out.println(current);
			String[] splitted = current.split(", ");
			if(splitted.length > 2 && splitted.length < 5)
			{
				servers.add(new String[]{ splitted[0], splitted[1], splitted[2] });
			}
		}
	}

	@Override
	public void onStringFound(String field, String string)
	{
		
	}

	@Override
	public String getLoginDetails(String post)
	{
		return null;
	}
}