package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class LauncherContentPane extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public static LauncherContentPane instance;
	public static LauncherTextfield login = new LauncherTextfield("login", 200, 100);
	public static LauncherPassfield password = new LauncherPassfield("password", 200, 200);
	public static LauncherLabel login_label = new LauncherLabel(LOGIN, 205, 75, 200, 23);
	public static LauncherLabel password_label = new LauncherLabel(PASSWORD, 205, 175, 200, 23);
	public static LauncherButton login_button = new LauncherButton(DO_LOGIN, 200, 300);
	public static LauncherButton offline_button = new LauncherButton(OFFLINE, 200, 350);

	public LauncherContentPane()
	{
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		setLayout(null);
		add(login_label);
		add(login);
		add(password_label);
		add(password);
		add(login_button);
		add(offline_button);
		instance = this;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		LauncherUtils.drawBackground(g2d);
	}
}