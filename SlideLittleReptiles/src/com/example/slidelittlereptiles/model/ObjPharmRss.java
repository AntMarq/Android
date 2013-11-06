package com.example.slidelittlereptiles.model;

import android.graphics.Bitmap;

public class ObjPharmRss 
{

	 	public String title;
	 	public String link;
	 	public String description;	  
	 	public String pubDate;
	 	public Bitmap imageRept;
	 	public String urlimage;
	    
	   

		public String getUrlimage() {
			return urlimage;
		}

		public void setUrlimage(String urlimage) {
			this.urlimage = urlimage;
		}

		public ObjPharmRss() 
	    {
			super();
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

		public Bitmap getImageRept() {
			return imageRept;
		}

		public void setImageRept(Bitmap imageRept) {
			this.imageRept = imageRept;
		}


		
		

}
