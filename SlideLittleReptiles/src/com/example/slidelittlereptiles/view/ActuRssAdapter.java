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

import com.example.slidelittlereptiles.ActuLoadRss;
import com.example.slidelittlereptiles.R;
import com.example.slidelittlereptiles.R.id;
import com.example.slidelittlereptiles.R.layout;
import com.example.slidelittlereptiles.model.ObjActuRss;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ActuRssAdapter extends BaseAdapter
{
	private Context 			 mContext;
    private ArrayList<ObjActuRss>listRSS;
    private 				LayoutInflater 		 inflater;
	//private 				ImageThreadLoader	 imageLoader = new ImageThreadLoader ();
	private String tag = "ActuRssAdapter" ;
	private ImageLoader imageLoader = ImageLoader.getInstance();
    
    public ActuRssAdapter(Context context, ActuLoadRss loadRSS) 
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
				
				 convertView = inflater.inflate(R.layout.actu_rss_cell, null);
				
				holder.rssTitleView = (TextView) convertView.findViewById (R.id.titre);
				holder.description = (TextView) convertView.findViewById (R.id.excerpt);
				holder.image = (ImageView) convertView.findViewById (R.id.image);
				holder.pubDate = (TextView) convertView.findViewById (R.id.pubdate);
						
				convertView.setTag(holder);
		}						
		else 
		{
			 
		
			holder = (ViewHolder)convertView.getTag();				
		
		}
		
		 // Do it on Application start	
		
		holder.image.setImageBitmap(listRSS.get(position).getImage());
		
		
		final ViewHolder finalHolder = holder;
		Bitmap cachedImage = null;

		final ObjActuRss objActu = ((ObjActuRss)(listRSS.get(position)));
		   holder.rssTitleView.setText  (Html.fromHtml((objActu.getTitle ().toString())));
		   holder.description.setText (Html.fromHtml((objActu.getDescription().toString())));
		   holder.pubDate.setText(listRSS.get(position).ActudateString());
		
		if ((objActu.getUrlimage () != null)  && objActu.getImage ()==null)
		{
			
			DisplayImageOptions options = new DisplayImageOptions.Builder()
			.cacheInMemory(true) // default
	        .cacheOnDisc(true)
	        .build();
			imageLoader.displayImage(objActu.getUrlimage(), holder.image,options);
			
		}
		else if(objActu.getImage ()!=null)
		{
			
			holder.image.setImageBitmap (objActu.getImage  ());
		}
					
	return  convertView;			
  }


private class ViewHolder 
{
    public TextView rssTitleView;
    public ImageView image ;
    public TextView	description;	
    public TextView pubDate;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
