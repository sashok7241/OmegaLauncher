package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LauncherContentPane extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public static int mode = 0;
	public static LauncherContentPane instance;
	public static JScrollPane serverlist = new JScrollPane();
	public static LauncherLabel login_label = new LauncherLabel(LOGIN, 15, 5, 200, 23);
	public static LauncherTextfield login = new LauncherTextfield("login", 10, 30, "");
	public static LauncherLabel password_label = new LauncherLabel(PASSWORD, 15, 100, 200, 23);
	public static LauncherPassfield password = new LauncherPassfield("password", 10, 125);
	public static LauncherButton login_button = new LauncherButton(DO_LOGIN, 10, 300);
	public static LauncherButton offline_button = new LauncherButton(OFFLINE, 10, 350);
	public static LauncherButton addserver_button = new LauncherButton(ADD_SERVER, 515, 415).setW(315);
	// ========================================================================================
	public static LauncherLabel serverAddrLabel = new LauncherLabel(SERVER_IP, 520, 15, 200, 23);
	public static LauncherTextfield serverAddr = new LauncherTextfield(null, 520, 40, "").setW(305);
	public static LauncherLabel serverPortLabel = new LauncherLabel(SERVER_PORT, 520, 100, 200, 23);
	public static LauncherTextfield serverPort = new LauncherTextfield(null, 520, 125, "25565").setW(305);
	public static LauncherLabel serverNameLabel = new LauncherLabel(SERVER_NAME, 520, 180, 200, 23);
	public static LauncherTextfield serverName = new LauncherTextfield(null, 520, 205, "Minecraft Server").setW(305);
	public static LauncherButton addserver_cancel = new LauncherButton(SERVER_CNC, 515, 370).setW(315);
	public static LauncherButton addserver_accept = new LauncherButton(ADD_SERVER, 515, 415).setW(315);
	// ========================================================================================
	public static ArrayList<LauncherServer> servers = new ArrayList<LauncherServer>();

	public static void addLoginElements()
	{
		instance.removeAll();
		instance.add(login_label);
		instance.add(login);
		instance.add(password_label);
		instance.add(password);
		instance.add(login_button);
		instance.add(offline_button);
		instance.add(addserver_button);
		instance.add(serverlist);
		serverlist.setOpaque(false);
		serverlist.getViewport().setOpaque(false);
		serverlist.setBounds(510, 10, 325, 400);
		serverlist.setBorder(null);
		serverlist.removeAll();
		for (int i = 0; i < servers.size(); i++)
		{
			LauncherServer server = servers.get(i);
			server.setBounds(5, i * 105 + 5, serverlist.getWidth() - 10, 100);
			serverlist.add(server);
		}
		mode = MODE_LOGIN;
		instance.validate();
		instance.repaint();
	}

	public static void addServerAdderElements()
	{
		instance.removeAll();
		instance.add(login_label);
		instance.add(login);
		instance.add(password_label);
		instance.add(password);
		instance.add(login_button);
		instance.add(offline_button);
		instance.add(serverAddrLabel);
		instance.add(serverAddr);
		instance.add(serverPortLabel);
		instance.add(serverPort.resetC());
		instance.add(serverNameLabel);
		instance.add(serverName);
		instance.add(addserver_cancel);
		instance.add(addserver_accept);
		serverAddr.requestFocusInWindow();
		instance.validate();
		instance.repaint();
	}

	public static void addUpdaterElements()
	{
		instance.removeAll();
		mode = MODE_DOWNLOAD_BEGIN;
	}

	public static void loadServers()
	{
		servers.clear();
		for (String currentServer : LauncherConfig.getString("servers", "").split(exdel))
		{
			String[] splitted = currentServer.split(delim);
			if (splitted.length != 3) continue;
			String addr = splitted[0];
			String port = splitted[1];
			String name = splitted[2];
			if (!LauncherActionListener.checkInteger(port)) continue;
			servers.add(new LauncherServer(name, addr, Integer.parseInt(port)));
		}
		addLoginElements();
	}

	public static void saveServers()
	{
		String toSettings = "";
		if (servers.size() == 0) return;
		for (LauncherServer server : servers)
			toSettings += exdel + server.address + delim + server.port + delim + server.name;
		LauncherConfig.set("servers", toSettings.substring(exdel.length()));
	}

	public LauncherContentPane()
	{
		instance = this;
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		setLayout(null);
		addLoginElements();
		loadServers();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		LauncherUtils.drawBackground(g2d);
		LauncherUtils.drawTransparentRect(g2d, 510, 10, 325, 450);
	}
}