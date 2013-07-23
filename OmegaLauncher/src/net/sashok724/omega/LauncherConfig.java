package net.sashok724.omega;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class LauncherConfig implements LauncherConstants
{
	public static final TreeMap<String, String> table = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	public static final File file = new File("omega.ini");
	
	public static int getInteger(String key, int def)
	{
		if(!table.containsKey(key)) return def;
		try
		{
			return Integer.parseInt(table.get(key));
		} catch(Exception e)
		{
			return def;
		}
	}
	
	public static String getString(String key, String def)
	{
		if(key == null) return def;
		if(!table.containsKey(key)) return def;
		return table.get(key);
	}
	
	public static synchronized void load() throws Exception
	{
		if(!file.exists()) return;
		table.clear();
		try(BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			for(String line = reader.readLine(); line != null; line = reader.readLine())
			{
				String[] splitted = line.split(": ");
				if(splitted.length != 2)
				{
					continue;
				}
				table.put(splitted[0], splitted[1]);
			}
		}
	}
	
	public static synchronized void save() throws Exception
	{
		try(PrintWriter writer = new PrintWriter(file))
		{
			for(Entry<String, String> entry : table.entrySet())
			{
				writer.println(entry.getKey() + ": " + entry.getValue());
			}
		}
	}
	
	public static void set(String key, Object value)
	{
		if(key == null) return;
		table.put(key, String.valueOf(value));
		try
		{
			save();
		} catch(Exception e)
		{
		}
	}
	
	static
	{
		try
		{
			load();
		} catch(Exception e)
		{
		}
	}
}