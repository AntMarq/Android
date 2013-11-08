package com.example.slidelittlereptiles.view;

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

import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.loader.PharmaLoadRSS;
import com.example.slidelittlereptiles.model.ObjPharmRss;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;



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
		public View getView (int position, View convertView, ViewGroup parent)
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



			final ObjPharmRss objrept = ((ObjPharmRss)(listRSS.get(position)));
			holder.rssTitleView.setText  (Html.fromHtml((objrept.getTitle ().toString())));
			holder.description.setText (Html.fromHtml((objrept.getDescription().toString())));
			
			if ((objrept.getUrlimage () != null)  && objrept.getImageRept ()==null)
			{
				
				DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true) // default
		        .cacheOnDisc(true)
		        .build();
				imageLoader.displayImage(objrept.getUrlimage(), holder.imagePharma,options);
				
			}
			else if(objrept.getImageRept ()!=null)
			{
				
				holder.imagePharma.setImageBitmap (objrept.getImageRept  ());
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