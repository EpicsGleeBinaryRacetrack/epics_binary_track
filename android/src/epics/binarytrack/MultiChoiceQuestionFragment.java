package epics.binarytrack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import epics.binarytrack.questions.Binary2Decimal;
import epics.binarytrack.questions.Question;

public class MultiChoiceQuestionFragment extends Fragment {

	private TextView mTextView = null;
	private EditText mEditText = null;
	private Question mQuestion = null;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	View layout = inflater.inflate(R.layout.choice_4_question, container, false);
    	savedInstanceState.getInt(Question.QUESTION_TYPE);
//    	mTextView = (TextView)layout.findViewById(R.id.textView1);
//    	mEditText = (EditText)layout.findViewById(R.id.editText1);
//    	mEditText.setOnEditorActionListener(new OnEditorActionListener() {
//			@Override
//			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//				String input = mEditText.getEditableText().toString();
//				Toast.makeText(MultiChoiceQuestionFragment.this.getActivity(), mQuestion.getAnswer(), Toast.LENGTH_SHORT).show();
//				if(mQuestion.processResponse(input.trim()) ){
//					Toast.makeText(MultiChoiceQuestionFragment.this.getActivity(), "You are right!", Toast.LENGTH_SHORT).show();
//				}else{
//					Toast.makeText(MultiChoiceQuestionFragment.this.getActivity(), "You are wrong!", Toast.LENGTH_SHORT).show();
//				}
//				mEditText.setText("");
//				reset();
//				InputMethodManager imm = (InputMethodManager)MultiChoiceQuestionFragment.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//				imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
//				return true;
//			}
//		});
//		reset();
        return layout;
    }
    
    public void buttonPress(View v){
    	Toast.makeText(getActivity(), "button was pressed", Toast.LENGTH_SHORT).show();
    }
	
	public void reset(){
		mQuestion = new Binary2Decimal();
		mTextView.setText(mQuestion.getQuestion());
	}

}
