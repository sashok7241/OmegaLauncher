package net.sashok724.omega;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ServerLists extends JPanel
{
	public static final long serialVersionUID = 1L;
	public JScrollPane scroller = new JScrollPane(this);
	public ArrayList<ServerEntry> serverList = new ArrayList<ServerEntry>();

	public ServerLists()
	{
		setLayout(null);
	}

	public void addServer(ServerEntry entry)
	{
		serverList.add(entry);
		scroller.validate();
		scroller.repaint();
	}

	public void removeServer(ServerEntry entry)
	{
		serverList.remove(entry);
		scroller.validate();
		scroller.repaint();
	}
}