package com.example.slidelittlereptiles.controller;

import com.example.slidelittlereptiles.GlobalVar;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.R.id;
import com.example.slidelittlereptiles.R.layout;
import com.example.slidelittlereptiles.model.ObjPharmRss;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class PharmRssDetailFragment extends Fragment
{
	
	GlobalVar application;
	

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate (R.layout.pharmarssdetailfrag, container, false);

		application = (GlobalVar) getActivity ().getApplicationContext ();

		WebView viewLong = (WebView) view.findViewById (R.id.webviewpharmarss);

		setHasOptionsMenu(true);
		
		final ObjPharmRss newReptiles = application.getObjPharmaRss ();

		viewLong.loadUrl(newReptiles.getLink());
		
		return view;
	}
	
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{

		if(item.getTitle().toString().contentEquals(getActivity().getActionBar().getTitle()))
		{
			//clic sur l'icone de l'appli donc retour activity précédente
			getActivity().finish();
		}
		return false;
	}
	
	
}
