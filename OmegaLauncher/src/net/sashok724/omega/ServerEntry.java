package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.net.InetSocketAddress;

import javax.swing.JComponent;

public class ServerEntry extends JComponent implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public LauncherButton remove = new LauncherButton("", 570, 5).setTextures(IMG_DELETE_DEF, IMG_DELETE_SEL);
	public LauncherButton enter = new LauncherButton("", 570, 25).setTextures(IMG_ENTER_DEF, IMG_ENTER_SEL);
	public InetSocketAddress address;
	public int status;
	public String curplayers = "0", maxplayers = "0", motd = "<Unknown>", auth, name, login, password;

	public static final int getPort(String port)
	{
		try
		{
			return Integer.parseInt(port);
		} catch (Exception e)
		{
			return 25565;
		}
	}

	public ServerEntry(String _name, String ip, String port, String _auth, String _login, String _password)
	{
		address = new InetSocketAddress(ip, getPort(port));
		name = _name;
		auth = _auth;
		login = _login;
		password = _password;
		setLayout(null);
		add(remove);
		add(enter);
	}

	@Override
	public void paintComponent(Graphics g1)
	{
		Graphics2D g = LauncherUtils.getG2D(g1);
		LauncherUtils.drawTransparentRect(g, 0, 0, getWidth(), getHeight());
		LauncherUtils.drawIcon(g, 5, 5, status == 1 ? IMG_SERVER_ONLINE : status == 0 ? IMG_SERVER_POLL : IMG_SERVER_OFFLINE);
		LauncherUtils.drawText(g, 30, 20, name, Color.WHITE, 16);
		LauncherUtils.drawTextNormal(g, 5, 30, "MOTD: " + motd, Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 40, "Игроки: " + curplayers + " / " + maxplayers, Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 50, "Адрес: " + address.getHostName() + ":" + address.getPort(), Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 60, "Авторизация: " + auth, Color.DARK_GRAY, 10);
		LauncherUtils.drawTextNormal(g, 5, 70, "Логин, пароль: " + login + ", " + password.replaceAll(".", "*"), Color.DARK_GRAY, 10);
		super.paintComponent(g1);
	}
}