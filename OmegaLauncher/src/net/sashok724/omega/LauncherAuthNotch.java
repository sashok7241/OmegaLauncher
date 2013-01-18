package net.sashok724.omega;

public class LauncherAuthNotch extends Thread
{
	public String version;

	public LauncherAuthNotch(String _version)
	{
		version = _version;
		start();
	}

	@Override
	public void run()
	{
	}
}