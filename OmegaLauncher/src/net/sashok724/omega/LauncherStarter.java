package net.sashok724.omega;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class LauncherStarter implements LauncherConstants
{
	public static JFrame frame;
	
	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame("OmegaLauncher v" + VERSION + " - The Cheat Minecraft Launcher");
		frame.setSize(850, 500);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(new LauncherPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(IMG_FAVICON);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}