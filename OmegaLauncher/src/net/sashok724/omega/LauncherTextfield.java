package net.sashok724.omega;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LauncherTextfield extends JTextField implements LauncherConstants, DocumentListener
{
	public static final long serialVersionUID = 1L;
	public String name;
	public boolean focused = true;

	public LauncherTextfield(String param, int x, int y)
	{
		super(LauncherConfig.getString(param, ""));
		setOpaque(false);
		setBounds(x, y, IMG_TEXTFIELD.getWidth(), IMG_TEXTFIELD.getHeight());
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setFont(new Font("Courier New", 0, 20));
		getDocument().addDocumentListener(this);
		name = param;
	}

	@Override
	protected void paintComponent(Graphics maing)
	{
		Graphics2D g = (Graphics2D) maing.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(isFocusOwner()) g.drawImage(IMG_TEXTFIELD_SEL, 0, 0, getWidth(), getHeight(), null);
		else g.drawImage(IMG_TEXTFIELD, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		super.paintComponent(maing);
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, getText());
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, getText());
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, getText());
	}
}