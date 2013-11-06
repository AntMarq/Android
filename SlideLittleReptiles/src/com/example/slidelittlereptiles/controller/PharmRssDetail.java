package com.example.slidelittlereptiles.controller;

import com.example.slidelittlereptiles.GlobalVar;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.R.layout;
import com.example.slidelittlereptiles.model.ObjPharmRss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.util.Log;

public class PharmRssDetail extends FragmentActivity 
{
	
	private static final String	tag	= "RssDetail";
	GlobalVar application;
	ObjPharmRss objectRss;
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.pharmarssdetail);
		application = (GlobalVar) getApplicationContext ();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent getAbTitle= getIntent();
		Bundle b = getAbTitle.getExtras();
		if(b!=null)
	    {
	        String j =(String) b.get("title");
	    	getActionBar().setTitle(j);
	    }	
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);		
	}
}
