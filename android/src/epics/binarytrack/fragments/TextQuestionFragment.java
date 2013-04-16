package epics.binarytrack.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import epics.binarytrack.R;

public class TextQuestionFragment extends QuestionFragment {

	private TextView mTextView = null;
	private EditText mEditText = null;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreateView(inflater, container, savedInstanceState);
    	View layout = inflater.inflate(R.layout.text_question, container, false);
    	mTextView = (TextView)layout.findViewById(R.id.textView1);
    	mEditText = (EditText)layout.findViewById(R.id.editText1);
    	mTextView.setVisibility(View.INVISIBLE);
    	mTextView.setVisibility(View.VISIBLE);
    	mEditText.setVisibility(View.INVISIBLE);
    	mEditText.setVisibility(View.VISIBLE);
    	
    	String q = mQuestion.getQuestion();
		mTextView.setText(q);
    	mEditText.setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String input = mEditText.getEditableText().toString();
				boolean isCorrect = mQuestion.processResponse(input.trim());
				mEditText.setText("");
				InputMethodManager imm = (InputMethodManager)TextQuestionFragment.this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
				mCallback.onQuestionAnswered(isCorrect);
				return true;
			}
		});
        return layout;
    }
}
