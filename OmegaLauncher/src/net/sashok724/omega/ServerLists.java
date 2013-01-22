package net.sashok724.omega;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class ServerLists extends JPanel
{
	public static final long serialVersionUID = 1L;

	public ServerLists(Rectangle bounds)
	{
		setLayout(null);
		setOpaque(false);
		setBounds(bounds);
	}

	public void addServer(ServerEntry entry)
	{
		add(entry);
		rebuildSizes();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = LauncherUtils.getG2D(g);
		LauncherUtils.drawTransparentRect(g2d, 0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public void rebuildSizes()
	{
		for (int index = 0; index < getComponents().length; index++)
			((ServerEntry) getComponents()[index]).setBounds(5, 5 + index * 85, getWidth() - 10, 80);
		validate();
		repaint();
	}

	public void removeServer(ServerEntry entry)
	{
		remove(entry);
		rebuildSizes();
	}
}