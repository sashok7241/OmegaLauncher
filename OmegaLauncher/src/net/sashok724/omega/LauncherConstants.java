package net.sashok724.omega;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public interface LauncherConstants
{
	// Строковые константы
	public static final String VERSION = "0.2a", delim = "<:>", exdel = "<:n:>";
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
	public static final BufferedImage IMG_DELETE_DEF = LauncherUtils.loadImage("delete_def");
	public static final BufferedImage IMG_DELETE_SEL = LauncherUtils.loadImage("delete_sel");
	public static final BufferedImage IMG_ENTER_DEF = LauncherUtils.loadImage("enter_def");
	public static final BufferedImage IMG_ENTER_SEL = LauncherUtils.loadImage("enter_sel");
	// Шрифты
	public static final Font FONT_MC = LauncherUtils.loadFont("textfield");
	public static final Color SHADOW = Color.DARK_GRAY, DARK_SHADOW = new Color(0x151515);
	// Режимы
	public static final int MODE_LOGIN = 0, MODE_DOWNLOAD_REQUEST = 1, MODE_DOWNLOAD_BEGIN = 2, MODE_MODMM = 3;
}