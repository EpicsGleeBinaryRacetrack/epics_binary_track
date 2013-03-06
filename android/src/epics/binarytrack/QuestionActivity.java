package epics.binarytrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

public class QuestionActivity extends FragmentActivity {
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            MultiChoiceQuestionFragment firstFragment = new MultiChoiceQuestionFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }
    
    public void buttonPress(View v){
    	Toast.makeText(this, "button was pressed", Toast.LENGTH_SHORT).show();
    }


}
