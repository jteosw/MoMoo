package com.csewannabe;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.csewannabe.selection.AssignmentFragment;
import com.csewannabe.selection.ProblemsFragment;

public class SelectionActivity extends FragmentActivity implements AssignmentFragment.AssignmentSelectable{
	
	String mChosenAssignment;
	SelectionPagerAdapter mSelectionAdapter;
	ViewPager mViewer;
	ProblemsFragment problemFragment;
	
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
		
		mSelectionAdapter = new SelectionPagerAdapter(getSupportFragmentManager());
		mViewer = (ViewPager) findViewById(R.id.selection_pager);
		mViewer.setAdapter(mSelectionAdapter);
		
		/*
		//Set up first class fragment
		ClassFragment mClassFragment = new ClassFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, mClassFragment).commit();
	*/
	}
	
	public class SelectionPagerAdapter extends FragmentStatePagerAdapter {
		
		int currCount = 1;
		
		public SelectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position) {
				case 0:
					return new AssignmentFragment();
				case 1:
					problemFragment = new ProblemsFragment(mChosenAssignment);
					return problemFragment;
					
				default:
					return null;
			}
		}

		@Override
		public int getCount() {
			return currCount;
		}
		
	}

	@Override
	public void selectAssignment(String assignment) {
		mChosenAssignment = assignment;
		mSelectionAdapter.currCount = 2;
		if(problemFragment != null) {
			problemFragment.linkAdapter(assignment); 
		}

		//mSelectionAdapter = new SelectionPagerAdapter(getSupportFragmentManager());
		//mViewer.setAdapter(mSelectionAdapter);

	}

}