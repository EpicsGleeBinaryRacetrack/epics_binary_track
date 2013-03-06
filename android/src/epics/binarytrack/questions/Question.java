package epics.binarytrack.questions;

public class Question {
	public static String QUESTION_TYPE = "question_type";
	private String mQuestion = "";
	private String mAnswer = "";

	public String getAnswer() {
		return mAnswer;
	}

	public void setAnswer(String mAnswer) {
		this.mAnswer = mAnswer;
	}

	public String getQuestion() {
		return mQuestion;
	}

	public void setQuestion(String mQuestion) {
		this.mQuestion = mQuestion;
	}
	
	public boolean processResponse(String response){
		return response.equals(mAnswer);
	}
}
