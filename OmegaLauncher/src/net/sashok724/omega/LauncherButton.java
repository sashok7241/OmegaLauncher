package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ButtonModel;
import javax.swing.JButton;

public final class LauncherButton extends JButton implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public BufferedImage texture_def = LauncherConstants.IMG_BUTTON_DEF, texture_sel = LauncherConstants.IMG_BUTTON_SEL;
	
	public LauncherButton(String name, int x, int y)
	{
		super(name);
		setBounds(x, y, IMG_BUTTON_DEF.getWidth(), IMG_BUTTON_DEF.getHeight());
		setOpaque(false);
		setFocusable(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setFont(FONT_MC.deriveFont(16F));
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		addActionListener(new LauncherActionListener());
	}
	
	@Override
	public void paintComponent(Graphics g1)
	{
		ButtonModel buttonModel = getModel();
		Graphics2D g = LauncherUtils.getG2D(g1);
		int width = getFontMetrics(getFont()).stringWidth(getText());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth(), h = getHeight();
		if(!isEnabled())
		{
			g.drawImage(IMG_BUTTON_LCK, 0, 0, w, h, null);
		} else if(buttonModel.isRollover())
		{
			g.drawImage(texture_sel, 0, 0, w, h, null);
		} else
		{
			g.drawImage(texture_def, 0, 0, w, h, null);
		}
		g.setFont(getFont());
		g.setColor(SHADOW);
		g.drawString(getText(), getWidth() / 2 - width / 2 + 2, 28);
		g.dispose();
		super.paintComponent(g1);
	}
	
	public LauncherButton setTextures(BufferedImage def, BufferedImage sel)
	{
		texture_def = def;
		texture_sel = sel;
		setSize(def.getWidth(), def.getHeight());
		return this;
	}
	
	public LauncherButton setW(int i)
	{
		setSize(i, getHeight());
		return this;
	}
}