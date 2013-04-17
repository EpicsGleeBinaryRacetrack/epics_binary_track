package epics.binarytrack.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import epics.binarytrack.R;
import epics.binarytrack.questions.MultiChoiceQuestion;
import epics.binarytrack.questions.Question;

public class MultiChoiceQuestionFragment extends QuestionFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = null;
		if(mQuestion.getType()==Question.MULTI_CHOICE_4){
			layout=inflater.inflate(R.layout.choice_4_question, container,
						false);
		}else{
			layout=inflater.inflate(R.layout.choice_2_question, container,
					false);
		}

		TextView text = (TextView) layout.findViewById(R.id.textView1);
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				CharSequence f = ((Button) v).getText();
				String input = String.valueOf(f);
				mCallback.onQuestionAnswered(mQuestion.processResponse(input));
			}
		};
		MultiChoiceQuestion multi = (MultiChoiceQuestion) mQuestion;
		text.setText(multi.getQuestion());

		ArrayList<String> answers = multi.getAnswers();
		
		Log.d("question",multi.getQuestion());	
		Log.d("num of answers", "There are to many answers: "+answers.size());

		Button b = (Button) layout.findViewById(R.id.button1);
		b.setOnClickListener(listener);
		Collections.shuffle(answers);
		String k =answers.get(0); 
		b.setText(k);
		Log.d("question",k);	


		b = (Button) layout.findViewById(R.id.button2);
		b.setOnClickListener(listener);
		k =answers.get(1); 
		Log.d("question",k);
		b.setText(k);

		if (answers.size() == 4) {
			b = (Button) layout.findViewById(R.id.button3);
			b.setOnClickListener(listener);
			b.setText(answers.get(2));

			b = (Button) layout.findViewById(R.id.button4);
			b.setOnClickListener(listener);
			b.setText(answers.get(3));
		}
		return layout;
	}

}
