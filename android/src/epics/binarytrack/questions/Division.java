package epics.binarytrack.questions;

import java.util.Random;

public class Division extends Question
{
	public Division()
	{
		type = TEXT_INPUT;
		int num1 = new Random().nextInt(20);
		int num2 = num1*(new Random().nextInt(15));
		
		setQuestion("Divide: " + num2 + " / "+ Integer.toString(num1) + ".");
		int answer = num2 / num1;
		setAnswer(answer+"");		
	}
}
