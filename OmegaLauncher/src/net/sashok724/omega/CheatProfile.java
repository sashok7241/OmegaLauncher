package net.sashok724.omega;

public abstract class CheatProfile implements LauncherConstants
{
	public abstract String getLoginDetails(String post);

	public abstract void onSearchFinished();

	public abstract void onSearchStarted();

	public abstract void onStringArrayFound(String field, String[] array);

	public abstract void onStringFound(String field, String string);
}