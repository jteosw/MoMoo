package com.csewannabe.selection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.csewannabe.R;

public class ProblemsFragment extends ListFragment {
	
	public static final String CURR_ASSIGN = "com.csewannabe.selection.AssignmentFragment.assign";
	public static final String PREV_DATA = "com.csewannabe.selection.AssignmentFragment.prev";
	public static final String CURR_DATA = "com.csewannabe.selection.AssignmentFragment.curr";
	public static final String NEXT_DATA = "com.csewannabe.selection.AssignmentFragment.next";
	
	String mAssignment;
	DataCollector mData;
	String nextProblem;
	String prevProblem;
	
	public ProblemsFragment() {
		super();
	}
	
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
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String selected = (String) getListView().getItemAtPosition(position);
		
		Intent intent = new Intent(getActivity(), com.csewannabe.CanvasActivity.class);
		
		intent.putExtra(CURR_DATA, selected);
		intent.putExtra(CURR_ASSIGN, mAssignment);
		
		if (position >= 1) {
			intent.putExtra(PREV_DATA, (String) getListView().getItemAtPosition(position-1));
		} else {
			intent.putExtra(PREV_DATA, (String) null);
		}
		
		startActivity(intent);
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	public void linkAdapter(String assignment) {
		setListAdapter(new ProblemArrayAdapter<String>(getActivity(), R.layout.problem_list_item, R.id.list_item_textview, mData.getProblems(assignment)));

	}
}