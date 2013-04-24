package epics.binarytrack.questions;

import java.util.Random;

public class Multiplication extends Question
{

	public Multiplication()
	{
		type = TEXT_INPUT;
		int num1 = new Random().nextInt(15);
		int num2 = new Random().nextInt(15);
		
		setQuestion("Multiplication: " + num1 + " * "+ num2 + ".");
		int answer = num1 * num2;
		setAnswer(answer+"");		
	}
}
