package net.sashok724.omega;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class LauncherServer extends JComponent implements LauncherConstants
{
	public static final long serialVersionUID = 1L;
	public String address;
	public int port;

	public LauncherServer(String _address, int _port, int w)
	{
		address = _address;
		port = _port;
		setBounds(5, 5, w - 10, 100);
	}

	@Override
	public void paintComponent(Graphics g1)
	{
		Graphics2D g = (Graphics2D) g1;
		LauncherUtils.drawTransparentRect(g, 0, 0, getWidth(), getHeight());
	}
}