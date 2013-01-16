package net.sashok724.omega;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherActionListener implements ActionListener, LauncherConstants
{
	public static boolean checkInteger(String text)
	{
		try
		{
			Integer.parseInt(text);
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == LauncherContentPane.login_button)
		{
			// TODO
		} else if (e.getSource() == LauncherContentPane.offline_button)
		{
			// TODO
		} else if (e.getSource() == LauncherContentPane.addserver_button) LauncherContentPane.addServerAdderElements();
		else if (e.getSource() == LauncherContentPane.addserver_accept)
		{
			String addr = LauncherContentPane.serverAddr.getText();
			String port = LauncherContentPane.serverPort.getText();
			String name = LauncherContentPane.serverName.getText();
			if (!checkInteger(port) || Integer.parseInt(port) > 65535 || Integer.parseInt(port) < 0)
			{
				LauncherContentPane.serverPort.setForeground(Color.RED);
				return;
			}
			if (name.length() < 3)
			{
				LauncherContentPane.serverName.setForeground(Color.RED);
				return;
			}
			if (addr.length() < 4)
			{
				LauncherContentPane.serverAddr.setForeground(Color.RED);
				return;
			}
			LauncherContentPane.servers.add(new LauncherServer(name, addr, Integer.parseInt(port)));
			LauncherContentPane.saveServers();
			LauncherContentPane.addLoginElements();
		} else if (e.getSource() == LauncherContentPane.addserver_cancel) LauncherContentPane.addLoginElements();
	}
}