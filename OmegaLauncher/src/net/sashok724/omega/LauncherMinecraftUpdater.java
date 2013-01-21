package net.sashok724.omega;

import java.io.File;
import java.io.IOException;

public final class LauncherMinecraftUpdater extends Thread
{
	public LauncherMinecraftUpdater()
	{
		start();
	}

	@Override
	public void run()
	{
		File bin = new File(LauncherUtils.minecraftDir, "bin");
		for (String file : LauncherPanel.requiredFiles)
		{
			LauncherPanel.currentStat = "Загрузка файлов";
			LauncherPanel.currentFile = file;
			LauncherPanel.currentSize = LauncherUtils.getFileSize(file);
			if (LauncherPanel.currentSize == -1)
			{
				LauncherPanel.currentStat = "Ошибка получения файла";
				LauncherPanel.instance.repaint();
				return;
			}
			try
			{
				bin.mkdirs();
				LauncherUtils.loadFile(file, new File(bin, file));
			} catch (IOException e)
			{
				e.printStackTrace();
				LauncherPanel.currentStat = "Ошибка получения файла";
				LauncherPanel.instance.repaint();
				return;
			}
			LauncherPanel.currentSize = 0;
		}
		LauncherPanel.addModManagerComponents(LauncherPanel.username, LauncherPanel.session);
	}
}