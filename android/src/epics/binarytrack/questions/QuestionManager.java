package epics.binarytrack.questions;

import java.util.Random;

public class QuestionManager {

	Random r = new Random();

	public Question getNextQuestion() {
		while (true) {
			switch (r.nextInt(7)) {
			case 0:
				if (JsonParsing.bin_to_dec)
					return new Binary2Decimal();
			case 1:
				if (JsonParsing.subtraction)
					return new Subtraction();
			case 2:
				if (JsonParsing.addition)
					return new Addition();
			case 3:
				if(JsonParsing.multiplication)
					return new Multiplication();
			case 4:
				if(JsonParsing.dec_to_bin)
					return new Decimal2Binary();
			case 5:
				if(JsonParsing.division)
					return new Division();
			case 6:
				Question q = JsonParsing.getList().get(
						r.nextInt(JsonParsing.getList().size()));// r.nextInt(JsonParsing.getList().size())
				if(q!=null){
					return q;
				}
			}
		}
	}

}
