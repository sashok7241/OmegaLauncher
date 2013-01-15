package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class LauncherUtils implements LauncherConstants
{
	public static void drawBackground(Graphics2D g)
	{
		int backgroundWidth = IMG_BACKGROUND.getWidth(), backgroundHeight = IMG_BACKGROUND.getHeight();
		for (int x = 0; x < LauncherContentPane.instance.getWidth(); x += backgroundWidth)
			for (int y = 0; y < LauncherContentPane.instance.getHeight(); y += backgroundHeight)
				g.drawImage(IMG_BACKGROUND, x, y, null);
	}

	public static void drawTransparentRect(Graphics2D g, int x, int y, int w, int h)
	{
		g.setColor(new Color(0, 0, 0, 0.5F));
		g.fillRect(x, y, w, h);
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
}