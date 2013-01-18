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

	public String doRun()
	{
		String answer = LauncherUtils.executePost(LauncherPanel.authurl.getText(), "");
		if (answer == null) return "Ошибка подключения к серверу";
		return null;
	}

	@Override
	public void run()
	{
		String result = doRun();
		if (result == null) return;
	}
}