package org.chonger.ui.activity;


import org.chonger.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * »’¿˙√Ê∞Â
 * @author Daniel
 *
 */
public class Project extends Fragment {
	private TextView tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_project, container,false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//tv=(TextView)getView().findViewById(R.id.title)
	}
	
	
	
}
