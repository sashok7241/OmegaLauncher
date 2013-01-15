package net.sashok724.omega;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LauncherTextfield extends JTextField implements LauncherConstants, DocumentListener, FocusListener
{
	public static final long serialVersionUID = 1L;
	public String name;
	public boolean focused = false;

	public LauncherTextfield(String param, int x, int y)
	{
		super(LauncherConfig.getString(param, ""));
		setOpaque(false);
		setBounds(x, y, IMG_TEXTFIELD_DEF.getWidth(), IMG_TEXTFIELD_DEF.getHeight());
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setCaretColor(Color.WHITE);
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setFont(FONT_MC.deriveFont(16F));
		getDocument().addDocumentListener(this);
		addFocusListener(this);
		name = param;
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, getText());
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		focused = true;
		repaint();
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		focused = false;
		repaint();
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, getText());
	}

	@Override
	public void paintComponent(Graphics maing)
	{
		Graphics2D g = (Graphics2D) maing.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (focused) g.drawImage(IMG_TEXTFIELD_SEL, 0, 0, getWidth(), getHeight(), null);
		else g.drawImage(IMG_TEXTFIELD_DEF, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		super.paintComponent(maing);
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		LauncherConfig.set(name, getText());
	}

	public LauncherTextfield setW(int i)
	{
		setSize(i, getHeight());
		return this;
	}
}