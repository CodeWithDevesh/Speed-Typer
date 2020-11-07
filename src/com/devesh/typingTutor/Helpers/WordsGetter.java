package com.devesh.typingTutor.Helpers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class WordsGetter {

	private FileInputStream fileInputStream;
	private BufferedReader reader;

	private ArrayList<String> words = new ArrayList<>();

	public WordsGetter() {
		try
		{
			fileInputStream = new FileInputStream("res\\dictionary.txt");
			reader = new BufferedReader(new InputStreamReader(fileInputStream));

			String line = reader.readLine();

			while (line != null)
			{
				words.add(line);
				line = reader.readLine();
			}

		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Dictionary not found.", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

	}


	/*
	 * returns a random word from the dictionary
	 */
	public String getRandom()
	{
		Random random = new Random();
		return words.get(random.nextInt(words.size()));
	}


	/*
	 * returns a word with specified number of letters
	 */
	public String getWithLetters(int noOfLetters)
	{

		String string = getRandom();

		while (string.length() != noOfLetters)
		{
			string = getRandom();
		}

		return string;
	}


	/*
	 * returns a word smaller than the specified length
	 */
	public String getSmallerThan(int noOfLetters)
	{
		String string = getRandom();

		while (string.length() >= noOfLetters)
		{
			string = getRandom();
		}

		return string;
	}


	/*
	 * returns a word greater than the specified length
	 */
	public String getGreaterThan(int noOfLetters)
	{
		String string = getRandom();

		while (string.length() >= noOfLetters)
		{
			string = getRandom();
		}

		return string;
	}
}
