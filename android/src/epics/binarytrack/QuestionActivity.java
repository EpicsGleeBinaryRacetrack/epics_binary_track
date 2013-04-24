package epics.binarytrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import epics.binarytrack.fragments.MultiChoiceQuestionFragment;
import epics.binarytrack.fragments.QuestionFragment;
import epics.binarytrack.fragments.TextQuestionFragment;
import epics.binarytrack.questions.Question;
import epics.binarytrack.questions.QuestionManager;

public class QuestionActivity extends FragmentActivity implements
		OnQustionListener {

	MultiChoiceQuestionFragment firstFragment;
	TextQuestionFragment textFragment;
	QuestionFragment current = null;
	Question mQuestion = null;
	QuestionManager mQmanager;

	public void onQuestionAnswered(boolean isCorrect) {
		if(isCorrect && ServerApplication.out!=null){
			ServerApplication.out.write("player\n");
			ServerApplication.out.flush();
			Log.d("epics","sent output"); 
		}
		//This tells the users if the question answered is correct or not
//		if(isCorrect){
//			Toast.makeText(this, "You are right!", Toast.LENGTH_SHORT).show();
//		}else{
//			Toast.makeText(this, "You are wrong!", Toast.LENGTH_SHORT).show();
//		}
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

	public void nextQuestion() {
		if (mQmanager == null) {
			mQmanager = new QuestionManager();
		}
		mQuestion = mQmanager.getNextQuestion();
		Log.d("get type", mQuestion.getType()+"");
		switch (mQuestion.getType()) {
		case Question.TEXT_INPUT:
			current = new TextQuestionFragment();
			break;
		case Question.MULTI_CHOICE_4:
		case Question.MULTI_CHOICE_2:
			current = new MultiChoiceQuestionFragment();
			break;
		}
		Bundle b = getIntent().getExtras();
		if(b==null){
			b=new Bundle();
		}
		if(mQuestion!=null & b!=null){
			b.putSerializable("question", mQuestion);
		}
		current.setArguments(b);
		current.setQuestion(mQuestion);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, current).commit();

	}

	//
	// @Override
	// public void onBackPressed() {
	// }

}
