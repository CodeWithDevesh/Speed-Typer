package com.devesh.typingTutor.MainFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.devesh.typingTutor.Counter.Counter;
import com.devesh.typingTutor.Counter.CounterListener;

public class MainFrame extends JFrame implements CounterListener{

	private static final long serialVersionUID = 1L;
	private Counter counter = new Counter();
	private StatusPane statusPane = new StatusPane(counter, new Reset());

	private TypeArea typeArea = new TypeArea();

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		new MainFrame();
	}


	public MainFrame() {
		init();
	}


	private void init()
	{
		add(new TopPanel(), BorderLayout.NORTH);
		add(typeArea, BorderLayout.CENTER);
		add(statusPane, BorderLayout.SOUTH);
		
		pack();
		setMinimumSize(getSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		typeArea.requestFocus();
		typeArea.addTypeStartListener(counter);
		counter.addCounterListener(this);
//		counter.setTime(20);
	}


	@Override
	public void timeUp()
	{
		System.out.println("Errors = " + Result.errors);
		System.out.println("Letters Typed = " + Result.lettersTyped);
		System.out.println("WPM = " + Result.getWPM());
		typeArea.disable();
		statusPane.update();
	}

	
	class Reset implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			typeArea.reset();
			counter.reset();
			Result.errors=0;
			Result.lettersTyped=0;
		}
		
	}
}
