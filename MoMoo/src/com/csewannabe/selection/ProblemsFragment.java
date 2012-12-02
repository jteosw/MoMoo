package com.csewannabe.selection;

import com.csewannabe.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProblemsFragment extends ListFragment {
	
	String mAssignment;
	DataCollector mData;
	
	public ProblemsFragment(String assignmentChosen) {
		super();

		mAssignment = assignmentChosen;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = new DataCollector();
		//Sets up a custom ListAdapter 
		linkAdapter(mAssignment);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View finalView = inflater.inflate(R.layout.listfragment, null);
		
		return finalView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	public void linkAdapter(String assignment) {
		setListAdapter(new CustomArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.list_item_textview, mData.getProblems(assignment)));

	}
}