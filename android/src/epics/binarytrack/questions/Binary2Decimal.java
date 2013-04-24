package epics.binarytrack.questions;

import java.util.Random;

public class Binary2Decimal extends Question {
			
	public Binary2Decimal(){
		type = TEXT_INPUT;
		String binaryStr = Integer.toBinaryString(new Random().nextInt(20));
		setQuestion("What is the decimal number for "+binaryStr+" ?");
		setAnswer(Integer.parseInt(binaryStr,2)+"");		
	}

}
