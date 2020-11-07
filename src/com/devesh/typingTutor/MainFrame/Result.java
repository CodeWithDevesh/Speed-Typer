package com.devesh.typingTutor.MainFrame;

public class Result {
	public static int errors=0, lettersTyped=0;
	

	public static int getWPM()
	{
		int returnVal = (int) Math.round((lettersTyped/5) - (errors*0.5));
		if(returnVal < 0)
			return 0;
		else
			return returnVal;
	}

}
