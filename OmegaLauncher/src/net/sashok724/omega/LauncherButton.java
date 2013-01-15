package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ButtonModel;
import javax.swing.JButton;

public class LauncherButton extends JButton implements LauncherConstants
{
	public static final long serialVersionUID = 1L;

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
	}

	@Override
	protected void paintComponent(Graphics g1)
	{
		ButtonModel buttonModel = getModel();
		Graphics2D g = (Graphics2D) g1.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth(), h = getHeight();
		if (!isEnabled()) g.drawImage(IMG_BUTTON_LCK, 0, 0, w, h, null);
		else if (buttonModel.isRollover()) g.drawImage(IMG_BUTTON_SEL, 0, 0, w, h, null);
		else g.drawImage(IMG_BUTTON_DEF, 0, 0, w, h, null);
		g.dispose();
		super.paintComponent(g1);
	}
}
