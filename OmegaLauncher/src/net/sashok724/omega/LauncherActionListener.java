package net.sashok724.omega;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public final class LauncherActionListener implements ActionListener, LauncherConstants
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == LauncherPanel.analyzeClose)
		{
			LauncherPanel.instance.applyElements(LauncherPanel.loginElements);
		} else if(e.getSource() == LauncherPanel.analyze)
		{
			JFileChooser chooser = new JFileChooser();
			if(chooser.showDialog(LauncherStarter.frame, "Анализ") == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					LauncherUtils.crackLauncher(chooser.getSelectedFile(), new CheatAnalyze());
				} catch(Exception e1)
				{
					LauncherUtils.errorDialog("Ошибка анализа: " + e1.toString());
				}
			}
		}
	}
}