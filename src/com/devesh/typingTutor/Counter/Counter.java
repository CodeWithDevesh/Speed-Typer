package com.devesh.typingTutor.Counter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.devesh.typingTutor.MainFrame.TypeArea;

public class Counter implements ActionListener, TypeArea.TypeStartListener {

	private Timer secTimer;
	private JLabel label;

	int remTime = 60;

	private ArrayList<CounterListener> listeners = new ArrayList<>();

	public Counter() {
		secTimer = new Timer(1000, this);
		
		label = new JLabel("60");
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}


	public void start()
	{
		secTimer.start();
	}


	public void stop()
	{
		secTimer.stop();
	}


	public JLabel getLabel()
	{
		return label;
	}

	public void setTime(int sec){
		if(sec < 0) 
			throw(new IllegalArgumentException());
		remTime = sec;
	}
	
	public void reset() {
		secTimer.stop();
		remTime = 60;
		label.setText("60");
	}
	
	public void addCounterListener(CounterListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		remTime--;

		String remString = String.format("%02d", remTime);
		label.setText(remString);

		if (remTime <= 0)
		{
			stop();
			for(CounterListener listener : listeners) {
				listener.timeUp();
			}
		}
	}


	@Override
	public void startedTyping()
	{
		start();
	}
}
