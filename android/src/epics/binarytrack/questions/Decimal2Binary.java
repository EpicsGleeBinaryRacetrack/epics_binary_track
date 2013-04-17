package epics.binarytrack.questions;

import java.util.Random;

public class Decimal2Binary extends Question {
			
	public Decimal2Binary(){
		type = TEXT_INPUT;
		int num = new Random().nextInt(20);
		String binaryStr = num+"";
		setQuestion("What is the binary number for "+binaryStr+" ?");
		setAnswer(Integer.toBinaryString(num)+"");		
	}

}
