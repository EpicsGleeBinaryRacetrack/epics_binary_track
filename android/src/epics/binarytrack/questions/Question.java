package epics.binarytrack.questions;

public class Question {
	public final static String QUESTION = "question"; // used for passing
														// question to fragment
	public final static String QUESTION_TYPE = "question_type";
	public final static int TEXT_INPUT = 1;
	public final static int MULTI_CHOICE_2 = 2;
	public final static int MULTI_CHOICE_4 = 3;
	private String mQuestion = "";
	private String mAnswer = "";
	protected int type;

	public int getType() {
		return type;
	}

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

	public boolean processResponse(String response) {
		return response.equals(mAnswer);
	}
}
