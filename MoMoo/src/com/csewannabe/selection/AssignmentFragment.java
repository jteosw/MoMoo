package com.csewannabe.selection;

import com.csewannabe.R;
import com.csewannabe.R.layout;
import com.csewannabe.selection.*;

import android.R.color;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AssignmentFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Sets up a custom ListAdapter 
		setListAdapter(new CustomArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.list_item_textview, DataCollector.assignments));
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View finalView = inflater.inflate(R.layout.listfragment, null);
		
		return finalView;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String clicked = (String) getListView().getItemAtPosition(position);
		v.setBackgroundColor(Color.argb(150, 242, 47, 38));
		Log.d("LIST_CLICKED", "Cliked @: " + clicked);
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
}
