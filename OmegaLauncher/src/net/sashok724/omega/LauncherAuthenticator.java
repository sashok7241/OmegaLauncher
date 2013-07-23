package net.sashok724.omega;

import net.minecraft.LauncherFrame;

public class LauncherAuthenticator extends Thread
{
	public ServerEntry server;
	
	public LauncherAuthenticator(ServerEntry entry)
	{
		server = entry;
		start();
	}
	
	public String authenticate()
	{
		String[] splitted = server.auth.trim().split(",");
		if(splitted.length < 2) return "Не указан тип или другой параметр авторизации";
		switch(splitted[0])
		{
			case "sashok":
				if(splitted.length != 4) return "Не все параметры указаны (sashok,url,client,key)";
				String answer0 = LauncherUtils.executePost(splitted[1], "action=auth&login=" + server.login + "&password=" + server.password + "&client=" + splitted[2].replaceAll(" ", "").toLowerCase());
				String[] splitted1 = answer0.split("<br>");
				if(splitted1.length != 3) return answer0;
				String[] splitted2 = splitted1[1].split("<:>");
				if(splitted2.length != 2) return answer0;
				String session = decode(splitted2[1], splitted[3]);
				if(session == null) return "Ключ шифрации указан неверно.";
				new LauncherFrame(server.login, session, server.address.getHostName(), String.valueOf(server.address.getPort()));
				return null;
			case "notch":
				if(splitted.length != 3) return "Не все параметры указаны (notch,url,version)";
				String answer1 = LauncherUtils.executePost(splitted[1], "user=" + server.login + "&password=" + server.password + "&version=" + splitted[2]);
				if(answer1 == null) return "Ошибка подключения к серверу";
				String[] splitted3 = answer1.split(":");
				if(splitted3.length != 5) return answer1;
				new LauncherFrame(splitted3[2], splitted3[0], server.address.getHostName(), String.valueOf(server.address.getPort()));
				return null;
			default:
				return "Неизвестный тип авторизации: " + splitted[0];
		}
	}
	
	public String decode(String todec, String key)
	{
		String unxored = xorencode(inttostr(todec), key);
		if(unxored.replaceAll("[-0-9]", "").length() != 0) return null;
		return unxored;
	}
	
	@Override public void run()
	{
		LauncherUtils.disableAll(LauncherPanel.instance);
		String result = authenticate();
		if(result != null)
		{
			LauncherUtils.errorDialog(result);
			LauncherUtils.enableAll(LauncherPanel.instance);
			LauncherPanel.instance.applyElements(LauncherPanel.loginElements);
		}
	}
	
	public String xorencode(String text, String key)
	{
		String res = "";
		int j = 0;
		for(int i = 0; i < text.length(); i++)
		{
			res += (char) (text.charAt(i) ^ key.charAt(j));
			j++;
			if(j == key.length())
			{
				j = 0;
			}
		}
		return res;
	}
	
	public static String inttostr(String text)
	{
		String res = "";
		for(int i = 0; i < text.split("-").length; i++)
		{
			res += (char) Integer.parseInt(text.split("-")[i]);
		}
		return res;
	}
}