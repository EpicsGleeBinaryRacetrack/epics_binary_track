package epics.binarytrack.fragments;

import epics.binarytrack.OnQustionListener;
import epics.binarytrack.questions.Question;
import android.app.Activity;
import android.support.v4.app.Fragment;

public class QuestionFragment extends Fragment {
	
	protected OnQustionListener mCallback;
	protected Question mQuestion = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnQustionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public void setQuestion(Question q){
    	this.mQuestion=q;
    }
}
