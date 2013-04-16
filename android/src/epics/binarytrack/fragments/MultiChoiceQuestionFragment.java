package epics.binarytrack.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import epics.binarytrack.R;

public class MultiChoiceQuestionFragment extends QuestionFragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View layout = inflater.inflate(R.layout.choice_4_question, container, false);
    	OnClickListener listener = new OnClickListener(){
			@Override
			public void onClick(View v) {
				CharSequence f = ((Button)v).getText();
				String input = String.valueOf(f);
				mCallback.onQuestionAnswered(mQuestion.processResponse(input));
			}    		
    	};
    	Button b = (Button) layout.findViewById(R.id.button1);
    	b.setOnClickListener(listener);
    	b = (Button) layout.findViewById(R.id.button2);
    	b.setOnClickListener(listener);
    	b = (Button) layout.findViewById(R.id.button3);
    	b.setOnClickListener(listener);
    	b = (Button) layout.findViewById(R.id.button4);
    	b.setOnClickListener(listener);
        return layout;
    }

}
