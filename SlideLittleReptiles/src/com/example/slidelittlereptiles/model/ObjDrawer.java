package com.example.slidelittlereptiles.model;

public class ObjDrawer 
{


private int icon;
private String title;


	public ObjDrawer() 
	{
	    super ();
	}
	
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "ObjDrawer [icon=" + icon + ", title=" + title + "]";
	}
	
	
	
}