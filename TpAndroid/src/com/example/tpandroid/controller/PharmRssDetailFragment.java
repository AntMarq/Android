package com.example.tpandroid.controller;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.tpandroid.GlobalApplication;
import com.example.tpandroid.R;
import com.example.tpandroid.model.ObjPharmRss;

public class PharmRssDetailFragment extends Fragment
{
	
	GlobalApplication application;
	

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate (R.layout.pharmarssdetailfrag, container, false);

		application = (GlobalApplication) getActivity ().getApplicationContext ();

		WebView viewLong = (WebView) view.findViewById (R.id.webviewpharmarss);

		setHasOptionsMenu(true);
		
		final ObjPharmRss newReptiles = application.getObjPharmaRss ();

		viewLong.loadUrl(newReptiles.getLink());
		viewLong.setWebViewClient(new WebViewClient()
		{				 
			@Override
	        public void onPageStarted(WebView view, String url, Bitmap favicon) 
			{
	            // TODO Auto-generated method stub
	            super.onPageStarted(view, url, favicon);
	            getActivity().setProgressBarIndeterminateVisibility(true);
	        }

	            /** Callback method, executed when the page is completely loaded */
	            @Override
	            public void onPageFinished(WebView view, String url) 
	            {
	                 super.onPageFinished(view, url);
	 
	                 Toast.makeText(getActivity().getBaseContext(),
	                                "Page chargée",
	                                Toast.LENGTH_SHORT).show();
	 
	                /** Hiding Indeterminate Progress Bar in the title bar*/
	                 getActivity().setProgressBarIndeterminateVisibility(false);
	 
	            }
	 
	        });
		
		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{

		if(item.getTitle().toString().contentEquals(((ActionBarActivity) getActivity()).getSupportActionBar().getTitle()))
		{
			//clic sur l'icone de l'appli donc retour activity précédente
			getActivity().finish();
		}
		return false;
	}
	
	
}
