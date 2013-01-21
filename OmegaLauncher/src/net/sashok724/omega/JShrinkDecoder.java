package net.sashok724.omega;

import java.io.File;
import java.io.FileInputStream;

public final class JShrinkDecoder
{
	public static byte[] byteArray;
	public static String[] cache = new String[256];
	public static int[] keys = new int[256];

	public static String decode(int id)
	{
		int key = id & 0xFF;
		if (keys[key] != id)
		{
			keys[key] = id;
			if (id < 0) id &= 65535;
			String decoded = new String(byteArray, id, byteArray[id - 1] & 0xFF).intern();
			cache[key] = decoded;
		}
		return cache[key];
	}

	public static boolean updateFile(File file)
	{
		try
		{
			FileInputStream fis = new FileInputStream(file);
			int startbyte = fis.read() << 16 | fis.read() << 8 | fis.read();
			byteArray = new byte[startbyte];
			int index = 0, k = (byte) startbyte;
			byte[] byteList = byteArray;
			while (startbyte != 0)
			{
				int currentbyte = fis.read(byteList, index, startbyte);
				if (currentbyte == -1) break;
				startbyte -= currentbyte;
				currentbyte += index;
				while (index < currentbyte)
				{
					byte[] bytes = byteList;
					bytes[index] = (byte) (bytes[index] ^ k);
					index++;
				}
			}
			fis.close();
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}
}