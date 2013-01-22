package net.sashok724.omega;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;

public final class LauncherStarter implements LauncherConstants
{
	public static JFrame frame;

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame("OmegaLauncher v" + VERSION + " - The Cheat Minecraft Launcher");
		frame.setContentPane(new LauncherPanel());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(IMG_FAVICON);
		frame.setResizable(false);
		frame.setVisible(true);
		try
		{
			new LauncherCracker(new File("testlauncher.jar"));
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}