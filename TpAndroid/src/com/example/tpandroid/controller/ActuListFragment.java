package com.example.tpandroid.controller;

import com.example.tpandroid.R;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tpandroid.GlobalApplication;
import com.example.tpandroid.loader.ActuLoadRss;
import com.example.tpandroid.model.ObjActuRss;
import com.example.tpandroid.view.ActuRssAdapter;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;


public class ActuListFragment extends ListFragment 
{
	
	private GlobalApplication application;
    private ActuRssAdapter adapter;
    private ActuLoadRss loadRSS;
    private Handler		listeHandler;
	public String tag = "RSSfragment";
	private CharSequence lastUpdated;
	 

	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
			super.onActivityCreated(savedInstanceState);
			setHasOptionsMenu(true);
	}
	
//Inflate View
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		final PullToRefreshListView view = (PullToRefreshListView) inflater.inflate (getResources ().getLayout (R.layout.actu_rss_layout), container, false);
		if(loadRSS == null)
		{
			getActivity().setProgressBarIndeterminateVisibility(true);
		}
		
//Listener for refreshOnline
		listeHandler = new Handler ()
		{				
				public void handleMessage (Message msg)
				{
					int done = msg.arg1;
					if (done == 0)
					{
						getActivity().setProgressBarIndeterminateVisibility(false);
						Toast.makeText (getActivity ().getApplicationContext (), "Une erreur est survenue",Toast.LENGTH_SHORT).show ();
					}
					else
					{							
						getActivity().setProgressBarIndeterminateVisibility(false);
						adapter.notifyDataSetChanged ();
						view.onRefreshComplete();
					}
				}
		};
			
				application = (GlobalApplication) getActivity ().getApplicationContext ();
				loadRSS = new ActuLoadRss (getActivity().getApplicationContext());
				loadRSS.setHandler (listeHandler);	
				loadRSS.refreshOnline();
				String dateString = DateUtils.formatDateTime(application.getApplicationContext(),System.currentTimeMillis(), DateUtils.FORMAT_SHOW_DATE);
	            String timeString = DateUtils.formatDateTime(application.getApplicationContext(),System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
	        	lastUpdated = "Dernière mise à jour le " + dateString + " à " + timeString ;
		        adapter = new ActuRssAdapter(getActivity ().getBaseContext (), loadRSS);
		      
//PulltoRefresh	      
		        view.setOnRefreshListener(new OnRefreshListener() 
		        {
			        @Override
			        public void onRefresh() 
			        {
			        		        	
			        	loadRSS.refreshOnline();
						view.setLastUpdated(lastUpdated);
			        }
			    });	
		        setListAdapter (adapter);
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
				application.setObjActuRss(objActuRss);			
			}
			else
			{
				application.setObjActuRss (objActuRss);
				Intent intent = new Intent (getActivity ().getApplicationContext (), ActuRssDetail.class);
				String abTitle  = objActuRss.getTitle();
				
//Add title in the next fragment actionbar 
				intent.putExtra("title", abTitle);
				startActivity (intent);
			}
	}
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		  inflater.inflate(R.menu.meteo_menu, menu);
	}
		 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	
		return false;
	}
		 
	@Override
	public void onConfigurationChanged(Configuration newConfig) 
	{
		super.onConfigurationChanged(newConfig);
	}
}
