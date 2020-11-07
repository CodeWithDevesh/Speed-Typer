package com.devesh.typingTutor.MainFrame;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.devesh.typingTutor.Counter.Counter;

public class StatusPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	
	private JLabel lblTypos = new JLabel("Typos - ");
	private JLabel lblSpeed = new JLabel("Speed - ");
	private Counter counter;
	private JButton btnReset = new JButton();
	private ActionListener resetbtnListener;
	
	public StatusPane(Counter counter, ActionListener resetbtn) {
		this.counter = counter;
		resetbtnListener = resetbtn;
		init();
	}
	
	
	private void init() {
		
		lblTypos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypos.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		lblSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeed.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		
		ImageIcon icon = new ImageIcon("res\\ResetButton.png");
		Image img = icon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		btnReset.setIcon(icon);
		btnReset.setContentAreaFilled(false);
		btnReset.addActionListener(resetbtnListener);
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(15, 0, 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		
		layout.setConstraints(counter.getLabel(), constraints);
		
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		layout.setConstraints(lblSpeed, constraints);
		
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(lblTypos, constraints);
		
//		constraints.gridwidth = GridBagConstraints.RELATIVE;
		layout.setConstraints(btnReset, constraints);
		
		setLayout(layout);
		add(counter.getLabel());
		add(lblSpeed);
		add(lblTypos);
		add(btnReset);
	}
	
	public void update() {
		lblTypos.setText("Typos - " + Integer.toString(Result.errors));
		lblSpeed.setText("Speed - " + Integer.toString(Result.getWPM()) + " WPM");
	}
}
