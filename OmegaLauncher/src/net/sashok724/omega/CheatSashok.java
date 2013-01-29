package net.sashok724.omega;

import java.util.ArrayList;

public final class CheatSashok extends CheatProfile
{
	public ArrayList<String[]> servers = new ArrayList<String[]>();
	public ArrayList<String> potencialKeys = new ArrayList<String>();
	
	@Override
	public void onSearchFinished()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("======================== sashok724's launcher analyze ========================\n");
		builder.append("Режим: Анализ лаунчера sashok724\n");
		builder.append("Найденные сервера:\n");
		for(String[] array : servers)
			builder.append("\tимя: '" + array[0] + "', адрес: '" + array[1] + ":" + array[2] + "'\n");
		builder.append("Возможные ключи сессии (Это не может быть словом, скорее всего набор букв и цифр):\n");
		for(String key : potencialKeys)
			builder.append("\t\'" + key + "'\n");
		builder.append("Скорее всего ключом является:\n");
		builder.append("\t'" + potencialKeys.get(potencialKeys.size() - 1) + "'\n");
		builder.append("Авторизации для серверов:\n");
		for(String[] array : servers)
			builder.append("\t'sashok,http://DOMAIN/dir/launcher.php," + array[0] + "," + potencialKeys.get(potencialKeys.size() - 1) + "'\n");
		builder.append("==============================================================================");
		LauncherPanel.instance.applyElements(LauncherPanel.analyzeElements);
		LauncherPanel.analyzePane.setText(builder.toString());
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
			String[] splitted = current.split(", ");
			if(splitted.length > 2) servers.add(new String[] { splitted[0], splitted[1], splitted[2] });
		}
	}
	
	@Override
	public void onStringFound(String field, String string)
	{
		if(field.contains("components")) return;
		if(!field.startsWith("net.sashok724.launcher.")) return;
		if(string.startsWith("ABCDEF")) return;
		if(string.equals("=")) return;
		if(string.equals("AppData")) return;
		if(string.contains("%SERVERNAME%")) return;
		potencialKeys.add(string);
	}
}