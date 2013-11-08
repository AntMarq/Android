package com.example.slidelittlereptiles;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.slidelittlereptiles.controller.ActuListFragment;
import com.example.slidelittlereptiles.controller.PharmListFragment;
import com.example.slidelittlereptiles.loader.PharmaLoadRSS;
import com.example.slidelittlereptiles.model.ObjActuRss;
import com.example.slidelittlereptiles.model.ObjPharmRss;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GlobalVar extends Application
{

	private PharmaLoadRSS loadRSS;
	private ObjPharmRss objPharmaRss;
	private ObjActuRss objActuRss;
	private PharmListFragment rssListFragment;
	private ActuListFragment actuListFragment;




	@Override
	  public void onCreate() 
	{
	        super.onCreate();

	        // Create global configuration and initialize ImageLoader with this configuration
	        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
	        
	        .build();
	        
	        ImageLoader.getInstance().init(config);
	    }
	
	public  boolean isOnline (Context currentActivity)
	{
		ConnectivityManager cm = (ConnectivityManager) currentActivity.getSystemService (Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo ();
		if (netInfo != null && netInfo.isConnected ())
		{
			return true;
		}
		return false;
	}

	public PharmaLoadRSS getLoadRSS() {
		return loadRSS;
	}

	public void setLoadRSS(PharmaLoadRSS loadRSS) {
		this.loadRSS = loadRSS;
	}

	public ActuListFragment getActuListFragment() {
		return actuListFragment;
	}

	public void setActuListFragment(ActuListFragment actuListFragment) {
		this.actuListFragment = actuListFragment;
	}
	
	
	 public PharmListFragment getRssListFragment() {
		return rssListFragment;
	}

	public void setRssListFragment(PharmListFragment rssListFragment) {
		this.rssListFragment = rssListFragment;
	}

	public ObjPharmRss getObjPharmaRss() {
		return objPharmaRss;
	}

	public void setObjPharmaRss(ObjPharmRss objPharmaRss) {
		this.objPharmaRss = objPharmaRss;
	}

	public ObjActuRss getObjActuRss() {
		return objActuRss;
	}

	public void setObjActuRss(ObjActuRss objActuRss) {
		this.objActuRss = objActuRss;
	}
	
	
	
}
