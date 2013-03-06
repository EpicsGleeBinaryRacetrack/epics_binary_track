package epics.binarytrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import epics.binarytrack.fragments.MultiChoiceQuestionFragment;
import epics.binarytrack.fragments.QuestionFragment;
import epics.binarytrack.fragments.TextQuestionFragment;
import epics.binarytrack.questions.Question;
import epics.binarytrack.questions.QuestionManager;

public class QuestionActivity extends FragmentActivity implements OnQustionListener {

	MultiChoiceQuestionFragment firstFragment;
	TextQuestionFragment textFragment;
	QuestionFragment current = null;
	Question mQuestion = null;
	QuestionManager mQmanager;
	
    public void onQuestionAnswered() {
    	nextQuestion();
    }

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);

		if (findViewById(R.id.fragment_container) != null) {

			if (savedInstanceState != null) {
				return;
			}
			nextQuestion();
		}
	}
	
	public void nextQuestion(){
		if(mQmanager==null){
			mQmanager=new QuestionManager();
		}
		mQuestion = mQmanager.getNextQuestion();
				
		switch(mQuestion.getType()){
			case Question.TEXT_INPUT:
				current = new TextQuestionFragment();
				break;
			case Question.MULTI_CHOICE_4:
			case Question.MULTI_CHOICE_2:
				current = new MultiChoiceQuestionFragment();
				break;
		}
		current.setArguments(getIntent().getExtras());
		current.setQuestion(mQuestion);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, current).commit();
		
	}

}
