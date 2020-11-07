package com.devesh.typingTutor.MainFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

import com.devesh.typingTutor.Helpers.WordsGetter;

public class TypeArea extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;

	// Area showing hints
	private JTextArea hint;

	// Area overlapping hints where the user writes
	// User can't see this
	private JTextArea writingArea;

	private ArrayList<TypeStartListener> typeStartListeners = new ArrayList<>();

	private boolean started;

	public TypeArea() {
		init();
	}


	// --------------------------------------------------------------------------------------------------------
	// Initializes the component
	private void init()
	{
		hint = new JTextArea(2, 50);
		hint.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
		hint.setFocusable(false);
		hint.setEditable(false);
		hint.setLineWrap(true);
		hint.setText(getOneLineText() + getOneLineText());

		writingArea = new JTextArea(hint.getRows(), hint.getColumns());
		writingArea.setFont(hint.getFont());
		writingArea.setBackground(new Color(0, 0, 0, 0));
		writingArea.setForeground(new Color(0, 0, 0, 0));
		writingArea.setOpaque(false);
		writingArea.setLineWrap(true);
		writingArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "none");
		writingArea.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "none");
		writingArea.getInputMap().put(KeyStroke.getKeyStroke("TAB"), "none");
		writingArea.getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "none");
		writingArea.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "none");
		writingArea.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "none");
		writingArea.getCaret().addChangeListener(this);
		writingArea.getCaret().addChangeListener(new Scroller());

		setLayout(null);
		add(writingArea);
		add(hint);

	}
	// --------------------------------------------------------------------------------------------------------


	// --------------------------------------------------------------------------------------------------------
	@Override
	public void setBounds(int x, int y, int width, int height)
	{
		super.setBounds(x, y, width, height);
		hint.setBounds(0, 0, this.getWidth(), this.getHeight());
		writingArea.setBounds(0, 0, this.getWidth(), this.getHeight());
	}
	// --------------------------------------------------------------------------------------------------------


	// --------------------------------------------------------------------------------------------------------
	@Override
	public void requestFocus()
	{
		writingArea.requestFocus();
	}
	// --------------------------------------------------------------------------------------------------------


	// --------------------------------------------------------------------------------------------------------
	// returns preferred size of type area
	@Override
	public Dimension getPreferredSize()
	{
		return writingArea.getPreferredSize();
	}
	// --------------------------------------------------------------------------------------------------------


	// --------------------------------------------------------------------------------------------------------
	// Highlights the wrong and correct words in red and green
	@Override
	public void stateChanged(ChangeEvent e)
	{
		String hints = hint.getText();
		String typed = writingArea.getText();

		// --------------------------------------------------------------------------------------------------------
		// Fires type start listeners
		if (!started && writingArea.getCaret().getDot() != 0)
		{
			for(TypeStartListener listener : typeStartListeners)
			{
				listener.startedTyping();
			}
			started = true;
		}
		// --------------------------------------------------------------------------------------------------------

		// --------------------------------------------------------------------------------------------------------
		// Calculates number of errors
		if (typed.length() != 0)
		{
			if (typed.charAt(typed.length() - 1) != hints.charAt(typed.length() - 1))
			{
				Result.errors++;
			}
		}
		// --------------------------------------------------------------------------------------------------------

		// --------------------------------------------------------------------------------------------------------
		// Calculates number of letters typed
		if (typed.length() != 0)
		{
			Result.lettersTyped++;
		}
		// --------------------------------------------------------------------------------------------------------

		boolean[] correctLetter = new boolean[hints.length() + 50];

		DefaultHighlightPainter greenPainter = new DefaultHighlightPainter(
				new Color(14, 240, 41, 100));
		DefaultHighlightPainter redPainter = new DefaultHighlightPainter(
				new Color(255, 0, 17, 100));

		writingArea.getHighlighter().removeAllHighlights();

		for(int i = 0 ; i < typed.length() ; i++)
		{
			if (typed.charAt(i) != hints.charAt(i))
			{
				correctLetter[i] = false;
			} else
				correctLetter[i] = true;
		}

		for(int i = 0 ; i < typed.length() ; i++)
		{

			if (correctLetter[i])
			{
				try
				{
					writingArea.getHighlighter().addHighlight(i, i + 1, greenPainter);
				} catch (BadLocationException e1)
				{
					e1.printStackTrace();
				}
			} else
			{
				try
				{
					writingArea.getHighlighter().addHighlight(i, i + 1, redPainter);
				} catch (BadLocationException e1)
				{
					e1.printStackTrace();
				}
			}
		}

	}
	// --------------------------------------------------------------------------------------------------------


	// --------------------------------------------------------------------------------------------------------
	// returns one line of text
	private String getOneLineText()
	{
		WordsGetter wordsGetter = new WordsGetter();
		String string = "";
		while (string.length() < (hint.getColumns() - 1))
		{

			int remaining = hint.getColumns()
					- (string.length() % (hint.getColumns() - 1));
			remaining--;
			String wordToAdd = wordsGetter.getSmallerThan(7) + " ";

			while (wordToAdd.length() > remaining)
			{
				if (remaining == 1)
				{
					wordToAdd = " ";
				} else if (remaining < 5)
				{
					wordToAdd = wordsGetter.getWithLetters(remaining - 1) + " ";
				} else
					wordToAdd = wordsGetter.getSmallerThan(remaining) + " ";
			}
			string += wordToAdd;
		}
		return string;
	}
	// --------------------------------------------------------------------------------------------------------


	public void disable()
	{
		writingArea.setEditable(false);
	}
	
	public void reset() {
		started = false;
		writingArea.setEditable(true);
		writingArea.getHighlighter().removeAllHighlights();
		hint.setText(getOneLineText() + getOneLineText());
		writingArea.setText("");
		writingArea.getCaret().setDot(0);
		requestFocus();
	}

	// --------------------------------------------------------------------------------------------------------
	// Scrolls the viewport
	class Scroller implements ActionListener, ChangeListener {

		// --------------------------------------------------------------------------------------------------------
		public void scroll()
		{
			writingArea.setText("");
			writingArea.setCaretPosition(0);

			String hints = hint.getText();
			hints = hints.substring(hint.getColumns() - 1) + getOneLineText();
			hint.setText(hints);
		}
		// --------------------------------------------------------------------------------------------------------


		@Override
		public void actionPerformed(ActionEvent e)
		{
			scroll();
		}


		@Override
		public void stateChanged(ChangeEvent e)
		{
			if (writingArea.getCaret().getDot() == hint.getColumns() - 1)
			{
				Timer timer = new Timer(1, this);
				timer.setRepeats(false);
				timer.start();
			}
		}

	}
	// --------------------------------------------------------------------------------------------------------

	// Adds a TypeStartListener
	public void addTypeStartListener(TypeStartListener listener)
	{
		typeStartListeners.add(listener);
	}

	public interface TypeStartListener {
		public void startedTyping();
	}
}