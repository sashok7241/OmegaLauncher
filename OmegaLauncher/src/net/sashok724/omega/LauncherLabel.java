package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class LauncherLabel extends JLabel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;

	public LauncherLabel(String label, int x, int y)
	{
		super(label);
		setOpaque(false);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFont(FONT_MC.deriveFont(16F));
		setBounds(x, y, getFontMetrics(getFont()).stringWidth(getText()), getFontMetrics(getFont()).getHeight());
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setFont(getFont());
		g.setColor(SHADOW);
		g.drawString(getText(), 2, 19);
		super.paintComponent(g);
	}

	public LauncherLabel resetC()
	{
		setForeground(Color.WHITE);
		return this;
	}
}