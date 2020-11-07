package com.devesh.typingTutor.MainFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AccountButton extends JButton implements ActionListener{
	
	
	private static final long serialVersionUID = 1L;

	public AccountButton(String image, Dimension dimension){
		ImageIcon icon = new ImageIcon(image);
		Image img = icon.getImage().getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		this.setIcon(icon);
		
		Dimension size = dimension;
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("clicked");
	}
	
	protected void paintComponent(Graphics g) {
		if(getModel().isArmed()) {
			g.setColor(Color.lightGray);
		}else {
			g.setColor(getBackground());
		}
		g.fillOval(0,  0,  getSize().width-1,  getSize().height-1);
		super.paintComponent(g);
	}

}
