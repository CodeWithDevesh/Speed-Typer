package com.devesh.typingTutor.Accounts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AccountManager implements Serializable {

	private static final long serialVersionUID = 1L;

	// List of accounts
	private ArrayList<Account> accounts;

	// file in which accounts are saved
	private File file = new File(
			System.getProperty("user.home") + "\\Typing Tutor\\accounts.dat");

	// Constructor for account manager
	@SuppressWarnings("unchecked")
	public AccountManager() {
		if (file.exists())
		{
			try
			{
				ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
				accounts = (ArrayList<Account>) oi.readObject();
				oi.close();
			} catch (Exception e)
			{
				accounts = new ArrayList<Account>();
				e.printStackTrace();
			}
		} else
		{
			accounts = new ArrayList<Account>();
		}
	}


	/*
	 * creates a account with name and password and adds it to the account list.
	 * Returns false if failed.
	 */
	public boolean CreateAccount(String userName, String password)
	{
		Account account = new Account();
		account.name = userName;
		account.password = password;

		if (userName.length() < 3 || password.length() < 3)
			return false;

		if (findAccountIndex(userName) == -1)
		{
			accounts.add(account);
			if (file.exists())
				file.delete();
			try
			{
				ObjectOutputStream os = new ObjectOutputStream(
						new FileOutputStream(file));
				os.writeObject(accounts);
				os.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			return true;
		} else
		{
			return false;
		}
	}


	/*
	 * Checks the password for the given user name. Returns true if the password
	 * is correct. Returns false if the account is not found or the password is
	 * incorrect.
	 */
	public boolean CheckPassword(String userName, String password)
	{

		int accountIndex = findAccountIndex(userName);

		if (accountIndex == -1)
		{
			return false;
		} else
		{
			if (accounts.get(accountIndex).password.matches(password))
			{
				return true;
			} else
			{
				return false;
			}
		}
	}


	/*
	 * returns index of account in list accounts. Searches by the name of
	 * account. Returns -1 if not found.
	 */
	private int findAccountIndex(String name)
	{
		int n = -1;

		for(int i = 0 ; i < accounts.size() ; i++)
		{
			if (accounts.get(i).name.matches(name))
			{
				n = i;
				break;
			}
		}

		return n;
	}

	// class for storing account information
	class Account implements Serializable {

		private static final long serialVersionUID = 1L;
		public String name;
		public String password;

		Account() {

		}
	}
}
