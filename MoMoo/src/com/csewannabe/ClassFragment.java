package com.csewannabe;

import com.csewannabe.selection.DataCollector;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class ClassFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//setListAdapter(new ArrayAdapter<T>(this, textViewResourceId, DataCollector.classes));
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		
	}
}
