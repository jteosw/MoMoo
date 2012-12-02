package com.csewannabe.selection;

import com.csewannabe.R;
import com.csewannabe.R.layout;
import com.csewannabe.selection.*;

import android.R.color;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AssignmentFragment extends ListFragment {
	
	ViewPager parentViewer;
	
	View prevListEntry;
	Color prevColor;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parentViewer = (ViewPager) getActivity().findViewById(R.id.selection_pager);
		DataCollector mData = new DataCollector();
		//Sets up a custom ListAdapter 
		setListAdapter(new AssignmentArrayAdapter<String>(getActivity(), R.layout.assignment_list_item, R.id.list_item_textview, mData.getAssignments()));
		
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
		if(prevListEntry == null) {
			v.setBackgroundColor(Color.argb(150, 242, 47, 38));
			prevListEntry = v;
		} else {
			prevListEntry.setBackgroundColor(Color.alpha(0));
			v.setBackgroundColor(Color.argb(150, 242, 47, 38));
			prevListEntry = v;
		}
		((AssignmentSelectable) getActivity()).selectAssignment(selected);
		parentViewer.setCurrentItem(1, true);
		
		Log.d("LIST_CLICKED", "Cliked @: " + selected);
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	public interface AssignmentSelectable {
		public void selectAssignment(String assignment);
	}
}
