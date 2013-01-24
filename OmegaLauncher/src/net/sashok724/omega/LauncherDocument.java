package net.sashok724.omega;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public final class LauncherDocument extends PlainDocument
{
	public static final long serialVersionUID = 1L;
	public int maxlen = 16;
	
	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException
	{
		if (str == null) return;
		if (getLength() + str.length() <= maxlen) super.insertString(offset, str, attr);
	}
}