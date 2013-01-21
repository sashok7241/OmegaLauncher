package net.sashok724.omega;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.minecraft.LauncherFrame;

public final class LauncherPanel extends JPanel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public static ArrayList<String> requiredFiles;
	public static String currentFile = "<Unknown>", currentStat = "<Unknown>", username, session;
	public static int mode = 0, currentSize = 0, currentByte = 0;
	public static LauncherPanel instance;
	public static JScrollPane serverlist = new JScrollPane();
	public static LauncherLabel login_label = new LauncherLabel("Логин:", 15, 10);
	public static LauncherTextfield login = new LauncherTextfield("login", 10, 30, "", false);
	public static LauncherLabel password_label = new LauncherLabel("Пароль:", 15, 90);
	public static LauncherPassfield password = new LauncherPassfield("password", 10, 110);
	public static LauncherLabel authurl_label = new LauncherLabel("Ссылка авторизации:", 15, 170);
	public static LauncherTextfield authurl = new LauncherTextfield("authurl", 10, 190, "http://login.minecraft.net/", true);
	public static LauncherLabel authext_label = new LauncherLabel("Тип лаунчера:", 15, 250);
	public static LauncherTextfield authext = new LauncherTextfield("authext", 10, 270, "notch:13", false);
	public static LauncherButton login_button = new LauncherButton("Войти в аккаунт", 10, 415);
	public static LauncherButton offline_button = new LauncherButton("Играть оффлайн", 10, 370);
	public static LauncherButton addserver_button = new LauncherButton("Добавить сервер", 515, 420).setW(315);
	// ========================================================================================
	public static LauncherLabel serverNameLabel = new LauncherLabel("Имя сервера:", 520, 15);
	public static LauncherTextfield serverName = new LauncherTextfield(null, 520, 35, "Minecraft Server", false).setW(305);
	public static LauncherLabel serverAddrLabel = new LauncherLabel("Адрес сервера:", 520, 100);
	public static LauncherTextfield serverAddr = new LauncherTextfield(null, 520, 120, "", false).setW(305);
	public static LauncherLabel serverPortLabel = new LauncherLabel("Порт сервера:", 520, 185);
	public static LauncherTextfield serverPort = new LauncherTextfield(null, 520, 205, "25565", false).setW(305);
	public static LauncherButton addserver_cancel = new LauncherButton("Отмена", 515, 375).setW(315);
	public static LauncherButton addserver_accept = new LauncherButton("Добавить сервер", 515, 420).setW(315);
	// ========================================================================================
	public static LauncherButton download_accept = new LauncherButton("Да, закачать", 225, 300);
	public static LauncherButton download_cancel = new LauncherButton("Нет, вернуться", 225, 350);
	// ========================================================================================
	public static ArrayList<LauncherServer> servers = new ArrayList<LauncherServer>();

	public static void addDownloadElements()
	{
		instance.removeAll();
		new LauncherMinecraftUpdater();
		mode = MODE_DOWNLOAD_BEGIN;
		instance.validate();
		instance.repaint();
	}

	public static void addLoginElements()
	{
		instance.removeAll();
		instance.add(login_label);
		instance.add(login);
		instance.add(password_label);
		instance.add(password);
		instance.add(authurl_label);
		instance.add(authurl);
		instance.add(authext_label);
		instance.add(authext);
		instance.add(login_button);
		instance.add(offline_button);
		instance.add(addserver_button);
		instance.add(serverlist);
		addserver_button.setEnabled(servers.size() < 4);
		serverlist.setOpaque(false);
		serverlist.getViewport().setOpaque(false);
		serverlist.setBounds(510, 10, 325, 405);
		serverlist.setBorder(null);
		serverlist.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		serverlist.removeAll();
		for (int i = 0; i < servers.size(); i++)
		{
			LauncherServer server = servers.get(i);
			server.setBounds(5, i * 105 + 5, serverlist.getWidth() - 10, 100);
			serverlist.add(server);
		}
		LauncherUtils.enableAll(instance);
		mode = MODE_LOGIN;
		instance.validate();
		instance.repaint();
	}

	public static void addModManagerComponents(String login, String _session)
	{
		username = login;
		session = _session;
		instance.removeAll();
		requiredFiles = LauncherUtils.checkClient();
		if (requiredFiles.size() == 0)
		{
			try
			{
			//	LauncherUtils.updateNatives();
				addMinecraftElements();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			mode = MODE_MODMM;
		} else
		{
			mode = MODE_DOWNLOAD_REQUEST;
			instance.add(download_accept);
			instance.add(download_cancel);
		}
		instance.validate();
		instance.repaint();
	}
	
	public static void addMinecraftElements() throws Exception
	{
		instance.removeAll();
		instance.setLayout(new BorderLayout());
		LauncherStarter.frame.setVisible(false);
		new LauncherFrame();
	}

	public static void addServerAdderElements()
	{
		instance.removeAll();
		instance.add(login_label);
		instance.add(login);
		instance.add(password_label);
		instance.add(password);
		instance.add(authurl_label);
		instance.add(authurl);
		instance.add(authext_label);
		instance.add(authext);
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

	public LauncherPanel()
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
		switch (mode)
		{
			case MODE_LOGIN:
				LauncherUtils.drawTransparentRect(g2d, 510, 10, 325, 455);
				break;
			case MODE_DOWNLOAD_REQUEST:
				LauncherUtils.drawText(g2d, 100, 100, "Клиент не был найден", Color.WHITE, 48);
				LauncherUtils.drawText(g2d, 100, 135, "Запуск клиента невозможен без наличия самого клиента.", Color.GRAY, 16);
				LauncherUtils.drawText(g2d, 100, 155, "Закачать недостоющие файлы используя интернет?", Color.GRAY, 16);
				LauncherUtils.drawText(g2d, 100, 175, "(могут быть удалены установленные моды и аддоны)", Color.GRAY, 16);
				break;
			case MODE_DOWNLOAD_BEGIN:
				LauncherUtils.drawText(g2d, 100, 100, "Клиент закачивается...", Color.WHITE, 48);
				LauncherUtils.drawText(g2d, 100, 135, "Текущий файл: " + currentFile, Color.GRAY, 16);
				LauncherUtils.drawText(g2d, 100, 155, "Размер файла: " + currentSize + " байт", Color.GRAY, 16);
				LauncherUtils.drawText(g2d, 100, 175, "Закачано: " + currentByte + " байт", Color.GRAY, 16);
				LauncherUtils.drawText(g2d, 100, 195, "Состояние: " + currentStat, Color.GRAY, 16);
				LauncherUtils.drawTransparentRect(g2d, 100, 350, 650, 35);
				if (currentByte == 0 || currentSize == 0) break;
				LauncherUtils.drawProgressBar(g2d, 105, 355, currentByte * 640 / currentSize, 25);
				break;
			case MODE_MODMM:
				LauncherUtils.drawText(g2d, 5, 20, "Версия клиента: some...", Color.WHITE, 16);
				break;
		}
	}
}