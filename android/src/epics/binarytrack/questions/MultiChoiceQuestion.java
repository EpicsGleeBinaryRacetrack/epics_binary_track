package epics.binarytrack.questions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MultiChoiceQuestion extends Question {
	private ArrayList<String> mAnswers;

	public MultiChoiceQuestion(int num_of_answers) {
		mAnswers = new ArrayList<String>(num_of_answers);
		if (num_of_answers == 4) {
			type = Question.MULTI_CHOICE_4;
		} else {
			type = Question.MULTI_CHOICE_2;
		}
	}

	public boolean addAnswers(String answer) {
		if (mAnswers.size() > 4) {
			return false;
		}
		return mAnswers.add(answer);
	}

	public boolean processResponse(String response) {
		if (mAnswers.size() != 2 && mAnswers.size() != 4) {
			throw new RuntimeException("There are to many answers: "+mAnswers.size());
		}
		return super.processResponse(response);
	}

	public ArrayList<String> getAnswers() {
		return mAnswers;
	}

}
