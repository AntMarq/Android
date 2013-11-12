/*
Ï * Copyright 2013 The Android Open Source Project
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

package com.example.tpandroid;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tpandroid.controller.ActuListFragment;
import com.example.tpandroid.controller.PharmListFragment;
import com.example.tpandroid.controller.ShowMapFragment;
import com.example.tpandroid.loader.ArrayDrawerData;
import com.example.tpandroid.model.ObjDrawer;
import com.example.tpandroid.view.CustomArrayAdapter;




public class MainActivity extends ActionBarActivity 
{
	private static final String tag = "MainActivity";
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String tagMap = "Map" ;
    private String tagActu = "Actu" ;
    private String tagPharma = "Pharma" ;
    private ActuListFragment actuListFragment = new ActuListFragment();
    private PharmListFragment pharmListFragment = new PharmListFragment() ;
    private ShowMapFragment showMapFragment = new ShowMapFragment();
    private ArrayDrawerData dataDrawer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
       
        
        mTitle = mDrawerTitle = getTitle();
     //   menuTitles = getResources().getStringArray(R.array.actu_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
    
      //  mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, menuTitles));
        dataDrawer = new ArrayDrawerData(this);
   //     Log.v(tag, "test list" + dataDrawer.toString());
        mDrawerList.setAdapter(new CustomArrayAdapter (this.getBaseContext(), dataDrawer) );
        
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
            public void onDrawerClosed(View view) 
            {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) 
            {
            	getSupportActionBar().setTitle(mDrawerTitle);
                supportInvalidateOptionsMenu();// creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

//Select default item
        if (savedInstanceState == null) 
        {
            selectItem(1);
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
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Only handle with DrawerToggle if the drawer indicator is enabled.
        if (mDrawerToggle.isDrawerIndicatorEnabled() && mDrawerToggle.onOptionsItemSelected(item))        
        {
            return true;
        }
		 return super.onOptionsItemSelected(item);
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener 
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
        {
        	
            selectItem(position);
        }
    }
    
    private void selectItem(int position) 
    {
    	FragmentManager manager = getSupportFragmentManager();
    	FragmentTransaction ft = manager.beginTransaction();

        switch (position) 
        {        
        	case 1: 
        	   
//Display ActuListFragment and hide others fragments
        	
        	if(manager.findFragmentByTag(tagActu) == null )
        	{
                ft.add(R.id.content_frame,actuListFragment,tagActu);
                pharmListFragment.setHasOptionsMenu(false);
	        	showMapFragment.setHasOptionsMenu(false);
        	}	
        	else
        	{       	
        		ft.hide(pharmListFragment).hide(showMapFragment).show(actuListFragment) ;   
        		actuListFragment.setHasOptionsMenu(true);
        	}
        	ft.commit();
            break;
            
        case 2:
  
//Display PharmaListfragment and hide others fragments
        	
        	if(manager.findFragmentByTag(tagPharma) == null )
        	{	        	
	        	ft .add(R.id.content_frame,pharmListFragment,tagPharma).hide(actuListFragment).hide(showMapFragment).show(pharmListFragment); 	        	
	        	actuListFragment.setHasOptionsMenu(false);
	        	showMapFragment.setHasOptionsMenu(false);
        	}
        	else
        	{
        		ft.hide(actuListFragment).hide(showMapFragment).show(pharmListFragment) ;   
        		pharmListFragment.setHasOptionsMenu(true);
        	}
        	ft.commit();  	
            break;
            
        case 4:
        	
//Display ShowMapfragment and hide others fragments 
        	
        	if(manager.findFragmentByTag(tagMap) == null )
        	{          
	            ft.add(R.id.content_frame,showMapFragment,tagMap).hide(actuListFragment).hide(pharmListFragment).show(showMapFragment)  ;      
	            actuListFragment.setHasOptionsMenu(false);
	            pharmListFragment.setHasOptionsMenu(false);	           
        	}

        	else
        	{
        		ft.hide(actuListFragment).hide(pharmListFragment).show(showMapFragment);
        		showMapFragment.setHasOptionsMenu(true);
        	}   
        	
        	ft.commit();
            break;
        }
        
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        String dataString = (((ObjDrawer)dataDrawer.get(position))).getTitle();      
        setTitle(dataString);
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

