package net.sashok724.omega;

import java.awt.Font;
import java.awt.image.BufferedImage;

public interface LauncherConstants extends LauncherLocalizations
{
	// Строковые константы
	public static final String VERSION = "0.1a";
	// Изображения
	public static final BufferedImage IMG_BACKGROUND = LauncherUtils.loadImage("background");
	public static final BufferedImage IMG_TEXTFIELD = LauncherUtils.loadImage("textfield");
	public static final BufferedImage IMG_TEXTFIELD_SEL = LauncherUtils.loadImage("textfield_sel");
	public static final Font FONT_MC = LauncherUtils.loadFont("textfield");
}