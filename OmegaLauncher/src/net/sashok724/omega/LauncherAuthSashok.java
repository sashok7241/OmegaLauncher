package net.sashok724.omega;

import javax.swing.JOptionPane;

public final class LauncherAuthSashok extends Thread
{
	public static String inttostr(String text)
	{
		String res = "";
		for (int i = 0; i < text.split("-").length; i++)
			res += (char) Integer.parseInt(text.split("-")[i]);
		return res;
	}

	public static String strtoint(String text)
	{
		String res = "";
		for (int i = 0; i < text.length(); i++)
			res += (int) text.charAt(i) + "-";
		res = res.substring(0, res.length() - 1);
		return res;
	}

	public String key, client;

	public LauncherAuthSashok(String _key, String _client)
	{
		key = _key;
		client = _client;
		start();
	}

	public String decode(String todec)
	{
		String unxored = xorencode(strtoint(todec));
		if (!LauncherActionListener.checkInteger(unxored)) return null;
		return unxored;
	}

	public String doRun()
	{
		String answer = LauncherUtils.executePost(LauncherPanel.authurl.getText(), "action=auth&login=" + LauncherPanel.login.getText() + "&password=" + new String(LauncherPanel.password.getPassword()) + "&client=" + client);
		if (answer == null) return "Ошибка подключения к серверу";
		String[] splitted1 = answer.split("<br>");
		if (splitted1.length != 3) return answer;
		String[] splitted2 = splitted1[1].split("<:>");
		if (splitted2.length != 2) return answer;
		String session = decode(splitted2[1]);
		if (session == null) return "Ключ шифрации указан неверно.";
		LauncherPanel.addModManagerComponents(splitted2[0], session);
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

	public String xorencode(String text)
	{
		String res = "";
		int j = 0;
		for (int i = 0; i < text.length(); i++)
		{
			res += (char) (text.charAt(i) ^ key.charAt(j));
			j++;
			if (j == key.length()) j = 0;
		}
		return res;
	}
}