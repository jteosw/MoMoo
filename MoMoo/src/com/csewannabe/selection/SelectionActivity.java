package com.csewannabe.selection;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.csewannabe.R;

public class SelectionActivity extends FragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.selection_activity);
		
		//Set up action bar tab navigation
		final ActionBar mActionbar = getActionBar();
		mActionbar.setDisplayShowHomeEnabled(false); //Gets rid of ugly Actionbar
		mActionbar.setDisplayShowTitleEnabled(false);
		mActionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.TabListener mTabListener = new ActionBar.TabListener() {
			@Override
			public void onTabReselected(Tab tab,
					android.app.FragmentTransaction ft) {				
			}

			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction ft) {				
			}

			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {		
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
		
		SelectionPagerAdapter mSelectionAdapter = new SelectionPagerAdapter(getSupportFragmentManager());
		ViewPager mViewer = (ViewPager) findViewById(R.id.selection_pager);
		mViewer.setAdapter(mSelectionAdapter);
		
		/*
		//Set up first class fragment
		ClassFragment mClassFragment = new ClassFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, mClassFragment).commit();
	*/
	}
	
	public static class SelectionPagerAdapter extends FragmentPagerAdapter {

		public SelectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position) {
				case 0:
					return new AssignmentFragment();
				case 1:
					return new AssignmentFragment();
				case 2:
					return new AssignmentFragment();
				default:
					return null;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}
		
	}

}