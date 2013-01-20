package net.minecraft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.net.URL;

import javax.swing.JFrame;
import net.minecraft.Launcher;
import net.sashok724.omega.LauncherConstants;
import net.sashok724.omega.LauncherPanel;
import net.sashok724.omega.LauncherUtils;

public class LauncherFrame extends JFrame implements LauncherConstants
{
	private static final long serialVersionUID = 1L;
	public static Launcher mcapplet;
	
	public LauncherFrame()
	{
		try
		{
			addWindowListener(new WindowListener()
			{
				public void windowOpened(WindowEvent e) {}
				public void windowIconified(WindowEvent e) {}
				public void windowDeiconified(WindowEvent e) {}
				public void windowDeactivated(WindowEvent e) {}
				public void windowClosed(WindowEvent e) {}
				public void windowActivated(WindowEvent e) {}
				public void windowClosing(WindowEvent e)
				{
					mcapplet.stop();
					mcapplet.destroy();
					System.exit(0);
				}
			});
			
			String bin = LauncherUtils.minecraftDir.toString() + File.separator + "bin" + File.separator;
			setForeground(Color.BLACK);
			setBackground(Color.BLACK);
			URL[] urls = new URL[4];
			urls[0] = new File(bin, "minecraft.jar").toURI().toURL();
			urls[1] = new File(bin, "lwjgl.jar").toURI().toURL();
			urls[2] = new File(bin, "jinput.jar").toURI().toURL();
			urls[3] = new File(bin, "lwjgl_util.jar").toURI().toURL();
			
			mcapplet = new Launcher(bin, urls);
			mcapplet.customParameters.put("username", LauncherPanel.username);
			mcapplet.customParameters.put("sessionid", LauncherPanel.session);
			mcapplet.customParameters.put("stand-alone", "true");
			setTitle("Minecraft");
			setSize(850, 480);
			setMinimumSize(new Dimension(850, 480));
			setLocationRelativeTo(null);
			mcapplet.setForeground(Color.BLACK);
			mcapplet.setBackground(Color.BLACK);
			setLayout(new BorderLayout());
			add(mcapplet, BorderLayout.CENTER);
			validate();
			setIconImage(IMG_FAVICON);
			setVisible(true);
			mcapplet.init();
			mcapplet.start();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
