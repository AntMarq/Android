package com.example.geochild;




import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class MapFragment extends Fragment implements  OnTouchListener
{
	
	String tag = "MapFragment";
	private Context context;
	private GlobalMethods application;
	int myChildPosition ;
	private Bitmap mBitmap,mutableBitmap,workingBitmap;
	private ImageViewTouch imageViewTouch ;
	
	private Bitmap bitmap;
	private float scaleX,scaleY ;
	private Canvas canvas;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private float hauteurBarAction;
	private int [] coordinateL = new int [2];
	int heightTouch;
	int widthTouch ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState) 
	{	    
		
		View view = inflater.inflate(R.layout.mainfragment, parent, false);
	    application = (GlobalMethods) getActivity().getApplicationContext();
	    imageViewTouch = (ImageViewTouch) view.findViewById(R.id.image);
	    imageViewTouch.setOnTouchListener(this);
	    
	    Bundle bundle=getArguments(); 
		myChildPosition =bundle.getInt("position");  
		
		BitmapFactory.Options myOptions = new BitmapFactory.Options();
	    myOptions.inDither = true;
	    myOptions.inScaled = false;
	    myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
	    myOptions.inPurgeable = true;
	   
	    if(myChildPosition == 0)
	    {
	    	 bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.allemagne_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    	 
	    	
	    	
	    }
	    else if(myChildPosition == 1)
	    {
	    	 bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.angleterre_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    else if(myChildPosition ==  2)
	    {
	    	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.espagne_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    else if (myChildPosition ==  3)
	    {
	    	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.france_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    else if(myChildPosition ==  4)
	    {   	
	    	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.italie_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    else if(myChildPosition ==  5)
	    {
	    	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.portugal_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    else if(myChildPosition ==  6)
	    {
	    	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.russie_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    else if(myChildPosition ==  7)
	    {
	    	bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.suisse_map,myOptions);
	    	 workingBitmap =  Bitmap.createBitmap(bitmap);
			 mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
	    	 imageViewTouch.setImageBitmap(mutableBitmap);
	    }
	    imageViewTouch.setOnLongClickListener(new OnLongClickListener() {

	        @Override
	        public boolean onLongClick(final View v) 
	        {
	        	getActivity().runOnUiThread (new Runnable ()
				{
		
					@Override
					public void run ()
					{
						
						 canvas = new Canvas(mutableBitmap);
						
					//	hauteurBarAction = getActivity().getActionBar ().getHeight ();
					//	 Log.v("MapFragment", "hauteurBarAction" + hauteurBarAction );
						 
		                    coordinateL[0] = widthTouch;
		                    coordinateL[1] = heightTouch;
		                 /*   Drawable d = new BitmapDrawable(getResources(),mutableBitmap);
		                   
		                    scaleX = d.getIntrinsicWidth()/canvas.getWidth();    
		                    scaleY = d.getIntrinsicHeight()/canvas.getHeight(); */
		                    
		                    paint.setARGB(255,0,255,20);
		                    paint.setStyle(Style.FILL);
		                    
		                   Log.v(tag, "taille de la vue H " + v.getHeight() + " taille de la vue W " + v.getWidth()) ;

		                    int calculmapH = mutableBitmap.getHeight();		                   
		                    Log.v(tag, "calculmapH" + calculmapH);
		                  
		                    int calculmapW = mutableBitmap.getWidth();
		                    Log.v(tag, "calculmapW" + calculmapW);
		                    
		                    float screenDimH = (application.dimScreen()[0] ) ;
		                    Log.v(tag, "screenDimH" + screenDimH);
		                    float screenDimW = application.dimScreen()[1];
		                    Log.v(tag, "screenDimW" + screenDimW);
		                    
		                    float ratioH = calculmapH/screenDimH ;
		                    Log.v(tag, "ratioH" + ratioH);
		                    float ratioW = calculmapW/screenDimW ;
		                   
		                    Log.v(tag, "SCALE  " + imageViewTouch.getScale()) ; 
		                    
		                    canvas.drawCircle(((widthTouch)*ratioW), ((heightTouch)*ratioH), 10, paint);
		        	        imageViewTouch.invalidate(); 
					}
				});
	        	 return true;	          
	        }
	    });
	  
	    return view ;
	
	}

	

	@Override
	public boolean onTouch(View v,  MotionEvent event) 
	{
		widthTouch = (int) event.getX();
		heightTouch = (int) event.getY();
		
		return false;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) 
	    {
	    	
	    } 
	    else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
	    {
	      
	    }
	}
	
}
