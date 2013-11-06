package com.example.slidelittlereptiles.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.slidelittlereptiles.GlobalVar;
import com.example.slidelittlereptiles.PharmaLoadRSS;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.R.id;
import com.example.slidelittlereptiles.R.layout;
import com.example.slidelittlereptiles.R.menu;
import com.example.slidelittlereptiles.model.ObjPharmRss;
import com.example.slidelittlereptiles.view.PharmRssAdapter;
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;


public class PharmListFragment extends ListFragment 
{
	
	 	GlobalVar application;
	    private PharmRssAdapter adapter;
	   PharmaLoadRSS loadRSS;
	    private Handler		listeHandler;
		public String tag = "RSSfragment";
		 private MenuItem mRefresh = null;
		 CharSequence lastUpdated;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		
		final PullToRefreshListView view = (com.markupartist.android.widget.PullToRefreshListView) inflater.inflate (getResources ().getLayout (R.layout.pharm_rss_layout), container, false);
		if(loadRSS == null)
		{
			getActivity().setProgressBarIndeterminateVisibility(true);
		}
			
		
        listeHandler = new Handler ()
		{
			
			public void handleMessage (Message msg)
			{
				int done = msg.arg1;
				if (done == 0)
				{
					getActivity().setProgressBarIndeterminateVisibility(false);
					MenuItemCompat.setActionView(mRefresh, null);
					Toast.makeText (getActivity ().getApplicationContext (), "Une erreur est survenue",
							Toast.LENGTH_SHORT).show ();
				}
				else
				{
					if(mRefresh != null)
					{
						MenuItemCompat.setActionView(mRefresh, null);
					}		
					getActivity().setProgressBarIndeterminateVisibility(false);
					adapter.notifyDataSetChanged ();
					view.onRefreshComplete();
				}
			}
		};
	
			application = (GlobalVar) getActivity ().getApplicationContext ();
			loadRSS = new PharmaLoadRSS (getActivity().getApplicationContext());
			loadRSS.setHandler (listeHandler);	
			loadRSS.refreshOnline();
	        adapter = new PharmRssAdapter(getActivity ().getBaseContext (), loadRSS);
	        
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
	        setListAdapter (adapter);

        return view;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
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
    	ObjPharmRss objPharmaRss = (ObjPharmRss) listView.getItemAtPosition (position);
		PharmRssDetailFragment detailfrag = (PharmRssDetailFragment) getFragmentManager ().findFragmentById (R.id.webviewpharmarss);

		if (detailfrag != null && detailfrag.isInLayout ())
		{
			// mise à jour
			application.setObjPharmaRss(objPharmaRss);			
		}
		else
		{
			application.setObjPharmaRss(objPharmaRss);
			Intent intent = new Intent (getActivity ().getApplicationContext (), PharmRssDetail.class);
			String abTitle  = objPharmaRss.getTitle();
			intent.putExtra("title", abTitle);
			startActivity (intent);
		}
	}
	

	 @Override
	    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
	 { super.onCreateOptionsMenu(menu, inflater);
	        inflater.inflate(R.menu.rss_menu, menu);
	      
	 }
	 
	 @Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{
			switch (item.getItemId()) {
			    case R.id.refresh:
			    	mRefresh = item;
			    	MenuItemCompat.setActionView(mRefresh, R.layout.progressbar);
			    	loadRSS.refreshOnline();
			    break;        
			}
			return false;
		}

	
	

}
