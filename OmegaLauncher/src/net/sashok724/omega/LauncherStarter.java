package net.sashok724.omega;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class LauncherStarter implements LauncherConstants
{
	public static final JFrame frame = new JFrame(TITLE + VERSION);

	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame.setContentPane(new LauncherContentPane());
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.WHITE);
		frame.setSize(850, 500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
	}
}