package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class LauncherServer extends JComponent implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public String address, name, motd = "???", maxplayers = "???", curplayers = "???";
	public int port, status;

	public LauncherServer(String _name, String _address, int _port)
	{
		super();
		name = _name;
		address = _address.toLowerCase();
		port = _port;
		new LauncherServerPoll(this);
	}

	@Override
	public void paintComponent(Graphics g1)
	{
		Graphics2D g = (Graphics2D) g1;
		LauncherUtils.drawTransparentRect(g, 0, 0, getWidth(), getHeight());
		LauncherUtils.drawIcon(g, 5, 5, status == 0 ? IMG_SERVER_POLL : status == 1 ? IMG_SERVER_ONLINE : IMG_SERVER_OFFLINE);
		LauncherUtils.drawText(g, 30, 19, name, Color.WHITE);
		LauncherUtils.drawText(g, 5, 40, MOTD + motd, Color.DARK_GRAY);
		LauncherUtils.drawText(g, 5, 60, PLAYERS + curplayers + " / " + maxplayers, Color.DARK_GRAY);
		LauncherUtils.drawText(g, 5, 80, ADDRESS + address + ":" + port, Color.DARK_GRAY);
	}
}