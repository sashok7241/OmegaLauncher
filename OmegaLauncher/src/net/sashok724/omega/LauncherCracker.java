package net.sashok724.omega;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LauncherCracker
{
	public LauncherCracker(File file, PrintWriter writer) throws Exception
	{
		JarFile jarfile = new JarFile(file);
		URLClassLoader loader = new URLClassLoader(new URL[] { file.toURI().toURL() });
		for (JarEntry entry : Collections.list(jarfile.entries()))
		{
			if (!entry.getName().endsWith(".class")) continue;
			String entryname = entry.getName().replace("/", ".").replace(".class", "");
			Field[] fields = loader.loadClass(entryname).getFields();
			for (Field field : fields)
			{
				if (field.getType() != String.class) continue;
				if (!Modifier.isStatic(field.getModifiers())) continue;
				writer.println(entryname + "." + field.getName() + " -> " + field.get(null));
			}
		}
		loader.close();
		jarfile.close();
	}
}