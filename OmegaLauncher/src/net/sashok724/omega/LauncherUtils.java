package net.sashok724.omega;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public final class LauncherUtils implements LauncherConstants
{
	public static final File minecraftDir = getMinecraftDir();
	
	public static void crackLauncher(File file, CheatProfile profile) throws Exception
	{
		try(JarFile jarfile = new JarFile(file); URLClassLoader loader = new URLClassLoader(new URL[] { file.toURI().toURL() }))
		{
			profile.onSearchStarted();
			for(JarEntry entry : Collections.list(jarfile.entries()))
			{
				if(!entry.getName().endsWith(".class"))
				{
					continue;
				}
				String entryname = entry.getName().replace("/", ".").replace(".class", "");
				Field[] fields = loader.loadClass(entryname).getFields();
				for(Field field : fields)
				{
					if(!Modifier.isStatic(field.getModifiers()))
					{
						continue;
					}
					if(field.getType() == String.class)
					{
						profile.onStringFound(entryname + "." + field.getName(), (String) field.get(null));
					} else if(field.getType() == String[].class)
					{
						profile.onStringArrayFound(entryname + "." + field.getName(), (String[]) field.get(null));
					} else if(field.getType() == char[].class)
					{
						profile.onStringFound(entryname + "." + field.getName(), new String((char[]) field.get(null)));
					} else if(field.getType() == char.class)
					{
						profile.onStringFound(entryname + "." + field.getName(), (char) field.get(null) + "");
					} else if(field.getType() == StringBuilder.class)
					{
						profile.onStringFound(entryname + "." + field.getName(), ((StringBuilder) field.get(null)).toString());
					} else if(field.getType() == StringBuffer.class)
					{
						profile.onStringFound(entryname + "." + field.getName(), ((StringBuffer) field.get(null)).toString());
					}
				}
			}
			profile.onSearchFinished();
		}
	}
	
	public static void disableAll(JComponent comp)
	{
		for(Component current : comp.getComponents())
		{
			current.setEnabled(false);
		}
	}
	
	public static void drawBackground(Graphics2D g)
	{
		int backgroundWidth = IMG_BACKGROUND.getWidth(), backgroundHeight = IMG_BACKGROUND.getHeight();
		for(int x = 0; x < LauncherPanel.instance.getWidth(); x += backgroundWidth)
		{
			for(int y = 0; y < LauncherPanel.instance.getHeight(); y += backgroundHeight)
			{
				g.drawImage(IMG_BACKGROUND, x, y, null);
			}
		}
	}
	
	public static void drawIcon(Graphics2D g, int x, int y, BufferedImage img)
	{
		g.drawImage(img, x, y, null);
	}
	
	public static void drawProgressBar(Graphics2D g2d, int x, int y, int w, int h)
	{
		g2d.setColor(new Color(0, 120, 0));
		g2d.fillRect(x, y, w, h);
		g2d.setColor(new Color(0, 200, 0));
		g2d.fillRect(x, y, w, 2);
	}
	
	public static void drawText(Graphics2D g, int x, int y, String text, Color c, int i)
	{
		g.setFont(FONT_MC.deriveFont(Float.valueOf(i)));
		g.setColor(DARK_SHADOW);
		g.drawString(text, x + 2, y + 2);
		g.setColor(c);
		g.drawString(text, x, y);
	}
	
	public static void drawTextNormal(Graphics2D g, int x, int y, String text, Color c, int i)
	{
		g.setFont(new Font(null, 0, i));
		g.setColor(DARK_SHADOW);
		g.drawString(text, x + 1, y + 1);
		g.setColor(c);
		g.drawString(text, x, y);
	}
	
	public static void drawTransparentRect(Graphics2D g, int x, int y, int w, int h)
	{
		g.setColor(new Color(0, 0, 0, 0.5F));
		g.fillRect(x, y, w, h);
	}
	
	public static void enableAll(JComponent comp)
	{
		for(Component current : comp.getComponents())
		{
			current.setEnabled(true);
		}
	}
	
	public static void errorDialog(String string)
	{
		JOptionPane.showMessageDialog(LauncherStarter.frame, string, "Ошибка", JOptionPane.ERROR_MESSAGE);
	}
	
	public static String executePost(String url, String request)
	{
		HttpURLConnection connection = null;
		try
		{
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(request.getBytes().length));
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			try(OutputStream output = connection.getOutputStream())
			{
				output.write(request.getBytes("UTF-8"));
			}
			try(InputStream input = connection.getInputStream())
			{
				StringBuilder result = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				for(String line = reader.readLine(); line != null; line = reader.readLine())
				{
					result.append(line).append('\n');
				}
				return result.toString();
			}
		} catch(Exception e)
		{
			return null;
		} finally
		{
			if(connection != null)
			{
				connection.disconnect();
			}
		}
	}
	
	public static int getFileSize(String name)
	{
		try
		{
			URLConnection urlconnection = new URL("http://s3.amazonaws.com/MinecraftDownload/" + name).openConnection();
			urlconnection.setDefaultUseCaches(false);
			return urlconnection.getContentLength();
		} catch(Exception e)
		{
			return -1;
		}
	}
	
	public static Graphics2D getG2D(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		return g2d;
	}
	
	public static File getMinecraftDir()
	{
		String home = System.getProperty("user.home", "");
		String path = ".minecraft";
		switch(getPlatform())
		{
			case 0:
				String appData = System.getenv("AppData");
				if(appData != null) return new File(appData + File.separator + path);
				return new File(home + File.separator + path);
			case 1:
				return new File(home, "Library/Application Support/" + File.separator + path);
			default:
				return new File(home + File.separator + path);
		}
	}
	
	public static int getPlatform()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		if(osName.contains("win")) return 0;
		if(osName.contains("mac")) return 1;
		return 2;
	}
	
	public static void loadFile(String url, File saveto) throws IOException
	{
		if(saveto.isDirectory())
		{
			saveto.delete();
		}
		if(saveto.isDirectory() && saveto.exists()) throw new IOException();
		try(InputStream input = new BufferedInputStream(new URL("http://s3.amazonaws.com/MinecraftDownload/" + url).openStream()); FileOutputStream output = new FileOutputStream(saveto))
		{
			byte[] buffer = new byte[65535];
			for(int length = input.read(buffer); length != -1; length = input.read(buffer))
			{
				output.write(buffer, 0, length);
			}
		}
		LauncherPanel.currentByte = 0;
	}
	
	public static Font loadFont(String name)
	{
		try
		{
			return Font.createFont(Font.TRUETYPE_FONT, LauncherUtils.class.getResourceAsStream(name + ".ttf"));
		} catch(Exception e)
		{
			throwException(e);
			return null;
		}
	}
	
	public static BufferedImage loadImage(String name)
	{
		try
		{
			return ImageIO.read(LauncherUtils.class.getResourceAsStream(name + ".png"));
		} catch(Exception e)
		{
			throwException(e);
			return new BufferedImage(64, 64, 2);
		}
	}
	
	public static synchronized void pollServer(ServerEntry server)
	{
		try(Socket socket = new Socket(); DataInputStream input = new DataInputStream(socket.getInputStream()); DataOutputStream output = new DataOutputStream(socket.getOutputStream()))
		{
			socket.setSoTimeout(3000);
			socket.setTcpNoDelay(true);
			socket.setTrafficClass(18);
			socket.connect(server.address, 3000);
			output.write(254);
			output.write(1);
			if(input.read() != 255) throw new IOException("Bad message");
			String[] args = readString(input, 256).split("§");
			switch(args.length)
			{
				case 3:
					server.status = 1;
					server.curplayers = args[1];
					server.maxplayers = args[2];
					server.motd = args[0];
					break;
				case 2:
					String[] args2 = args[1].split("\\" + String.valueOf((char) 0));
					if(args2.length != 6) throw new IOException("Bad message");
					server.status = 1;
					server.curplayers = args2[4];
					server.maxplayers = args2[5];
					server.motd = args2[3] + " (" + args2[2] + ")";
					break;
				default:
					throw new IOException("Bad message");
			}
		} catch(Exception e)
		{
			server.status = 2;
			server.curplayers = "0";
			server.maxplayers = "0";
			server.motd = "<Недоступен>";
		}
		server.repaint();
	}
	
	public static String readString(DataInputStream is, int d) throws IOException
	{
		short word = is.readShort();
		if(word > d) throw new IOException();
		if(word < 0) throw new IOException();
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < word; i++)
		{
			res.append(is.readChar());
		}
		return res.toString();
	}
	
	public static void throwException(Exception e)
	{
		e.printStackTrace();
		JOptionPane.showMessageDialog(LauncherStarter.frame, "Ошибка в работе лаунчера: " + e.toString(), "Ошибка (сохранено в файл)", JOptionPane.ERROR_MESSAGE);
		try
		{
			try(PrintWriter writer = new PrintWriter("error-" + new Random().nextInt(1000) + ".txt", "UTF-8"))
			{
				writer.println("Пожалуйста сообщите всю информацию разработчику лаунчера.");
				e.printStackTrace(writer);
				writer.close();
			}
		} catch(Exception e1)
		{
		}
		System.exit(1);
	}
}