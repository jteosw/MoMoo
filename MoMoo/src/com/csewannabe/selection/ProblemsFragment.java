package com.csewannabe.selection;

import com.csewannabe.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProblemsFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Sets up a custom ListAdapter 
		setListAdapter(new CustomArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.list_item_textview, DataCollector.problems));
		
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
}