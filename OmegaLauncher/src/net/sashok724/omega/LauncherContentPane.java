package net.sashok724.omega;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class LauncherContentPane extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public static LauncherContentPane instance;

	public LauncherContentPane()
	{
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		setLayout(new BorderLayout());
		add(new LauncherAuthPane(), BorderLayout.SOUTH);
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