package com.example.slidelittlereptiles.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.graphics.Bitmap;
import android.util.Log;


public class ObjActuRss implements Comparable<Object>{


 	public String title;
 	public String link;
 	public String description;	  
 	public String pubDate;
 	public Bitmap image;
 	public String urlimage;
 	
    
   

	public String getUrlimage() {
		return urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	public ObjActuRss() 
    {
		super();
    }
	
	public Date ActuDate (String string)
	{
		String pattern = "EEE, d MMM yyyy HH:mm:ss Z" ;
		
		/* Parsing String -> Date */ 
		try 
		{ 
		  Date dateActu = (new SimpleDateFormat( pattern, Locale.US  )).parse( string ) ;
	//	  Log.v("ObjeActuRss", "dateformat" + dateActu );
		  return dateActu;
		} 
		catch ( ParseException ex ) 
		{ 
		    System.err.println( ex.getMessage() ) ; 
		    return null;
		}		
	}
	
	public String ActudateString (String originalDate)
	{
		String newPattern = "EEE d/MM/yyyy HH:mm:ss" ;
		String newDateString = new SimpleDateFormat( newPattern).format( ActuDate (originalDate) ) ; 
		return newDateString;
	}
	
	 @Override
	 public int compareTo(Object another) 
	 {
		 
	  Date date1 = ((ObjActuRss) another).ActuDate(((ObjActuRss) another).getPubDate()); 
	  Date date2 = this.ActuDate(this.pubDate);
	  
	    if (date2.compareTo(date1)<0)  return -1; 
	    else if(date2.compareTo(date1)==0) return 0; 
	    else return 1;
	 }

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ObjActuRss [title=" + title + ", link=" + link
				+ ", description=" + description + ", pubDate=" + pubDate
				+ ", image=" + image + ", urlimage=" + urlimage + "]";
	}

	


	
	

}
