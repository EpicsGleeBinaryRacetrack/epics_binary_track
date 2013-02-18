package epics.binarytrack;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class QuestionScreen extends Activity {
	
	int binary = 0;
	String binaryStr = "";
	String tmp = "What is the decimal number for "+binaryStr+" ?";
	
	TextView textView = null;
	EditText editText = null;
	Random random = new Random();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.android_question_screen);
		textView = (TextView)this.findViewById(R.id.textView1);
		editText = (EditText)this.findViewById(R.id.editText1);
		editText.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				String input = editText.getEditableText().toString();
				int answer = Integer.parseInt(input);
				if(answer == binary ){
					Toast.makeText(QuestionScreen.this, "You are right!", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(QuestionScreen.this, "You are wrong!", Toast.LENGTH_SHORT).show();
				}
				editText.setText("");
				reset();
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
				return true;
			}
		});
		reset();
	} 
	
	public void reset(){
		binaryStr = Integer.toBinaryString(random.nextInt( 32 ));
		binary = Integer.parseInt(binaryStr,2);
		tmp = "What is the decimal number for "+binaryStr+" ?";
		textView.setText(tmp);
	}

}
