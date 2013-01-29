package net.minecraft;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import net.sashok724.omega.LauncherUtils;

public final class Launcher extends Applet implements AppletStub
{
	public static final long serialVersionUID = 1L;
	public Applet mcApplet = null;
	public Map<String, String> customParameters = new HashMap<String, String>();
	public URL[] urls;
	public File bin;
	
	public Launcher(File bin2, URL[] _urls)
	{
		bin = bin2;
		urls = _urls;
	}
	
	@Override
	public void appletResize(int w, int h)
	{
	}
	
	@Override
	public void destroy()
	{
		if(mcApplet != null) mcApplet.destroy();
	}
	
	@Override
	public URL getDocumentBase()
	{
		try
		{
			return new URL("http://www.minecraft.net/game/");
		} catch(MalformedURLException e)
		{
			return null;
		}
	}
	
	@Override
	public String getParameter(String name)
	{
		String custom = customParameters.get(name);
		if(custom != null) return custom;
		try
		{
			return super.getParameter(name);
		} catch(Exception e)
		{
			customParameters.put(name, null);
		}
		return null;
	}
	
	@Override
	public void init()
	{
		@SuppressWarnings("resource")
		URLClassLoader cl = new URLClassLoader(urls);
		System.setProperty("org.lwjgl.librarypath", bin + File.separator + "natives");
		System.setProperty("net.java.games.input.librarypath", bin + File.separator + "natives");
		try
		{
			Class<?> Mine = cl.loadClass("net.minecraft.client.MinecraftApplet");
			Applet applet = (Applet) Mine.newInstance();
			mcApplet = applet;
			applet.setStub(this);
			applet.setSize(getWidth(), getHeight());
			setLayout(new BorderLayout());
			add(applet, "Center");
			applet.init();
			validate();
		} catch(Exception e)
		{
			LauncherUtils.throwException(e);
		}
	}
	
	@Override
	public boolean isActive()
	{
		return mcApplet != null;
	}
	
	public void replace(Applet applet)
	{
		mcApplet = applet;
		applet.setStub(this);
		applet.setSize(getWidth(), getHeight());
		setLayout(new BorderLayout());
		add(applet, "Center");
		applet.init();
		applet.start();
		validate();
	}
	
	@Override
	public void start()
	{
		if(mcApplet != null) mcApplet.start();
	}
	
	@Override
	public void stop()
	{
		if(mcApplet != null) mcApplet.stop();
	}
}