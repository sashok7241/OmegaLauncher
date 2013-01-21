package net.sashok724.omega;

public final class LauncherServerPoll extends Thread implements LauncherConstants
{
	public LauncherServer server;

	public LauncherServerPoll(LauncherServer _server)
	{
		server = _server;
		start();
	}

	@Override
	public void run()
	{
		while (true)
		{
			LauncherUtils.pollServer(server);
			server.repaint();
			try
			{
				Thread.sleep(2000L);
			} catch (Exception e)
			{
			}
		}
	}
}