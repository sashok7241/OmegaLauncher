package net.sashok724.omega;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class LauncherAuthRightPane extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public static LauncherAuthRightPane instance;
	public static LauncherTextfield login = new LauncherTextfield("login", 10, 10);

	public LauncherAuthRightPane()
	{
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setLayout(null);
		setOpaque(false);
		setPreferredSize(new Dimension(400, 100));
		add(login);
	}
}