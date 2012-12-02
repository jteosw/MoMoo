/**
 * Custom array adapter that allows the use of custom 
 * fonts in the TextView. Can only use a basic TextView
 * specifying constructor.
 * 
 * @author Zachary
 * @version 1 December 2012
 */

package com.csewannabe.selection;

import com.csewannabe.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;



public class CustomArrayAdapter<T> extends ArrayAdapter<T> implements ListAdapter{
	
	Typeface mTypeface;
	
	public CustomArrayAdapter(Context context, int textViewResourceId, T[] objects) {
		super(context, textViewResourceId, objects);
		mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/quicksand.ttf");
	}
	
	public CustomArrayAdapter(Context context, int resource, int textViewResourceId, T[] objects) {
		super(context, resource, textViewResourceId, objects);
		mTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/quicksand.ttf");
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =  super.getView(position, convertView, parent);
		((TextView) view.findViewById(R.id.list_item_textview)).setTypeface(mTypeface);
		return view;
	}
	
	
	

}
