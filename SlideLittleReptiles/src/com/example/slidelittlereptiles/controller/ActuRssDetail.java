package com.example.slidelittlereptiles.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.example.slidelittlereptiles.GlobalVar;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.model.ObjActuRss;

public class ActuRssDetail extends ActionBarActivity 
{

	private static final String	tag	= "RssDetail";
	GlobalVar application;
	ObjActuRss objectRss;
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.acturssdetail);
		application = (GlobalVar) getApplicationContext ();
	
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent getAbTitle= getIntent();
		Bundle b = getAbTitle.getExtras();
		if(b!=null)
	    {
	        String j =(String) b.get("title");
	        getSupportActionBar().setTitle(j);
	    }	
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);		
	}
}