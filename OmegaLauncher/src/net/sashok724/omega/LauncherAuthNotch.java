package net.sashok724.omega;

import javax.swing.JOptionPane;

public class LauncherAuthNotch extends Thread
{
	public String version;

	public LauncherAuthNotch(String _version)
	{
		version = _version;
		start();
	}

	public String doRun()
	{
		String answer = LauncherUtils.executePost(LauncherPanel.authurl.getText(), "user=" + LauncherPanel.login.getText() + "&password=" + new String(LauncherPanel.password.getPassword()) + "&version=" + version);
		if (answer == null) return "Ошибка подключения к серверу";
		String[] splitted = answer.split(":");
		if (splitted.length != 5) return answer;
		LauncherPanel.addModManagerComponents(splitted[2], splitted[0]);
		return null;
	}

	@Override
	public void run()
	{
		String result = doRun();
		if (result == null) return;
		JOptionPane.showMessageDialog(LauncherStarter.frame, result, "Ошибка авторизации", JOptionPane.ERROR_MESSAGE);
		LauncherPanel.addLoginElements();
	}
}