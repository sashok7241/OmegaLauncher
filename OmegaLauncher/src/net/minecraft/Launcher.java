package net.minecraft;

import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.BorderLayout;
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
	public int context = 0;
	public boolean active = false;
	public URL[] urls;
	public String bin;

	public Launcher(String bin, URL[] urls)
	{
		this.bin = bin;
		this.urls = urls;
	}

	@Override
	public void appletResize(int w, int h)
	{
	}

	@Override
	public void destroy()
	{
		if (mcApplet != null)
		{
			mcApplet.destroy();
			return;
		}
	}

	@Override
	public URL getDocumentBase()
	{
		try
		{
			return new URL("http://www.minecraft.net/game/");
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getParameter(String name)
	{
		String custom = customParameters.get(name);
		if (custom != null) return custom;
		try
		{
			return super.getParameter(name);
		} catch (Exception e)
		{
			customParameters.put(name, null);
		}
		return null;
	}

	@Override
	public void init()
	{
		if (mcApplet != null)
		{
			mcApplet.init();
			return;
		}
		init(0);
	}

	public void init(int i)
	{
		@SuppressWarnings("resource")
		URLClassLoader cl = new URLClassLoader(urls);
		System.setProperty("org.lwjgl.librarypath", bin + "natives");
		System.setProperty("net.java.games.input.librarypath", bin + "natives");
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
			active = true;
			validate();
		} catch (Exception e)
		{
			LauncherUtils.throwException(e);
		}
	}

	@Override
	public boolean isActive()
	{
		if (context == 0)
		{
			context = -1;
			try
			{
				if (getAppletContext() != null) context = 1;
			} catch (Exception e)
			{
			}
		}
		if (context == -1) return active;
		return super.isActive();
	}

	public void replace(Applet applet)
	{
		mcApplet = applet;
		applet.setStub(this);
		applet.setSize(getWidth(), getHeight());
		setLayout(new BorderLayout());
		add(applet, "Center");
		applet.init();
		active = true;
		applet.start();
		validate();
	}

	@Override
	public void start()
	{
		if (mcApplet != null)
		{
			mcApplet.start();
			return;
		}
	}

	@Override
	public void stop()
	{
		if (mcApplet != null)
		{
			active = false;
			mcApplet.stop();
			return;
		}
	}
}
