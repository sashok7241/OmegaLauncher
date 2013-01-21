package net.sashok724.omega;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public final class LauncherPanel extends JPanel
{
	public static final long serialVersionUID = 1L;
	public static ServerLists serverPanel;
	public static LauncherPanel instance;
	public static int currentByte = 0, currentSize = 0;
	public static String currentStat = "<Unknown>", currentFile = "<Unknown>", username, session;
	public static ArrayList<String> requiredFiles;
	public static ArrayList<? extends JComponent> loginElements;

	public LauncherPanel()
	{
		setLayout(null);
		instance = this;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = LauncherUtils.getG2D(g);
		LauncherUtils.drawBackground(g2d);
	}
}