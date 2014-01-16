package com.example.geochild;




import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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
//	private TouchImageView imageViewTouch ;
	private Bitmap bitmap;
	private float scaleX,scaleY ;
	private Canvas canvas;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private float hauteurBarAction;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState) 
	{	    
		
		View view = inflater.inflate(R.layout.mainfragment, parent, false);
	    application = (GlobalMethods) getActivity().getApplicationContext();
	    imageViewTouch = (ImageViewTouch) view.findViewById(R.id.image);
	    imageViewTouch.setOnTouchListener(this);
	//    imageViewTouch.setMaxZoom(10.0f);
	//    imageViewTouch.setMinZoom(1.0f);
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
	        public boolean onLongClick(View v) 
	        {
	        	getActivity().runOnUiThread (new Runnable ()
				{
		
					@Override
					public void run ()
					{

						hauteurBarAction = getActivity().getActionBar ().getHeight ();
						 Log.v("MapFragment", "hauteurBarAction" + hauteurBarAction );
						
						 	float x = application.dimScreen()[1];
		                    Log.v("MapFragment", "x = " + x);
		                    float y = application.dimScreen()[0];
		                    Log.v("MapFragment", "y = " + y);
		                 //   Drawable d = getResources().getDrawable(R.drawable.mini_map_marker);
		                  //  scaleX = d.getIntrinsicWidth()/canvas.getWidth();
		                  //  scaleY = d.getIntrinsicHeight()/canvas.getHeight();
		                    paint.setARGB(255,0,255,20);
		                    paint.setStyle(Style.FILL);
		            	   
		                    canvas = new Canvas(mutableBitmap);
		                    Bitmap newIconmap = BitmapFactory.decodeResource(getResources(),R.drawable.map_marker);
		        	        canvas.drawBitmap(newIconmap,500, 250, paint);
		        	        imageViewTouch.invalidate();
		        	      
		        	       
					}
				});
	        	 return true;
	          
	        }
	    });
	  /*  imageViewTouch.setSingleTapListener(new OnImageViewTouchSingleTapListener()
	    {
            
            @Override
            public void onSingleTapConfirmed() 
            {
                    Log.v("MapFragment", "onSingleTapConfirmed" );
                    
                   
            }
	    });*/
	  
	    return view ;
	
	}

	

	@Override
	public boolean onTouch(View arg0,  MotionEvent event) 
	{
		int heightTouch = (int) event.getX();
		int widthTouch = (int) event.getY();
		Log.v(tag, "heightTouch" + heightTouch);
		Log.v(tag, "widthTouch" + widthTouch);
		return false;
	}
	

	
}
