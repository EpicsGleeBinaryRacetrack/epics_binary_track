package epics.binarytrack.questions;

import java.util.Random;

public class Subtraction extends Question
{
	public Subtraction()
	{
		type = TEXT_INPUT;
		int num1 = new Random().nextInt(100);
		int num2 = new Random().nextInt(100);
		
		int temp = num1;
		int temp1 = num2;
		
		num1=Math.max(temp, temp1);
		num2=Math.min(temp, temp1);
		
		setQuestion("Subtract: " + num1 + " - "+ Integer.toString(num2) + ".");
		int answer = num1 - num2;
		setAnswer(answer+"");		
	}
	
	
}
