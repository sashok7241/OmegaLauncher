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
		if (e.getSource() == LauncherPanel.login_button)
		{
			// TODO
		} else if (e.getSource() == LauncherPanel.offline_button) LauncherPanel.addModManagerComponents();
		else if (e.getSource() == LauncherPanel.addserver_button) LauncherPanel.addServerAdderElements();
		else if (e.getSource() == LauncherPanel.addserver_accept)
		{
			String addr = LauncherPanel.serverAddr.getText();
			String port = LauncherPanel.serverPort.getText();
			String name = LauncherPanel.serverName.getText();
			if (!checkInteger(port) || Integer.parseInt(port) > 65535 || Integer.parseInt(port) < 0)
			{
				LauncherPanel.serverPort.setForeground(Color.RED);
				return;
			}
			if (name.length() < 3)
			{
				LauncherPanel.serverName.setForeground(Color.RED);
				return;
			}
			if (addr.length() < 4)
			{
				LauncherPanel.serverAddr.setForeground(Color.RED);
				return;
			}
			LauncherPanel.servers.add(new LauncherServer(name, addr, Integer.parseInt(port)));
			LauncherPanel.saveServers();
			LauncherPanel.addLoginElements();
		} else if (e.getSource() == LauncherPanel.addserver_cancel) LauncherPanel.addLoginElements();
		else if (e.getSource() == LauncherPanel.download_cancel) LauncherPanel.addLoginElements();
		else if (e.getSource() == LauncherPanel.download_accept) LauncherPanel.addDownloadElements();
	}
}