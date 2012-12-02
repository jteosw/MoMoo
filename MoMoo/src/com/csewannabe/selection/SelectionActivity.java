package com.csewannabe.selection;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.csewannabe.R;


public class SelectionActivity extends FragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selection_activity);
		
		Fragment mClassFragment = new ClassFragment();
		
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, mClassFragment).commit();
	}


}
