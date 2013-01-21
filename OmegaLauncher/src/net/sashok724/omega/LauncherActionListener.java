package net.sashok724.omega;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class LauncherActionListener implements ActionListener, LauncherConstants
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
			String login = LauncherPanel.login.getText(), password = new String(LauncherPanel.password.getPassword());
			String authurl = LauncherPanel.authurl.getText(), authext = LauncherPanel.authext.getText();
			if (login.length() < 3)
			{
				LauncherPanel.login.setForeground(Color.RED);
				return;
			} else LauncherPanel.login.setForeground(Color.WHITE);
			if (password.length() < 3)
			{
				LauncherPanel.password.setForeground(Color.RED);
				return;
			} else LauncherPanel.password.setForeground(Color.WHITE);
			if (!authurl.startsWith("http://"))
			{
				LauncherPanel.authurl.setForeground(Color.RED);
				return;
			} else LauncherPanel.authurl.setForeground(Color.WHITE);
			if (!authext.startsWith("notch:") && !authext.startsWith("sashok:"))
			{
				LauncherPanel.authext.setForeground(Color.RED);
				return;
			}
			if (authext.startsWith("notch"))
			{
				String[] splitted = authext.split(":");
				if (splitted.length != 2)
				{
					LauncherPanel.authext.setForeground(Color.RED);
					return;
				}
				new LauncherAuthNotch(splitted[1]);
			}
			if (authext.startsWith("sashok"))
			{
				String[] splitted = authext.split(":");
				if (splitted.length != 3)
				{
					LauncherPanel.authext.setForeground(Color.RED);
					return;
				}
				new LauncherAuthSashok(splitted[2], splitted[1].replaceAll(" ", "").toLowerCase());
			}
			LauncherUtils.disableAll(LauncherPanel.instance);
			LauncherPanel.authext.setForeground(Color.WHITE);
		} else if (e.getSource() == LauncherPanel.offline_button) LauncherPanel.addModManagerComponents(LauncherPanel.login.getText(), "123456");
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
			} else LauncherPanel.serverPort.setForeground(Color.WHITE);
			if (name.length() < 3)
			{
				LauncherPanel.serverName.setForeground(Color.RED);
				return;
			} else LauncherPanel.serverName.setForeground(Color.WHITE);
			if (addr.length() < 4)
			{
				LauncherPanel.serverAddr.setForeground(Color.RED);
				return;
			} else LauncherPanel.serverAddr.setForeground(Color.WHITE);
			LauncherPanel.servers.add(new LauncherServer(name, addr, Integer.parseInt(port)));
			LauncherPanel.saveServers();
			LauncherPanel.addLoginElements();
		} else if (e.getSource() == LauncherPanel.addserver_cancel) LauncherPanel.addLoginElements();
		else if (e.getSource() == LauncherPanel.download_cancel) LauncherPanel.addLoginElements();
		else if (e.getSource() == LauncherPanel.download_accept) LauncherPanel.addDownloadElements();
	}
}