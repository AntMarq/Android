package com.example.geochild;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class MapFragment extends Fragment implements SurfaceHolder.Callback,  OnTouchListener
{
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	String tag = "MapFragment";
	private Context context;
	private GlobalMethods application;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState) 
	{
	    
	    View view = inflater.inflate(R.layout.mainfragment, parent, false);   
	    application = (GlobalMethods) getActivity().getApplicationContext();
	    mSurfaceView = (SurfaceView)view.findViewById(R.id.map_surfaceView);
	    mSurfaceHolder = mSurfaceView.getHolder();
	    mSurfaceHolder.addCallback(this);
	    mSurfaceView.setWillNotDraw(false);
	    mSurfaceView.setBackgroundResource(R.drawable.france_map);
	    
	    mSurfaceView.setOnClickListener(new View.OnClickListener() 
	    {		
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
				Toast.makeText(application, "Cooooool",
					Toast.LENGTH_SHORT).show();
			}
	});
	   
	    return view ;
	
	}
	
	
    protected void onDrawCanvas(Canvas canvas) 
    {
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.map_marker);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(icon, 10, 10, new Paint());        
    }

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		Canvas canvas = null;
        try {
            canvas = holder.lockCanvas(null);
            synchronized (holder) {
            	onDrawCanvas(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                holder.unlockCanvasAndPost(canvas);
            }
        }
		
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) 
	{
		
		Log.v(tag, "onTouch");
		return false;
	}
}
