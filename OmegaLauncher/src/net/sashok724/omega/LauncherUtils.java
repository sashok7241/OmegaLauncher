package net.sashok724.omega;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.imageio.ImageIO;

public class LauncherUtils implements LauncherConstants
{
	public static final File minecraftDir = getMinecraftDir();

	public static boolean checkClient()
	{
		if (!new File(minecraftDir, "bin").exists()) return false;
		if (!new File(minecraftDir, "bin/lwjgl.jar").exists()) return false;
		if (!new File(minecraftDir, "bin/jinput.jar").exists()) return false;
		if (!new File(minecraftDir, "bin/minecraft.jar").exists()) return false;
		if (!new File(minecraftDir, "bin/lwjgl_util.jar").exists()) return false;
		if (!new File(minecraftDir, "bin/natives").exists()) return false;
		return true;
	}

	public static void drawBackground(Graphics2D g)
	{
		int backgroundWidth = IMG_BACKGROUND.getWidth(), backgroundHeight = IMG_BACKGROUND.getHeight();
		for (int x = 0; x < LauncherContentPane.instance.getWidth(); x += backgroundWidth)
			for (int y = 0; y < LauncherContentPane.instance.getHeight(); y += backgroundHeight)
				g.drawImage(IMG_BACKGROUND, x, y, null);
	}

	public static void drawIcon(Graphics2D g, int x, int y, BufferedImage img)
	{
		g.drawImage(img, x, y, null);
	}

	public static void drawText(Graphics2D g, int x, int y, String text, Color c)
	{
		g.setFont(FONT_MC.deriveFont(16F));
		g.setColor(SHADOW);
		g.drawString(text, x + 2, y + 2);
		g.setColor(c);
		g.drawString(text, x, y);
	}

	public static void drawTransparentRect(Graphics2D g, int x, int y, int w, int h)
	{
		g.setColor(new Color(0, 0, 0, 0.5F));
		g.fillRect(x, y, w, h);
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

	public static void pollServer(LauncherServer server)
	{
		Socket soc = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try
		{
			server.status = 0;
			soc = new Socket();
			soc.setSoTimeout(3000);
			soc.setTcpNoDelay(true);
			soc.setTrafficClass(18);
			soc.connect(new InetSocketAddress(server.address, server.port), 3000);
			dis = new DataInputStream(soc.getInputStream());
			dos = new DataOutputStream(soc.getOutputStream());
			dos.write(254);
			if (dis.read() != 255) throw new IOException("Bad message");
			String[] string = readString(dis, 256).split("§");
			server.status = 1;
			server.curplayers = string[1];
			server.maxplayers = string[2];
			server.motd = string[0];
		} catch (Exception e)
		{
			server.status = 2;
			server.curplayers = "???";
			server.maxplayers = "???";
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