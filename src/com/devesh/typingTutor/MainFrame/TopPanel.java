package com.devesh.typingTutor.MainFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TopPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel title;
	private AccountButton button;
	
	public TopPanel(){
		init();
	}
	
	private void init() {
		// ----------------------------------------------------------------------------------------------------
		title = new JLabel("Speed Typer");
		button = new AccountButton("res\\DefaultAccount.jpg", new Dimension(30, 30));
		// ----------------------------------------------------------------------------------------------------
		
		// ----------------------------------------------------------------------------------------------------
		//Setting title label
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		// ----------------------------------------------------------------------------------------------------
		
		// ----------------------------------------------------------------------------------------------------
		// Initialize layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		// ----------------------------------------------------------------------------------------------------
		
		// ----------------------------------------------------------------------------------------------------
		//Setting constraints
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 5;
		constraints.insets = new Insets(15, 60, 30, 0);
		layout.setConstraints(title, constraints);
		
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(15, 0, 30, 15);
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		layout.setConstraints(button, constraints);
		// ----------------------------------------------------------------------------------------------------
		
		// ----------------------------------------------------------------------------------------------------
		//Adding components
		add(title);
		add(button);
		// ----------------------------------------------------------------------------------------------------
	}
}
