package net.sashok724.omega;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class LauncherConfig implements LauncherConstants
{
	public static final TreeMap<String, String> table = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
	public static final File file = new File("omega.ini");

	public static int getInteger(String key, int def)
	{
		try
		{
			load();
		} catch (Exception e)
		{
		}
		if (!table.containsKey(key)) return def;
		try
		{
			return Integer.parseInt(table.get(key));
		} catch (Exception e)
		{
			return def;
		}
	}

	public static String getString(String key, String def)
	{
		if(key == null) return "";
		try
		{
			load();
		} catch (Exception e)
		{
		}
		if (!table.containsKey(key)) return def;
		return table.get(key);
	}

	public static void load() throws Exception
	{
		if (!file.exists()) return;
		table.clear();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine = null;
		while ((currentLine = reader.readLine()) != null)
		{
			String[] splitted = currentLine.split(": ");
			if (splitted.length != 2) continue;
			table.put(splitted[0], splitted[1]);
		}
		reader.close();
	}

	public static void save() throws Exception
	{
		PrintWriter writer = new PrintWriter(file);
		for (String key : table.keySet())
			writer.println(key + ": " + table.get(key));
		writer.close();
	}

	public static void set(String key, Object value)
	{
		if(key == null) return;
		table.put(key, String.valueOf(value));
		try
		{
			save();
		} catch (Exception e)
		{
		}
	}
}