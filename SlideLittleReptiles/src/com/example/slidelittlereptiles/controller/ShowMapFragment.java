package com.example.slidelittlereptiles.controller;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.R.id;
import com.example.slidelittlereptiles.R.layout;
import com.example.slidelittlereptiles.R.menu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapFragment extends Fragment 
{

	
	 TextView mSearchText;
	private SupportMapFragment fragment;
	private GoogleMap map;
	 String tag = "ShowMapFrag" ;
	 MarkerOptions markerOptions;
	
	 

		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{		
		return inflater.inflate(R.layout.showmapview_layout, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		FragmentManager fm = getChildFragmentManager();
		fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);		
		if (fragment == null) 
		{
			
			fragment = SupportMapFragment.newInstance();
			fm.beginTransaction().add(R.id.map, fragment).commit();
		}
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		if (map == null) 
		{
			map = fragment.getMap();
			map.setMyLocationEnabled(true);
			
        	LocationManager locationManager = (LocationManager)getActivity().getApplicationContext().getSystemService( Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            Location location = locationManager.getLastKnownLocation(provider);

            double lat= location.getLatitude();
            double lng = location.getLongitude();

            LatLng ll = new LatLng(lat, lng);

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 10));
		}

	}
	
	 @Override
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
	 {
		 
	        
	     inflater.inflate(R.menu.map_menu, menu);
	   
	   //  SearchManager searchManager =(SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
	      final SearchView searchView =(SearchView) menu.findItem(R.id.action_search).getActionView();
	      searchView.setQueryHint("Search The Map");
	      searchView.setOnQueryTextListener(new OnQueryTextListener() 
	      {

	          @Override
	          public boolean onQueryTextSubmit(String query) 
	          {	             
				Log.v(tag, "query=" + query);
				new GeocoderTask().execute(query);
				searchView.clearFocus();
	              return false;
	          }

			@Override
			public boolean onQueryTextChange(String arg0) 
			{
				// TODO Auto-generated method stub
				return false;
			}
	      });
	    //  searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	    
	      super.onCreateOptionsMenu(menu, inflater);
   
	 }
	 
	
	 
	 @Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{
			switch (item.getItemId()) 
			{
			    case R.id.action_search:
			    	
			    	
			    break;        
			}
			return false;
		}

	 
	 private class GeocoderTask extends AsyncTask<String, Void, List<Address>>{
		 
	        @Override
	        protected List<Address> doInBackground(String... locationName) {
	            // Creating an instance of Geocoder class
	            Geocoder geocoder = new Geocoder(getActivity().getBaseContext());
	            List<Address> addresses = null;
	 
	            try {
	                // Getting a maximum of 3 Address that matches the input text
	                addresses = geocoder.getFromLocationName(locationName[0], 3);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            return addresses;
	        }
	 
	        @Override
	        protected void onPostExecute(List<Address> addresses) {
	 
	            if(addresses==null || addresses.size()==0){
	                Toast.makeText(getActivity().getBaseContext(), "No Location found", Toast.LENGTH_SHORT).show();
	            }
	 
	            // Clears all the existing markers on the map
	            map.clear();
	 
	            // Adding Markers on Google Map for each matching address
	            for(int i=0;i<addresses.size();i++)
	            {
	 
	                Address address = (Address) addresses.get(i);
	 
	                // Creating an instance of GeoPoint, to display in Google Map
	                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
	 
	                String addressText = String.format("%s, %s",
	                address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                address.getCountryName());
	 
	                markerOptions = new MarkerOptions();
	                markerOptions.position(latLng);
	                markerOptions.title(addressText);
	 
	                map.addMarker(markerOptions);
	 
	                // Locate the first location
	                if(i==0)
	                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
	            }
	        }
	    }
	
	
}