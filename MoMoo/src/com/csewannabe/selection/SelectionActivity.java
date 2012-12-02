package com.csewannabe.selection;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.csewannabe.R;

public class SelectionActivity extends FragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_NO_TITLE); //Gets rid of ugly title bar
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.selection_activity);
		

		
		//Set up action bar tab navigation
		final ActionBar mActionbar = getActionBar();
		mActionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.TabListener mTabListener = new ActionBar.TabListener() {
			@Override
			public void onTabReselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		Tab assignmentTab = mActionbar.newTab();
		assignmentTab.setText("Assignments");
		assignmentTab.setTabListener(mTabListener);
		Tab questionTab = mActionbar.newTab();
		questionTab.setText("Questions");
		questionTab.setTabListener(mTabListener);
		mActionbar.addTab(assignmentTab);
		mActionbar.addTab(questionTab);
		
		//Set up first class fragment
		ClassFragment mClassFragment = new ClassFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, mClassFragment).commit();
	}
	


}