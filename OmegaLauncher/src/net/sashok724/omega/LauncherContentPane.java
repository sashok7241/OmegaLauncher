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
	public static LauncherTextfield login = new LauncherTextfield("login", 10, 30);
	public static LauncherLabel password_label = new LauncherLabel(PASSWORD, 15, 100, 200, 23);
	public static LauncherPassfield password = new LauncherPassfield("password", 10, 125);
	public static LauncherButton login_button = new LauncherButton(DO_LOGIN, 10, 300);
	public static LauncherButton offline_button = new LauncherButton(OFFLINE, 10, 350);
	public static LauncherButton addserver_button = new LauncherButton(ADD_SERVER, 505, 415);
	//========================================================================================
	public static LauncherLabel serverAddrLabel = new LauncherLabel(SERVER_IP, 510, 15, 200, 23);
	public static LauncherTextfield serverAddr = new LauncherTextfield(null, 510, 40).setW(305);
	//========================================================================================
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
		addserver_button.setSize(315, addserver_button.getHeight());
		serverlist.setOpaque(false);
		serverlist.getViewport().setOpaque(false);
		serverlist.setBounds(500, 10, 325, 400);
		serverlist.setBorder(null);
		serverlist.removeAll();
		//===============================
		servers.add(new LauncherServer("sashok724.net", 25565));
		//===============================
		for(int i = 0; i < servers.size(); i++)
		{
			LauncherServer server = servers.get(i);
			server.setBounds(5, i * 105 + 5, serverlist.getWidth() - 10, 100);
			serverlist.add(server);
		}
		mode = MODE_LOGIN;
		instance.validate();
		instance.repaint();
	}

	public static void addUpdaterElements()
	{
		instance.removeAll();
		mode = MODE_DOWNLOAD_BEGIN;
	}

	public LauncherContentPane()
	{
		instance = this;
		setDoubleBuffered(true);
		setBackground(Color.BLACK);
		setForeground(Color.BLACK);
		setLayout(null);
		addLoginElements();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		LauncherUtils.drawBackground(g2d);
		LauncherUtils.drawTransparentRect(g2d, 500, 10, 325, 450);
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
		instance.validate();
		instance.repaint();
	}
}