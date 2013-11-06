package com.example.slidelittlereptiles.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slidelittlereptiles.PharmaLoadRSS;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.R.id;
import com.example.slidelittlereptiles.R.layout;
import com.example.slidelittlereptiles.model.ObjPharmRss;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;



public class PharmRssAdapter extends BaseAdapter
{
		private Context 			 mContext;
        private ArrayList<ObjPharmRss>listRSS;
        private 				LayoutInflater 		 inflater;
    	//private 				ImageThreadLoader	 imageLoader = new ImageThreadLoader ();
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
			
			
			
			if (convertView == null)
			{
					holder = new ViewHolder();
			//		 LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			
			 // Do it on Application start	
			
			holder.imagePharma.setImageBitmap(listRSS.get(position).getImageRept());
			
			
			final ViewHolder finalHolder = holder;
			Bitmap cachedImage = null;

			final ObjPharmRss objrept = ((ObjPharmRss)(listRSS.get(position)));
			 holder.rssTitleView.setText  (Html.fromHtml((objrept.getTitle ().toString())));
			   holder.description.setText (Html.fromHtml((objrept.getDescription().toString())));
			
			if ((objrept.getUrlimage () != null)  && objrept.getImageRept ()==null)
			{
				/*try
				{					
					cachedImage = imageLoader.loadImage (objrept.getUrlimage (), new ImageLoadedListener ()
					{
						@Override
						public void imageLoaded (Bitmap imageBitmap)
						{			
							Log.v(tag, "imageLoaded");
							objrept.setImageRept (imageBitmap);
							finalHolder.imageRept.setImageBitmap (objrept.getImageRept ());	
						//	notifyDataSetChanged();
						}
					});
				}
				catch (MalformedURLException e)
				{					
					Log.e (tag, "Bad remote image URL: " + objrept.getUrlimage (), e);
				}
				if (cachedImage != null)
				{
					Log.v(tag, "cachedImage");
					holder.imageRept.setImageBitmap (cachedImage);
				}
				*/
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