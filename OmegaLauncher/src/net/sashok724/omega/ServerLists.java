package net.sashok724.omega;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ServerLists extends JPanel
{
	public static final long serialVersionUID = 1L;
	public ArrayList<ServerEntry> serverList = new ArrayList<ServerEntry>();

	public ServerLists(Rectangle bounds)
	{
		setBounds(bounds);
		setOpaque(false);
		setBorder(null);
	}

	public void addServer(ServerEntry entry)
	{
		serverList.add(entry);
		add(entry);
		rebuildSizes();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = LauncherUtils.getG2D(g);
		LauncherUtils.drawTransparentRect(g2d, 0, 0, getWidth(), getHeight());
	}

	public void rebuildSizes()
	{
		for (int index = 0; index < serverList.size(); index++)
		{
			ServerEntry entry = serverList.get(index);
			entry.setBounds(5, 5 + index * 85, getWidth() - 10, 80);
		}
		validate();
		repaint();
	}

	public void removeServer(ServerEntry entry)
	{
		serverList.remove(entry);
		remove(entry);
		rebuildSizes();
	}
}