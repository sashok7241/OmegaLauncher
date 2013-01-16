package net.sashok724.omega;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public interface LauncherConstants extends LauncherLocalizations
{
	// Строковые константы
	public static final String VERSION = "0.1a";
	// Изображения
	public static final BufferedImage IMG_BACKGROUND = LauncherUtils.loadImage("background");
	public static final BufferedImage IMG_TEXTFIELD_DEF = LauncherUtils.loadImage("textfield_def");
	public static final BufferedImage IMG_TEXTFIELD_SEL = LauncherUtils.loadImage("textfield_sel");
	public static final BufferedImage IMG_BUTTON_DEF = LauncherUtils.loadImage("button_def");
	public static final BufferedImage IMG_BUTTON_LCK = LauncherUtils.loadImage("button_lck");
	public static final BufferedImage IMG_BUTTON_SEL = LauncherUtils.loadImage("button_sel");
	public static final BufferedImage IMG_FAVICON = LauncherUtils.loadImage("favicon");
	public static final BufferedImage IMG_SERVER_POLL = LauncherUtils.loadImage("server_poll");
	public static final BufferedImage IMG_SERVER_ONLINE = LauncherUtils.loadImage("server_on");
	public static final BufferedImage IMG_SERVER_OFFLINE = LauncherUtils.loadImage("server_off");
	// Шрифты
	public static final Font FONT_MC = LauncherUtils.loadFont("textfield");
	public static final Color SHADOW = new Color(20, 20, 20);
	// Режимы
	public static final int MODE_LOGIN = 0, MODE_DOWNLOAD_BEGIN = 1;
}