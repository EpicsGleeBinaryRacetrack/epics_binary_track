package epics.binarytrack.questions;

import java.util.HashSet;
import java.util.Set;

public class MultiChoiceQuestion extends Question {
	private Set<String> mAnswers;
	
	public MultiChoiceQuestion(int num_of_answers){
		mAnswers = new HashSet<String>(num_of_answers);
	}

	public boolean addAnswers(String answer) {
		if(mAnswers.size()>4){
			return false;
		}
		return mAnswers.add(answer);
	}
	
	public boolean processResponse(String response){
		if(mAnswers.size()!= 2 || mAnswers.size()!= 4){
			throw new RuntimeException("There are to many answers");
		}
		return super.processResponse(response);
	}

}
