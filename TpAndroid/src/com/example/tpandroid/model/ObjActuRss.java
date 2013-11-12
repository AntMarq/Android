package com.example.tpandroid.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.graphics.Bitmap;


public class ObjActuRss 
{


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
	//	Date to String 
            String pattern = "EEE, d MMM yyyy HH:mm:ss Z" ;                    
            try
            {
             Date dateActu = (new SimpleDateFormat( pattern, Locale.US )).parse( string ) ;
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
    	 //Parse format 
            String newPattern = "EEE d/MM/yyyy HH:mm:ss" ;
            String newDateString = new SimpleDateFormat( newPattern).format( ActuDate (originalDate) ) ;
            return newDateString;
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
