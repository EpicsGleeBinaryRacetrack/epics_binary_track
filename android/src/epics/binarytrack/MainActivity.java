package epics.binarytrack;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {
	ArrayList<String> usernames = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void startButton(View v){
		AutoCompleteTextView username = (AutoCompleteTextView)this.findViewById(R.id.usernames);
		String input = username.getEditableText().toString();
		if(usernames.indexOf(input) == -1)
			usernames.add(input);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, usernames);
		username.setAdapter(adapter);
		Toast.makeText(MainActivity.this, "Welcome " + input, Toast.LENGTH_SHORT).show();
		this.startActivity(new Intent(this,QuestionScreen.class));
	}

}
