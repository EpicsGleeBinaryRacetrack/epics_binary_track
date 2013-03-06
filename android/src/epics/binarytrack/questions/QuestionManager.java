package epics.binarytrack.questions;

public class QuestionManager {
	
	public Question getNextQuestion(){
		return new Binary2Decimal();
	}
	
}
