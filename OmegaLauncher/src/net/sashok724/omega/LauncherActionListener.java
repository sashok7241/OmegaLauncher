package net.sashok724.omega;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public final class LauncherActionListener implements ActionListener, LauncherConstants
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == LauncherPanel.analyzeClose) LauncherPanel.instance.applyElements(LauncherPanel.loginElements);
		else if (e.getSource() == LauncherPanel.analyze)
		{
			JFileChooser chooser = new JFileChooser();
			if (chooser.showDialog(LauncherStarter.frame, "Анализ") == JFileChooser.APPROVE_OPTION) try
			{
				LauncherUtils.crackLauncher(chooser.getSelectedFile(), new CheatAnalyze());
			} catch (Exception e1)
			{
				LauncherUtils.errorDialog("Ошибка анализа: " + e1.toString());
			}
		} else if (e.getSource() == LauncherPanel.addserver)
		{
			LauncherPanel.addserver_name.setText("");
			LauncherPanel.addserver_addr.setText("");
			LauncherPanel.addserver_auth.setText("");
			LauncherPanel.addserver_dir.setText("");
			LauncherPanel.addserver_logn.setText("");
			LauncherPanel.addserver_pass.setText("");
			LauncherPanel.instance.applyElements(LauncherPanel.addserverElements);
		} else if (e.getSource() == LauncherPanel.addserver_cancel) LauncherPanel.instance.applyElements(LauncherPanel.loginElements);
		else if (e.getSource() == LauncherPanel.addserver_accept)
		{
			String servername = LauncherPanel.addserver_name.getText();
			String serveraddr = LauncherPanel.addserver_addr.getText();
			String serverauth = LauncherPanel.addserver_auth.getText();
			String serverdir = LauncherPanel.addserver_dir.getText();
			String serverlogn = LauncherPanel.addserver_logn.getText();
			String serverpass = new String(LauncherPanel.addserver_pass.getPassword());
			if (servername.length() < 3) LauncherUtils.errorDialog("Слишком короткое имя сервера.");
			else if (serveraddr.split(":").length != 2) LauncherUtils.errorDialog("В адресе нужно указать порт. (например localhost:25565)");
			else if (serverdir.length() < 3) LauncherUtils.errorDialog("Слишком короткое имя папки.");
			else if (serverlogn.length() < 3) LauncherUtils.errorDialog("Слишком короткий логин.");
			else if (serverpass.length() < 3) LauncherUtils.errorDialog("Слишком короткий пароль.");
			else if (!serverauth.startsWith("sashok,") && !serverauth.startsWith("notch,") && !serverauth.startsWith("hummer,")) LauncherUtils.errorDialog("Неизвестный тип авторизации");
			else
			{
				LauncherPanel.serverPanel.addServer(new ServerEntry(servername, serveraddr, serverdir, serverauth, serverlogn, serverpass));
				LauncherPanel.instance.applyElements(LauncherPanel.loginElements);
			}
		}
	}
}