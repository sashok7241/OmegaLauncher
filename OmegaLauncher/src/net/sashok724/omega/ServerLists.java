package net.sashok724.omega;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ServerLists extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	
	public ServerLists(Rectangle bounds)
	{
		setLayout(null);
		setOpaque(false);
		setBounds(bounds);
		for(String current : LauncherConfig.getString("serverlists", "").split(exdel))
		{
			String[] splitted = current.split(delim);
			if(splitted.length != 6) continue;
			addServer(new ServerEntry(splitted[0], splitted[1], splitted[3], splitted[2], splitted[4], splitted[5]), false);
		}
	}
	
	public void addServer(ServerEntry entry, boolean save)
	{
		add(entry);
		rebuildSizes(save);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = LauncherUtils.getG2D(g);
		LauncherUtils.drawTransparentRect(g2d, 0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	
	public void rebuildSizes(boolean save)
	{
		for(int index = 0; index < getComponents().length; index++)
			((ServerEntry) getComponents()[index]).setBounds(5, 5 + index * 85, getWidth() - 10, 80);
		StringBuilder builder = new StringBuilder();
		if(save && getComponents().length > 0)
		{
			for(Component current : getComponents())
			{
				ServerEntry entry = (ServerEntry) current;
				builder.append(exdel + entry.name + delim + entry.address.getHostName() + ":" + entry.address.getPort() + delim + entry.auth + delim + entry.dir + delim + entry.login + delim + entry.password);
			}
			LauncherConfig.set("serverlists", builder.toString().substring(exdel.length()));
		}
		validate();
		repaint();
	}
	
	public void removeServer(ServerEntry entry)
	{
		remove(entry);
		rebuildSizes(true);
	}
}