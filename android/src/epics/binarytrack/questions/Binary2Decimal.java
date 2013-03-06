package epics.binarytrack.questions;

import java.util.Random;

public class Binary2Decimal extends Question {
		
	public Binary2Decimal(){
		String binaryStr = Integer.toBinaryString(new Random().nextInt( 32 ));
		setQuestion("What is the decimal number for "+binaryStr+" ?");
		setAnswer(Integer.parseInt(binaryStr,2)+"");		
	}

}
