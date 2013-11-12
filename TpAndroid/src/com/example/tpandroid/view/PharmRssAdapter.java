package com.example.tpandroid.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tpandroid.R;
import com.example.tpandroid.loader.PharmaLoadRSS;
import com.example.tpandroid.model.ObjPharmRss;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;



public class PharmRssAdapter extends BaseAdapter
{
		private Context 			 mContext;
        private ArrayList<ObjPharmRss>listRSS;
        private 				LayoutInflater 		 inflater;
		private String tag = "RssAdapter" ;
		private ImageLoader imageLoader = ImageLoader.getInstance();
        
        public PharmRssAdapter(Context context, PharmaLoadRSS loadRSS) 
        {
        	mContext = context;	
            this.listRSS = loadRSS;
            inflater = LayoutInflater.from(context);
        }
        
        @Override
		public Object getItem (int position)
		{
        	
			return listRSS.get (position);
		}

		@Override
		public long getItemId (int position)
		{
			return position;
		}

		@Override
		public int getCount ()
		{
			
			return listRSS.size ();
		}

		@Override
		public View getView (final int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			
			Log.v(tag, "getView");
			
			if (convertView == null)
			{
					holder = new ViewHolder();
					convertView = inflater.inflate(R.layout.pharm_rss_cell, null);
					
					holder.rssTitleView = (TextView) convertView.findViewById (R.id.titrereptiles);
					holder.description = (TextView) convertView.findViewById (R.id.excerptreptiles);
					holder.imagePharma = (ImageView) convertView.findViewById (R.id.imagereptiles);
							
					convertView.setTag(holder);
		}						
		else 
		{
			 
		
			holder = (ViewHolder)convertView.getTag();				
		
		}

			

			final ObjPharmRss objPharma = ((ObjPharmRss)(listRSS.get(position)));
			holder.rssTitleView.setText  (Html.fromHtml((objPharma.getTitle ().toString())));
			holder.description.setText (Html.fromHtml((objPharma.getDescription().toString())));
			holder.imagePharma.setImageBitmap(objPharma.getImagePharma());
			
			final ViewHolder finalHolder = holder;
			if ((objPharma.getUrlimage () != null)  && objPharma.getImagePharma ()==null)
			{
				
				DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true) // default
		        .cacheOnDisc(true)
		        .build();
				imageLoader.displayImage(objPharma.getUrlimage(), holder.imagePharma,options,new ImageLoadingListener() 
				{
				    @Override
				    public void onLoadingStarted(String imageUri, View view) 
				    {
				       
				    }
				    @Override
				    public void onLoadingFailed(String imageUri, View view, FailReason failReason) 
				    {
				    	finalHolder.imagePharma.setImageResource(R.drawable.icon_lyon);
				    }
				    @Override
				    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) 
				    {
				    	
				    }
				    @Override
				    public void onLoadingCancelled(String imageUri, View view) 
				    {
				        
				    }
				
				});
			}
			else if(objPharma.getImagePharma ()!=null)
			{
				
				holder.imagePharma.setImageBitmap (objPharma.getImagePharma  ());
			}
			else
			{
				holder.imagePharma.setImageResource(R.drawable.sante);
			}
						
		return  convertView;			
	  }
	
	
    private class ViewHolder 
    {
        public TextView rssTitleView;
        public ImageView imagePharma ;
        public TextView	description;	
    }
}