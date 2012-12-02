package com.csewannabe.selection;

import com.csewannabe.R;
import com.csewannabe.R.layout;
import com.csewannabe.selection.*;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ClassFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Sets up a custom ListAdapter 
		setListAdapter(new CustomArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.list_item_textview, DataCollector.classes));
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View finalView = inflater.inflate(R.layout.class_listfragment, null);
		
		return finalView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		
	}
}
