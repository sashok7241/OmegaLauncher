package net.sashok724.omega;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public final class LauncherPanel extends JPanel
{
	public static final long serialVersionUID = 1L;
	public static LauncherPanel instance;
	public static int currentByte = 0, currentSize = 0;
	public static String currentStat = "<Unknown>", currentFile = "<Unknown>", username, session;
	public static ArrayList<String> requiredFiles;
	public static ArrayList<JComponent> loginElements = new ArrayList<JComponent>();
	public static ArrayList<JComponent> analyzeElements = new ArrayList<JComponent>();
	public static ArrayList<JComponent> addserverElements = new ArrayList<JComponent>();
	// ========================= LOGIN ELEMENTS ===========================================
	public static ServerLists serverPanel = new ServerLists(new Rectangle(5, 5, 600, 515));
	public static LauncherButton addserver = new LauncherButton("Добавить сервер", 610, 5).setW(245);
	public static LauncherButton settings = new LauncherButton("Настройки", 610, 50).setW(245);
	public static LauncherButton analyze = new LauncherButton("Анализ лаунчера", 610, 95).setW(245);
	public static LauncherButton offline = new LauncherButton("Оффлайн", 610, 480).setW(245);
	// ========================= ANALYZE ELEMENTS =========================================
	public static JTextArea analyzePane = new JTextArea();
	public static JScrollPane analyzeScroller = new JScrollPane(analyzePane);
	public static LauncherButton analyzeClose = new LauncherButton("Закрыть результаты", 250, 475);
	// ========================= ADDSERVER ELEMENTS =======================================
	public static LauncherButton addserver_cancel = new LauncherButton("Вернуться", 250, 425);
	public static LauncherButton addserver_accept = new LauncherButton("Добавить сервер", 250, 475);
	public static LauncherTextfield addserver_name = new LauncherTextfield(null, 200, 10, "", false).setW(650);
	public static LauncherTextfield addserver_addr = new LauncherTextfield(null, 200, 60, "", true).setW(650);
	public static LauncherTextfield addserver_auth = new LauncherTextfield(null, 200, 110, "", true).setW(650);
	public static LauncherTextfield addserver_dir = new LauncherTextfield(null, 200, 160, "", false).setW(650);
	public static LauncherTextfield addserver_logn = new LauncherTextfield(null, 200, 210, "", false).setW(650);
	public static LauncherPassfield addserver_pass = new LauncherPassfield(null, 200, 260).setW(650);
	public static LauncherLabel addserver_name_title = new LauncherLabel("Имя сервера:", 30, 35);
	public static LauncherLabel addserver_addr_title = new LauncherLabel("Адрес сервера:", 30, 35 + 50);
	public static LauncherLabel addserver_auth_title = new LauncherLabel("Параметры auth:", 30, 35 + 50 * 2);
	public static LauncherLabel addserver_dir_title = new LauncherLabel("Папка:", 30, 35 + 50 * 3);
	public static LauncherLabel addserver_logn_title = new LauncherLabel("Логин:", 30, 35 + 50 * 4);
	public static LauncherLabel addserver_pass_title = new LauncherLabel("Пароль:", 30, 35 + 50 * 5);
	// ========================= SOME OTHER STUFF =========================================
	static
	{
		loginElements.add(serverPanel);
		loginElements.add(offline);
		loginElements.add(analyze);
		loginElements.add(addserver);
		loginElements.add(settings);
		analyzePane.setFont(new Font("Courier New", 0, 12));
		analyzePane.setBackground(Color.BLACK);
		analyzePane.setForeground(Color.GREEN);
		analyzeScroller.setBounds(10, 10, 840, 450);
		analyzeElements.add(analyzeScroller);
		analyzeElements.add(analyzeClose);
		addserverElements.add(addserver_accept);
		addserverElements.add(addserver_cancel);
		addserverElements.add(addserver_name_title);
		addserverElements.add(addserver_name);
		addserverElements.add(addserver_addr_title);
		addserverElements.add(addserver_addr);
		addserverElements.add(addserver_auth_title);
		addserverElements.add(addserver_auth);
		addserverElements.add(addserver_dir_title);
		addserverElements.add(addserver_dir);
		addserverElements.add(addserver_logn_title);
		addserverElements.add(addserver_logn);
		addserverElements.add(addserver_pass_title);
		addserverElements.add(addserver_pass);
	}

	public LauncherPanel()
	{
		instance = this;
		setLayout(null);
		setPreferredSize(new Dimension(850, 515));
		applyElements(loginElements);
		serverPanel.addServer(new ServerEntry("Лёнечка лапочка", "realminecraft.ru:25565", "rmc", "hummer,http://realminecraft.ru/launcher/action.php", "sashok724", "123456"));
		serverPanel.addServer(new ServerEntry("Minecraft-Москва Free 3", "game.minecraft-moscow.ru:25503", "moscow", "notch,http://minecraft-moscow.ru/prox/auth/auth.php", "sashok724", "someotherpassword"));
	}

	public void applyElements(ArrayList<JComponent> components)
	{
		instance.removeAll();
		for (JComponent current : components)
			instance.add(current);
		instance.repaint();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = LauncherUtils.getG2D(g);
		LauncherUtils.drawBackground(g2d);
	}
}