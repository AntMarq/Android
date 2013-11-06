/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.slidelittlereptiles;

import com.example.slidelittlereptiles.controller.ActuListFragment;
import com.example.slidelittlereptiles.controller.PharmListFragment;
import com.example.slidelittlereptiles.controller.ShowMapFragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
public class MainActivity extends ActionBarActivity {
	private static final String TAG = "MainActivity";
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    String tag = "MainActivity";
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] menuTitles;
    private ActuListFragment actuListFragment;
    private PharmListFragment pharmListFragment ;
    private ShowMapFragment showMapFragment ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
       
        mTitle = mDrawerTitle = getTitle();
        menuTitles = getResources().getStringArray(R.array.reptiles_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, menuTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
         //      invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
            	getSupportActionBar().setTitle(mDrawerTitle);
           //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) 
        {
        	
            selectItem(0);
        }
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) 
    {
        // If the nav drawer is open, hide action items related to the content view
        
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Only handle with DrawerToggle if the drawer indicator is enabled.
        if (mDrawerToggle.isDrawerIndicatorEnabled() &&
                mDrawerToggle.onOptionsItemSelected(item))        
        {
        	Log.v(tag, "Drawer open");
        //	onBackPressed();
        	
            return true;
        }
		 return super.onOptionsItemSelected(item);
        
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
  
    private void selectItem(int position) 
    {
    	FragmentManager manager = getSupportFragmentManager();
    	FragmentTransaction ft = manager.beginTransaction();
    	Log.v(tag, "selectItem !");
    	  // update the main content by replacing fragments
    	 

        switch (position) 
        {
        
        case 0: 
        	String tagActu = "Actu" ;
        	
        	if(actuListFragment == null)
        	{
        		actuListFragment = new ActuListFragment();     		
                ft.add(R.id.content_frame,actuListFragment,tagActu);
               
        	}
        	else
        	{
        		if(pharmListFragment != null && showMapFragment != null)
        		{
        			Log.v(tag, "pharmListFragment not null in first fragment");
        			ft.hide(pharmListFragment).hide(showMapFragment).show(actuListFragment) ;           
        		}
        		
        		if(pharmListFragment != null && showMapFragment == null)
        		{
        			Log.v(tag, "pharmListFragment not null but map is null");
        			ft.hide(pharmListFragment).show(actuListFragment) ;                    
        		}        		
        		if(pharmListFragment == null && showMapFragment != null)
        		{
        			Log.v(tag, "pharmListFragment not null but pharmListFragment is null");
        			ft.hide(showMapFragment).show(actuListFragment) ;                  
        		}     
        		actuListFragment.setHasOptionsMenu(true);
        	}
        	ft.commit();

            break;
            
        case 1:
        	String tagPharma = "Pharma" ;
        	if(pharmListFragment == null)
        	{
	        	pharmListFragment = new PharmListFragment(); 	        	
	        	ft .add(R.id.content_frame,pharmListFragment,tagPharma); 
	        	actuListFragment.setHasOptionsMenu(false);
        	}
        	else
        	{
        		if(actuListFragment != null && showMapFragment != null)
        		{
        			Log.v(tag, "pharmListFragment not null in first fragment");
        			ft.hide(actuListFragment).hide(showMapFragment).show(pharmListFragment) ;
                    
        		}
        		
        		if(actuListFragment != null && showMapFragment == null)
        		{
        			Log.v(tag, "pharmListFragment not null but map is null");
        			ft.hide(actuListFragment).show(pharmListFragment) ;
                    
        		}
        		
        		if(actuListFragment == null && showMapFragment != null)
        		{
        			Log.v(tag, "pharmListFragment not null but pharmListFragment is null");
        			ft.hide(showMapFragment).show(pharmListFragment) ;
                    
        		}
        		pharmListFragment.setHasOptionsMenu(true);
        	}
        	ft.commit();  	
            break;
            
        case 2:
        	String tagMap = "Map" ;
        	if(showMapFragment == null)
        	{
	            showMapFragment = new ShowMapFragment();
	           
	            ft.add(R.id.content_frame,showMapFragment,tagMap)  ;      
	            actuListFragment.setHasOptionsMenu(false);
	           
        	}
        	else
        	{
        		if(actuListFragment != null && pharmListFragment != null)
        		{
        			Log.v(tag, "pharmListFragment not null in first fragment");
        			ft.hide(actuListFragment).hide(pharmListFragment).show(showMapFragment)  ;
                    
        		}
        		
        		if(actuListFragment != null && pharmListFragment == null)
        		{
        			Log.v(tag, "pharmListFragment not null but map is null");
        			ft.hide(actuListFragment).show(showMapFragment) ;
                    
        		}
        		
        		if(actuListFragment == null && pharmListFragment != null)
        		{
        			Log.v(tag, "pharmListFragment not null but pharmListFragment is null");
        			ft.hide(pharmListFragment).show(showMapFragment) ;
                    
        		}
        	}
        	
        	ft.commit();
            break;
        }

      

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(menuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
   

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    
    }