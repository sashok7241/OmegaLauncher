package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class LauncherPassfield extends JPasswordField implements LauncherConstants, DocumentListener, FocusListener
{
	public static final long serialVersionUID = 1L;
	public String name;
	public boolean focused = false;
	
	public LauncherPassfield(String param, int x, int y)
	{
		super();
		setOpaque(false);
		setBounds(x, y, IMG_TEXTFIELD_DEF.getWidth(), IMG_TEXTFIELD_DEF.getHeight());
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setCaretColor(Color.WHITE);
		setEchoChar('*');
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setFont(FONT_MC.deriveFont(16F));
		setDocument(new LauncherDocument());
		getDocument().addDocumentListener(this);
		setText(LauncherConfig.getString(param, ""));
		addFocusListener(this);
		validate();
		name = param;
	}
	
	@Override public void changedUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, new String(getPassword()));
	}
	
	@Override public void focusGained(FocusEvent e)
	{
		focused = true;
		repaint();
	}
	
	@Override public void focusLost(FocusEvent e)
	{
		focused = false;
		repaint();
	}
	
	@Override public void insertUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, new String(getPassword()));
	}
	
	@Override public void paintComponent(Graphics maing)
	{
		Graphics2D g = LauncherUtils.getG2D(maing);
		if(focused)
		{
			g.drawImage(IMG_TEXTFIELD_SEL, 0, 0, getWidth(), getHeight(), null);
		} else
		{
			g.drawImage(IMG_TEXTFIELD_DEF, 0, 0, getWidth(), getHeight(), null);
		}
		g.dispose();
		super.paintComponent(maing);
	}
	
	@Override public void removeUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, new String(getPassword()));
	}
	
	public LauncherPassfield setW(int i)
	{
		setSize(i, getHeight());
		return this;
	}
}