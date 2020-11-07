package com.devesh.typingTutor.Accounts;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField tfName;
	private JPasswordField pfPassword;
	private JButton btnOk, btnLogin;

	public SignUp() {
		init();
	}


	private void init()
	{
		JLabel lblTitle = new JLabel("New Account");
		JLabel lblName = new JLabel("Name");
		JLabel lblPassword = new JLabel("Password");
		JPanel btnPanel = new JPanel();
		btnOk = new JButton("OK");
		btnLogin = new JButton("Login");
		tfName = new JTextField();
		pfPassword = new JPasswordField();

		// ----------------------------------------------------------------------------------------------------
		// Setting label title
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

		// Setting button panel
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));

		// Setting name text field
		tfName.addKeyListener(new FocusChanger());

		// Setting password field
		pfPassword.addKeyListener(new FocusChanger());

		// Setting login button
		btnLogin.addActionListener(new ButtonListener());

		// Setting OK button
		btnOk.addActionListener(new ButtonListener());
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Initialize Layout
		GridBagConstraints constraints = new GridBagConstraints();
		GridBagLayout layout = new GridBagLayout();
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Setting Constraints

		// Constraints for title label
		constraints.ipadx = 80;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1;
		constraints.insets = new Insets(15, 30, 30, 30);
		layout.setConstraints(lblTitle, constraints);

		// Constraints for name label
		constraints.ipadx = 0;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 30, 5, 0);
		layout.setConstraints(lblName, constraints);

		// Constraints for name text field
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 5;
		constraints.insets = new Insets(0, 0, 5, 30);
		layout.setConstraints(tfName, constraints);

		// Constraints for password label
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 30, 15, 0);
		layout.setConstraints(lblPassword, constraints);

		// Constraints for password field
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 5;
		constraints.insets = new Insets(0, 0, 15, 30);
		layout.setConstraints(pfPassword, constraints);

		// Constraints for button panel
		constraints.insets = new Insets(0, 0, 15, 0);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		layout.setConstraints(btnPanel, constraints);
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Adding components to frame
		add(lblTitle);
		add(lblName);
		add(tfName);
		add(lblPassword);
		add(pfPassword);
		add(btnPanel);
		btnPanel.add(btnOk);
		btnPanel.add(btnLogin);
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Setting frame
		setLayout(layout);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		// ----------------------------------------------------------------------------------------------------

		tfName.requestFocus();
	}
	
	private void signUp() {
		AccountManager manager = new AccountManager();
		System.out.println(manager.CreateAccount(tfName.getText(), String.valueOf(pfPassword.getPassword())));
	}
	
	// Changes the focus when enter is pressed
	class FocusChanger implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				if (tfName.hasFocus() == true)
				{
					pfPassword.requestFocus();
				} else if (pfPassword.hasFocus())
				{
					signUp();
				}
			}
		}


		// Unused method
		@Override
		public void keyTyped(KeyEvent e)
		{
		}


		// Unused method
		@Override
		public void keyReleased(KeyEvent e)
		{
		}

	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource().equals(btnLogin))
			{
				dispose();
				new Login();
			} else if (e.getSource().equals(btnOk))
			{
				signUp();
			}
		}

	}
}
