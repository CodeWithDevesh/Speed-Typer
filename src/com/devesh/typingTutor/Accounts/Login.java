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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.devesh.typingTutor.MainFrame.MainFrame;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblName;
	private JLabel lblLogin;
	private JTextField tfName;
	private JPasswordField pfPassword;
	private JButton btnLogin;
	private JButton btnNew;
	private JLabel lblPassword;
	
	
	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		new Login();
	}


	public Login() {
		init();
	}


	public void init()
	{
		// ----------------------------------------------------------------------------------------------------
		lblName = new JLabel("Name");
		lblLogin = new JLabel("Login");
		tfName = new JTextField(10);
		pfPassword = new JPasswordField(10);
		lblPassword = new JLabel("Password");
		btnLogin = new JButton("Login");
		btnNew = new JButton("New Account");
		JPanel btnPanel = new JPanel();
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Setting label login
		lblLogin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);

		// Setting label Name
		lblName.setHorizontalAlignment(SwingConstants.LEADING);

		// Setting label Password
		lblPassword.setHorizontalAlignment(SwingConstants.LEADING);

		// Setting name text field
		tfName.addKeyListener(new FocusChanger());

		// Setting password field
		pfPassword.addKeyListener(new FocusChanger());

		// Setting new button
		btnNew.addActionListener(new ButtonListener());

		// Setting Login button
		btnLogin.addActionListener(new ButtonListener());

		// Setting button panel
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Initialize gridbag layout
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(layout);
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Setting layout constraints
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weighty = 0;
		constraints.weightx = 1;

		// Constraints for lblLogin
		constraints.insets = new Insets(15, 0, 30, 0);
		layout.setConstraints(lblLogin, constraints);

		// Constraints for lblName
		constraints.insets = new Insets(0, 30, 5, 0);
		// constraints.ipady = 0;
		constraints.ipadx = 40;
		constraints.weightx = 1;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		layout.setConstraints(lblName, constraints);

		// Constraints for tfName
		constraints.insets = new Insets(0, 0, 5, 30);
		constraints.weightx = 3;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(tfName, constraints);

		// Constraints for lblPassword
		constraints.insets = new Insets(0, 30, 15, 0);
		constraints.weightx = 1;
		constraints.gridwidth = GridBagConstraints.RELATIVE;
		layout.setConstraints(lblPassword, constraints);

		// Constraints for pfPassword
		constraints.insets = new Insets(0, 0, 15, 30);
		constraints.weightx = 3;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(pfPassword, constraints);

		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 0, 15, 0);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		layout.setConstraints(btnPanel, constraints);
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// adding components to the frame
		add(lblLogin);
		add(lblName);
		add(tfName);
		add(lblPassword);
		add(pfPassword);
		add(btnPanel);
		btnPanel.add(btnLogin);
		btnPanel.add(btnNew);
		// ----------------------------------------------------------------------------------------------------

		// ----------------------------------------------------------------------------------------------------
		// Setting Frame
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		// ----------------------------------------------------------------------------------------------------

		tfName.requestFocus();
	}
	
	private void login() {
		AccountManager manager = new AccountManager();
		boolean isLogged = manager.CheckPassword(tfName.getText(), String.valueOf(pfPassword.getPassword()));
		
		if(!isLogged) {
			JOptionPane.showMessageDialog(this, "Wrong User Name or Password", "Error Logging In", JOptionPane.ERROR_MESSAGE);
		}else {
			new MainFrame();
			dispose();
		}
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
					login();
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
			if (e.getSource().equals(btnNew))
			{
				dispose();
				new SignUp();
			} else if (e.getSource().equals(btnLogin))
			{
				login();
			}
		}

	}
	
}
