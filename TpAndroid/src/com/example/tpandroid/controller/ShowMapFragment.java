package com.example.tpandroid.controller;

import java.io.IOException;
import java.util.List;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tpandroid.GlobalApplication;
import com.example.tpandroid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapFragment extends Fragment implements LocationListener,OnMarkerClickListener
{

	private SupportMapFragment fragment;
	private GoogleMap map;
	private String tag = "ShowMapFrag" ;
	private MarkerOptions markerOptions;
	private LocationManager locationManager ;
	private Location currentLocation = null;
	private Marker marker;

	
	
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{		
		return inflater.inflate(R.layout.showmapview_layout, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
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
//Check map and Zoom in currentlocation if exist
            if (map == null) 
            {
                    map = fragment.getMap();
                    map.setMyLocationEnabled(true);
                
                    locationManager = (LocationManager)getActivity().getApplicationContext().getSystemService( Context.LOCATION_SERVICE);
		     		
			        Criteria criteria = new Criteria();			
			        String provider = locationManager.getBestProvider(criteria, true);
			       
			        currentLocation = locationManager.getLastKnownLocation(provider);
			      
			       
		        if(currentLocation != null)
		        {
		        	double lat= currentLocation.getLatitude();
		            double lng = currentLocation.getLongitude();

		            LatLng ll = new LatLng(lat, lng);

		            map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 10));
		        	
		        }
		        else
		        {
		        	   locationManager.requestLocationUpdates(provider, 10000, 0, this); 
		        }
		       
            }
            map.setOnMarkerClickListener((OnMarkerClickListener) this);
   
    }
	
	
	 @Override
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
	 {
		 
	     inflater.inflate(R.menu.map_menu, menu);
	     MenuItem searchItem = menu.findItem(R.id.action_search);
	     final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);      	
	    	 searchView.setQueryHint("Saisir votre adresse");
		      searchView.setOnQueryTextListener(new OnQueryTextListener() 
		      {

		          @Override
		          public boolean onQueryTextSubmit(String query) 
		          {	             
					new GeocoderTask().execute(query);
					searchView.clearFocus();
		            return false;
		          }

				@Override
				public boolean onQueryTextChange(String arg0) 
				{
					return false;
				}
		      });
		      
	      super.onCreateOptionsMenu(menu, inflater);
   
	 }
	 
	 
	
	 
	 @Override
		public boolean onOptionsItemSelected(MenuItem item) 
		{			
		 
			return false;
		}
 
	 private class GeocoderTask extends AsyncTask<String, Void, List<Address>>
	 {
		 
	        @Override
	        protected List<Address> doInBackground(String... locationName) {
	            // Creating an instance of Geocoder class
	            Geocoder geocoder = new Geocoder(getActivity().getBaseContext());
	            List<Address> addresses = null;
	 
	            try {
	                // Getting a maximum of 3 Address that matches the input text
	                addresses = geocoder.getFromLocationName(locationName[0], 3);
	            } 
	            catch (IOException e) 
	            {
	            	// Toast.makeText(getActivity().getBaseContext(), "Service not available", Toast.LENGTH_SHORT).show();
	                e.printStackTrace();
	               
	            }
	            return addresses;
	        }	
	 
	        @Override
	        protected void onPostExecute(List<Address> addresses) 
	        {
	            if(addresses==null || addresses.size()==0){
	                Toast.makeText(getActivity().getBaseContext(), "Aucune adresse trouvée", Toast.LENGTH_SHORT).show();
	            }
	            // Clears all markers on the map
	            map.clear();
	 
	            // Adding Markers on Google Map for each matching address
	            for(int i=0;i<addresses.size();i++)
	            {
	 
	                Address address = (Address) addresses.get(i);
	 
	                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
	 
	                String addressText = String.format("%s, %s",
	                address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                address.getCountryName());

	                markerOptions = new MarkerOptions();
	                markerOptions.position(latLng);
	                markerOptions.title(addressText);
	        //        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_navigation));
	               
	                marker = map.addMarker(markerOptions);
	               
	                // Locate the first location
	                if(i==0)
	                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
	                
	            }
	           
	        }
	}

	
	
	  

	@Override
	public void onLocationChanged(Location location) 
	{
		 //Move the camera to the user's location once it's available!
		currentLocation = location;
		  map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 10));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) 
	{
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public boolean onMarkerClick(final Marker marker) 
	{
		//GlobalApplication application = (GlobalApplication)getActivity().getApplicationContext ();	
		Log.v(tag, "onMarkerClick");
		if(this.marker.equals(marker))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle("Google Navigation");
			builder.setMessage("Voulez vous naviguer à cette adresse ?");
			builder.setCancelable(true);
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                	  Log.v(tag, "onClick");
                	  dialog.cancel();
                	  
                  }
              });
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

		        @Override
		        public void onClick(DialogInterface dialog, int id) 
		        {
		        	String uri = "http://maps.google.com/maps?saddr="+currentLocation.getLatitude() +"," + currentLocation.getLongitude() +"&daddr="+markerOptions.getPosition().latitude+","+markerOptions.getPosition().longitude;
		        	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
		        	intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		        	startActivity(intent);
		            }
		      });
			builder.show();
		}
		 
		return false;
	}

	


	
	
}