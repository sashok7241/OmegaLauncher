package net.sashok724.omega;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
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
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class LauncherUtils implements LauncherConstants
{
	public static final File minecraftDir = getMinecraftDir();

	public static ArrayList<String> checkClient()
	{
		ArrayList<File> files = new ArrayList<File>();
		File bin = new File(minecraftDir, "bin/");
		files.add(new File(bin, "lwjgl.jar"));
		files.add(new File(bin, "lwjgl_util.jar"));
		files.add(new File(bin, "jinput.jar"));
		files.add(new File(bin, "minecraft.jar"));
		ArrayList<String> result = new ArrayList<String>();
		for (File file : files)
			if (!file.exists()) result.add(file.getName());
		return result;
	}
	
	public static void updateNatives() throws Exception
	{
		File natives = new File(minecraftDir, "bin/natives");
		natives.mkdirs();
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File(natives, "jinput-dx8.dll"));
		files.add(new File(natives, "jinput-dx8_64.dll"));
		files.add(new File(natives, "jinput-raw.dll"));
		files.add(new File(natives, "jinput-raw_64.dll"));
		files.add(new File(natives, "lwjgl.dll"));
		files.add(new File(natives, "lwjgl64.dll"));
		files.add(new File(natives, "OpenAL32.dll"));
		files.add(new File(natives, "OpenAL64.dll"));
		for(File current : files)
		{
			if(current.exists()) continue;
			FileOutputStream outstream = new FileOutputStream(current);
			InputStream instream = LauncherUtils.class.getResourceAsStream("/net/sashok724/natives/" + current.getName());
			int buffer;
			while((buffer = instream.read()) != -1)
			{
				outstream.write(buffer);
			}
			outstream.close();
		}
	}

	public static void disableAll(JComponent comp)
	{
		for (Component current : comp.getComponents())
			current.setEnabled(false);
	}

	public static void drawBackground(Graphics2D g)
	{
		int backgroundWidth = IMG_BACKGROUND.getWidth(), backgroundHeight = IMG_BACKGROUND.getHeight();
		for (int x = 0; x < LauncherPanel.instance.getWidth(); x += backgroundWidth)
			for (int y = 0; y < LauncherPanel.instance.getHeight(); y += backgroundHeight)
				g.drawImage(IMG_BACKGROUND, x, y, null);
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
		Color prev = g.getColor();
		g.setFont(FONT_MC.deriveFont(Float.valueOf(i)));
		g.setColor(DARK_SHADOW);
		g.drawString(text, x + 2, y + 2);
		g.setColor(c);
		g.drawString(text, x, y);
		g.setColor(prev);
	}

	public static void drawTransparentRect(Graphics2D g, int x, int y, int w, int h)
	{
		g.setColor(new Color(0, 0, 0, 0.5F));
		g.fillRect(x, y, w, h);
	}

	public static void enableAll(JComponent comp)
	{
		for (Component current : comp.getComponents())
			current.setEnabled(true);
	}

	public static String executePost(String url, String request)
	{
		HttpURLConnection connection = null;
		try
		{
			connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(request.getBytes().length));
			connection.setRequestProperty("Content-Language", "ru-RU");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			DataOutputStream outstream = new DataOutputStream(connection.getOutputStream());
			outstream.writeBytes(request);
			outstream.flush();
			outstream.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null)
			{
				buffer.append(line);
				buffer.append('\r');
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e)
		{
			return null;
		} finally
		{
			if (connection != null) connection.disconnect();
		}
	}

	public static int getFileSize(String name)
	{
		try
		{
			URLConnection urlconnection = new URL("http://s3.amazonaws.com/MinecraftDownload/" + name).openConnection();
			urlconnection.setDefaultUseCaches(false);
			return urlconnection.getContentLength();
		} catch (Exception e)
		{
			return -1;
		}
	}

	public static File getMinecraftDir()
	{
		String home = System.getProperty("user.home", "");
		String path = ".minecraft";
		switch (getPlatform())
		{
			case 0:
				String appData = System.getenv("AppData");
				if (appData != null) return new File(appData + File.separator + path);
				else return new File(home + File.separator + path);
			case 1:
				return new File(home, "Library/Application Support/" + File.separator + path);
			default:
				return new File(home + File.separator + path);
		}
	}

	public static int getPlatform()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) return 0;
		if (osName.contains("mac")) return 1;
		return 2;
	}

	public static void loadFile(String url, File saveto) throws IOException
	{
		if (saveto.isDirectory()) saveto.delete();
		if (saveto.isDirectory() && saveto.exists()) throw new IOException();
		InputStream is = new BufferedInputStream(new URL("http://s3.amazonaws.com/MinecraftDownload/" + url).openStream());
		FileOutputStream fos = new FileOutputStream(saveto);
		int bs = 0;
		byte[] buffer = new byte[65535];
		while ((bs = is.read(buffer, 0, buffer.length)) != -1)
		{
			LauncherPanel.currentByte += bs;
			fos.write(buffer, 0, bs);
			LauncherPanel.instance.repaint();
		}
		is.close();
		fos.close();
		LauncherPanel.currentByte = 0;
	}

	public static Font loadFont(String name)
	{
		try
		{
			return Font.createFont(Font.TRUETYPE_FONT, LauncherUtils.class.getResourceAsStream(name + ".ttf"));
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static BufferedImage loadImage(String name)
	{
		try
		{
			return ImageIO.read(LauncherUtils.class.getResourceAsStream(name + ".png"));
		} catch (Exception e)
		{
			return new BufferedImage(64, 64, 2);
		}
	}

	public static synchronized void pollServer(LauncherServer server)
	{
		Socket soc = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try
		{
			soc = new Socket();
			soc.setSoTimeout(3000);
			soc.setTcpNoDelay(true);
			soc.setTrafficClass(18);
			soc.connect(new InetSocketAddress(server.address, server.port), 3000);
			dis = new DataInputStream(soc.getInputStream());
			dos = new DataOutputStream(soc.getOutputStream());
			dos.write(254);
			dos.write(1);
			if (dis.read() != 255) throw new IOException("Bad message");
			String[] args = readString(dis, 256).split("§");
			switch (args.length)
			{
				case 3:
					server.status = 1;
					server.curplayers = args[1];
					server.maxplayers = args[2];
					server.motd = args[0];
					break;
				case 2:
					String[] args2 = args[1].split("\\" + String.valueOf((char) 0));
					if (args2.length != 6) throw new IOException("Bad message");
					server.status = 1;
					server.curplayers = args2[4];
					server.maxplayers = args2[5];
					server.motd = args2[3] + " (" + args2[2] + ")";
					break;
				default:
					throw new IOException("Bad message");
			}
		} catch (Exception e)
		{
			server.status = 2;
			server.curplayers = "0";
			server.maxplayers = "0";
			server.motd = "<Недоступен>";
		} finally
		{
			try
			{
				dis.close();
			} catch (Exception e)
			{
			}
			try
			{
				dos.close();
			} catch (Exception e)
			{
			}
			try
			{
				soc.close();
			} catch (Exception e)
			{
			}
		}
	}

	public static String readString(DataInputStream is, int d) throws IOException
	{
		short word = is.readShort();
		if (word > d) throw new IOException();
		if (word < 0) throw new IOException();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < word; i++)
			res.append(is.readChar());
		return res.toString();
	}
}