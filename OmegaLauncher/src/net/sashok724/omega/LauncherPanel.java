package net.sashok724.omega;

import java.util.ArrayList;

import javax.swing.JPanel;

public class LauncherPanel extends JPanel
{
	public static final long serialVersionUID = 1L;
	public static LauncherPanel instance;
	public static int currentByte = 0, currentSize = 0;
	public static String currentStat = "<Unknown>", currentFile = "<Unknown>", username, session;
	public static ArrayList<String> requiredFiles;
	
	public LauncherPanel()
	{
		
	}
}