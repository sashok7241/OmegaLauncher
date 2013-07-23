package net.minecraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;

import javax.swing.JFrame;

import net.sashok724.omega.LauncherConstants;
import net.sashok724.omega.LauncherStarter;
import net.sashok724.omega.LauncherUtils;

public final class LauncherFrame extends JFrame implements LauncherConstants
{
	private static final long serialVersionUID = 1L;
	public static Launcher mcapplet;
	
	public LauncherFrame(String login, String session, String serverip, String serverport)
	{
		try
		{
			addWindowListener(new WindowListener()
			{
				@Override public void windowActivated(WindowEvent e)
				{
				}
				
				@Override public void windowClosed(WindowEvent e)
				{
				}
				
				@Override public void windowClosing(WindowEvent e)
				{
					mcapplet.stop();
					mcapplet.destroy();
					System.exit(0);
				}
				
				@Override public void windowDeactivated(WindowEvent e)
				{
				}
				
				@Override public void windowDeiconified(WindowEvent e)
				{
				}
				
				@Override public void windowIconified(WindowEvent e)
				{
				}
				
				@Override public void windowOpened(WindowEvent e)
				{
				}
			});
			LauncherStarter.frame.setVisible(false);
			System.out.println("Login: " + login + ", Session: " + session);
			File bin = new File(LauncherUtils.minecraftDir, "bin");
			setForeground(Color.BLACK);
			setBackground(Color.BLACK);
			URL[] urls = new URL[4];
			urls[0] = new File(bin, "minecraft.jar").toURI().toURL();
			urls[3] = new File(bin, "lwjgl_util.jar").toURI().toURL();
			urls[1] = new File(bin, "lwjgl.jar").toURI().toURL();
			urls[2] = new File(bin, "jinput.jar").toURI().toURL();
			mcapplet = new Launcher(bin, urls);
			if(login != null)
			{
				mcapplet.customParameters.put("username", login);
			}
			if(session != null)
			{
				mcapplet.customParameters.put("sessionid", session);
			}
			if(serverip != null)
			{
				mcapplet.customParameters.put("server", serverip);
			}
			if(serverport != null)
			{
				mcapplet.customParameters.put("port", serverport);
			}
			mcapplet.customParameters.put("stand-alone", "true");
			mcapplet.setForeground(Color.BLACK);
			mcapplet.setBackground(Color.BLACK);
			setTitle("Minecraft");
			setMinimumSize(LauncherStarter.frame.getMinimumSize());
			setLayout(new BorderLayout());
			setBounds(LauncherStarter.frame.getBounds());
			setIconImage(IMG_FAVICON);
			setVisible(true);
			add(mcapplet, BorderLayout.CENTER);
			validate();
			mcapplet.init();
			mcapplet.start();
		} catch(Exception e)
		{
			LauncherUtils.throwException(e);
		}
	}
}