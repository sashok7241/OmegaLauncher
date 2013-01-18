package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

public class LauncherServer extends JComponent implements LauncherConstants, ActionListener
{
	public static final long serialVersionUID = 1L;
	public String address, name, motd = "???", maxplayers = "???", curplayers = "???";
	public int port, status;
	public LauncherServerPoll thread = new LauncherServerPoll(this);
	public LauncherButton remove = new LauncherButton("", 290, 5).setTextures(IMG_DELETE_DEF, IMG_DELETE_SEL);

	public LauncherServer(String _name, String _address, int _port)
	{
		super();
		name = _name;
		address = _address.toLowerCase();
		port = _port;
		setLayout(null);
		add(remove);
		remove.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		thread.interrupt();
		LauncherPanel.servers.remove(this);
		LauncherPanel.saveServers();
		LauncherPanel.addLoginElements();
	}

	@Override
	public void paintComponent(Graphics g1)
	{
		Graphics2D g = (Graphics2D) g1;
		LauncherUtils.drawTransparentRect(g, 0, 0, getWidth(), getHeight());
		LauncherUtils.drawIcon(g, 5, 5, status == 0 ? IMG_SERVER_POLL : status == 1 ? IMG_SERVER_ONLINE : IMG_SERVER_OFFLINE);
		LauncherUtils.drawText(g, 30, 19, name, Color.WHITE, 16);
		LauncherUtils.drawText(g, 5, 40, "MOTD: " + motd, Color.DARK_GRAY, 16);
		LauncherUtils.drawText(g, 5, 60, "Игроки: " + curplayers + " / " + maxplayers, Color.DARK_GRAY, 16);
		LauncherUtils.drawText(g, 5, 80, "Адрес: " + address + ":" + port, Color.DARK_GRAY, 16);
		super.paintComponent(g1);
	}
}