package epics.binarytrack.questions;

import java.util.Random;

public class Subtraction extends Question
{
	public Subtraction()
	{
		type = TEXT_INPUT;
		int num1 = new Random().nextInt(100);
		int num2 = new Random().nextInt(100);
		
		setQuestion("Subtract: " + num1 + " - "+ Integer.toString(num2) + ".");
		int answer = num1 - num2;
		setAnswer(answer+"");		
	}
	
	
}
