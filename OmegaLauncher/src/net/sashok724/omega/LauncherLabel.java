package net.sashok724.omega;

import java.awt.Color;

import javax.swing.JLabel;

public class LauncherLabel extends JLabel implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	
	public LauncherLabel(String label, int x, int y, int w, int h)
	{
		super(label);
		setOpaque(false);
		setBounds(x, y, w, h);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFont(FONT_MC.deriveFont(16F));
	}
}