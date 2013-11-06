package com.example.slidelittlereptiles.controller;

import java.util.Date;

import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.slidelittlereptiles.ActuLoadRss;
import com.example.slidelittlereptiles.GlobalVar;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.model.ObjActuRss;
import com.example.slidelittlereptiles.view.ActuRssAdapter;
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;


public class ActuListFragment extends ListFragment 
{
	
	GlobalVar application;
    private ActuRssAdapter adapter;
    ActuLoadRss loadRSS;
    private Handler		listeHandler;
	public String tag = "RSSfragment";
	 int mCurCheckPosition = 0;
	 private MenuItem mRefresh = null;
	 CharSequence lastUpdated;
	 

	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
			
	 super.onCreate(savedInstanceState);
	 Log.v(tag, "onCreate" + savedInstanceState);
	 
	 setHasOptionsMenu(true);
	 
	 
	 
	
	 if(savedInstanceState != null) 
	 {
	        int currentfrag = savedInstanceState.getInt("currentfrag", 0);
	       Log.v(tag, "savedInstanceState != null");
	    }	
	 }
	 
	 
	 
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
			super.onActivityCreated(savedInstanceState);
			
		//	getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
			 
			Log.v(tag, "onActivityCreated");
	}
	
	 @Override
	 public void onSaveInstanceState(Bundle outState) 
	 {
	     super.onSaveInstanceState(outState);   
	 }
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		Log.v(tag, "onCreateView " + savedInstanceState);
		final PullToRefreshListView view = (PullToRefreshListView) inflater.inflate (getResources ().getLayout (R.layout.actu_rss_layout), container, false);

		listeHandler = new Handler ()
		{
				
				public void handleMessage (Message msg)
				{
					int done = msg.arg1;
					if (done == 0)
					{
						MenuItemCompat.setActionView(mRefresh, null);
						Toast.makeText (getActivity ().getApplicationContext (), "Une erreur est survenue",Toast.LENGTH_SHORT).show ();
					}
					else
					{
						if(mRefresh != null)
						{
							MenuItemCompat.setActionView(mRefresh, null);
						}								
						adapter.notifyDataSetChanged ();
						view.onRefreshComplete();
					}
				}
		};
			
				application = (GlobalVar) getActivity ().getApplicationContext ();
				loadRSS = new ActuLoadRss (getActivity().getApplicationContext());
				loadRSS.setHandler (listeHandler);	
				loadRSS.refreshOnline();
		        adapter = new ActuRssAdapter(getActivity ().getBaseContext (), loadRSS);
		        setListAdapter (adapter);
		      
		        view.setOnRefreshListener(new OnRefreshListener() 
		        {
			        @Override
			        public void onRefresh() 
			        {
			        	
			        	String dateString = DateUtils.formatDateTime(application.getApplicationContext(),System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE);
			            String timeString = DateUtils.formatDateTime(application.getApplicationContext(),System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
			        	lastUpdated = "Mise ˆ jour le " + dateString + " ˆ " + timeString ;
			        	loadRSS.refreshOnline();
			        	
						view.setLastUpdated(lastUpdated);
			        }
			    });	
		    return view;
	}
		
	
		
	@Override
	public void onResume()
	{
		super.onResume();
		
	}
		
	@Override
	public void onPause ()
	{
		super.onPause();
			
	}
		
	@Override
	public void onDestroy ()
	{
		super.onDestroy();		
	}
		
		
	@Override
	public void onListItemClick (ListView listView, View view, int position, long id)
	{
			ObjActuRss objActuRss = (ObjActuRss) listView.getItemAtPosition (position);
			ActuRssDetailFragment detailfrag = (ActuRssDetailFragment) getFragmentManager ().findFragmentById (R.id.webviewacturss);
		
			if (detailfrag != null && detailfrag.isInLayout ())
			{
				// mise à jour
				application.setObjActuRss(objActuRss);			
			}
			else
			{
				application.setObjActuRss (objActuRss);
				Intent intent = new Intent (getActivity ().getApplicationContext (), ActuRssDetail.class);
				String abTitle  = objActuRss.getTitle();
				intent.putExtra("title", abTitle);
				startActivity (intent);
			}
	}
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		  inflater.inflate(R.menu.rss_menu, menu);
	}
		 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if (item.getItemId() == R.id.refresh)
			{
					mRefresh = item;
					MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
					
				//    	getActivity().setProgressBarIndeterminateVisibility(true);
				    	loadRSS.refreshOnline();
				     
			}
				/*if(item.getItemId() == android.R.id.home)
				{
					getActivity().onBackPressed();
				}*/
				
				return super.onOptionsItemSelected(item);

		}
		 
	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{
		super.onConfigurationChanged(newConfig);
	}
}
