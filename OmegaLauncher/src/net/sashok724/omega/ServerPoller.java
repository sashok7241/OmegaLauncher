package net.sashok724.omega;

public class ServerPoller extends Thread
{
	public final ServerEntry server;
	
	public ServerPoller(ServerEntry entry)
	{
		server = entry;
		start();
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			LauncherUtils.pollServer(server);
			try
			{
				Thread.sleep(2000L);
			} catch (Exception e)
			{
			}
		}
	}
}