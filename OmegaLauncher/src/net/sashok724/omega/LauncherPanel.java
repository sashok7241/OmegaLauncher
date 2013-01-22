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
	// ========================= LOGIN ELEMENTS ===========================================
	public static ServerLists serverPanel = new ServerLists(new Rectangle(5, 5, 600, 515));
	public static LauncherButton offline = new LauncherButton("Оффлайн", 610, 435).setW(245);
	public static LauncherButton custom = new LauncherButton("Кастом вход", 610, 480).setW(245);
	static
	{
		loginElements.add(serverPanel);
		loginElements.add(offline);
		loginElements.add(custom);
	}

	public LauncherPanel()
	{
		instance = this;
		setLayout(null);
		setPreferredSize(new Dimension(850, 515));
		applyElements(loginElements);
		serverPanel.addServer(new ServerEntry("Лёнечка лапочка", "realminecraft.ru:25565", "rmc", "hummer,http://realminecraft.ru/launcher/action.php", "sashok724", "123456"));
		serverPanel.addServer(new ServerEntry("Minecraft-Москва Free 3", "game.minecraft-moscow.ru:25503", "moscow", "notch,http://minecraft-moscow.ru/prox/auth/auth.php", "sashok724", "someotherpassword"));
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