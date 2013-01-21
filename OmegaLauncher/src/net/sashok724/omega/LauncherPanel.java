package net.sashok724.omega;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public final class LauncherPanel extends JPanel
{
	public static final long serialVersionUID = 1L;
	public static LauncherPanel instance;
	public static int currentByte = 0, currentSize = 0;
	public static String currentStat = "<Unknown>", currentFile = "<Unknown>", username, session;
	public static ArrayList<String> requiredFiles;
	public static ArrayList<JComponent> loginElements = new ArrayList<JComponent>();
	public static ServerLists serverPanel = new ServerLists(new Rectangle(5, 5, 600, 515));
	static
	{
		loginElements.add(serverPanel);
	}

	public LauncherPanel()
	{
		instance = this;
		setLayout(null);
		setPreferredSize(new Dimension(850, 515));
		applyElements(loginElements);
		serverPanel.addServer(new ServerEntry("Лёнечка лапочка", "realminecraft.ru", "25565", "hummer,http://realminecraft.ru/launcher/action.php", "sashok724", "123456"));
	}

	public void applyElements(ArrayList<JComponent> components)
	{
		instance.removeAll();
		for (JComponent current : components)
			instance.add(current);
		instance.repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = LauncherUtils.getG2D(g);
		LauncherUtils.drawBackground(g2d);
	}
}