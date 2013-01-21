package net.sashok724.omega;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;

public final class LauncherStarter implements LauncherConstants
{
	public static final JFrame frame = new JFrame("OmegaLauncher v" + VERSION);

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame.setIconImage(IMG_FAVICON);
		frame.setContentPane(new LauncherPanel());
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.WHITE);
		frame.setResizable(false);
		frame.setSize(850, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
	}
}