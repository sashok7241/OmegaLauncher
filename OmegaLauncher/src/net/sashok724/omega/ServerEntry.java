package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.InetSocketAddress;

import javax.swing.JComponent;

public class ServerEntry extends JComponent implements LauncherConstants, ActionListener
{
	public static final long serialVersionUID = 1L;
	public LauncherButton remove = new LauncherButton("", 570, 5).setTextures(IMG_DELETE_DEF, IMG_DELETE_SEL);
	public LauncherButton enter = new LauncherButton("", 570, 25).setTextures(IMG_ENTER_DEF, IMG_ENTER_SEL);
	public InetSocketAddress address;
	public int status;
	public String curplayers = "0", maxplayers = "0", motd = "<Unknown>", dir, auth, name, login, password;
	
	public ServerEntry(String _name, String ip, String _dir, String _auth, String _login, String _password)
	{
		if(ip.split(":").length < 2)
		{
			ip += ":25565";
		}
		address = new InetSocketAddress(ip.split(":")[0], getPort(ip.split(":")[1]));
		dir = _dir;
		name = _name;
		auth = _auth;
		login = _login;
		password = _password;
		setLayout(null);
		add(remove);
		remove.addActionListener(this);
		add(enter);
		enter.addActionListener(this);
		new ServerPoller(this);
	}
	
	@Override public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == remove)
		{
			LauncherPanel.serverPanel.removeServer(this);
		}
		if(e.getSource() == enter)
		{
			new LauncherAuthenticator(this);
		}
	}
	
	public File getDirectory()
	{
		return new File(LauncherUtils.minecraftDir, dir);
	}
	
	@Override public void paintComponent(Graphics g1)
	{
		Graphics2D g = LauncherUtils.getG2D(g1);
		LauncherUtils.drawTransparentRect(g, 0, 0, getWidth(), getHeight());
		LauncherUtils.drawIcon(g, 5, 5, status == 1 ? IMG_SERVER_ONLINE : status == 0 ? IMG_SERVER_POLL : IMG_SERVER_OFFLINE);
		LauncherUtils.drawText(g, 30, 20, name, Color.WHITE, 16);
		LauncherUtils.drawTextNormal(g, 5, 32, "MOTD: " + motd, Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 42, "Игроки: " + curplayers + " / " + maxplayers, Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 52, "Адрес: " + address.getHostName() + ":" + address.getPort(), Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 62, "Авторизация: " + auth + ", " + login + ", " + password.replaceAll(".", "*"), Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 72, "Папка: " + getDirectory().getAbsolutePath(), Color.DARK_GRAY, 10);
		super.paintComponent(g1);
	}
	
	public static final int getPort(String port)
	{
		try
		{
			return Integer.parseInt(port);
		} catch(Exception e)
		{
			return 25565;
		}
	}
}