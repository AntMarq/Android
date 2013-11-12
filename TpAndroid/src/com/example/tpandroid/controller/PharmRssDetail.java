package com.example.tpandroid.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;

import com.example.tpandroid.GlobalApplication;
import com.example.tpandroid.R;
import com.example.tpandroid.model.ObjPharmRss;

public class PharmRssDetail extends ActionBarActivity 
{
	
	private static final String	tag	= "RssDetail";
	GlobalApplication application;
	ObjPharmRss objectRss;
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView (R.layout.pharmarssdetail);
		application = (GlobalApplication) getApplicationContext ();
		
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
