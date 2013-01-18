package net.sashok724.omega;

public class LauncherAuthSashok extends Thread
{
	public String version, key;

	public LauncherAuthSashok(String _version, String _key)
	{
		version = _version;
		key = _key;
		start();
	}

	@Override
	public void run()
	{
	}
}