package net.sashok724.omega;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class LauncherAuthPane extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public static LauncherAuthPane instance;

	public LauncherAuthPane()
	{
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setPreferredSize(new Dimension(100, 100));
		setLayout(new BorderLayout());
		add(new LauncherAuthRightPane(), BorderLayout.EAST);
		instance = this;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;// TODO
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		LauncherUtils.drawBorder(g2d);
	}
}