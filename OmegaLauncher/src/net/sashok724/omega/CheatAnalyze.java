package net.sashok724.omega;

import java.util.ArrayList;

public final class CheatAnalyze extends CheatProfile
{
	public ArrayList<String> entries = new ArrayList<String>();

	@Override
	public String getLoginDetails(String post)
	{
		return null;
	}

	@Override
	public void onSearchFinished()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("======================== sashok724's launcher analyze ========================\n");
		builder.append("Тут отображены все массивы и строковые переменные, что мы сумели найти.\n");
		builder.append("Среди них могут быть IP серверов, сессии, ключи, и другие няшности =)\n");
		builder.append("==============================================================================\n");
		for (String current : entries)
			builder.append(current + "\n");
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
		for (int index = 0; index < array.length; index++)
			entries.add("variable: " + field + "[" + index + "], value: " + array[index]);
	}

	@Override
	public void onStringFound(String field, String string)
	{
		entries.add("variable: " + field + ", value: " + string);
	}
}