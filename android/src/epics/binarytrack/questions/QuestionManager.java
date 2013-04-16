package epics.binarytrack.questions;

import java.util.Random;

public class QuestionManager {

	Random r = new Random();

	public Question getNextQuestion() {
		switch (r.nextInt(6)) {
		case 0:
			return new Binary2Decimal();
		case 1:
			return new Subtraction();
		case 2:
			return new Addition();
		case 3:
		case 4:
		case 5:
			return JsonParsing.getList().get(r.nextInt(JsonParsing.getList().size()));//r.nextInt(JsonParsing.getList().size())
		default:
			return new Binary2Decimal();
		}
	}

}
