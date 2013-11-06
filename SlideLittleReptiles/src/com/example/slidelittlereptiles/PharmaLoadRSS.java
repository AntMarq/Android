package com.example.slidelittlereptiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.slidelittlereptiles.model.ObjPharmRss;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



public class PharmaLoadRSS extends ArrayList<ObjPharmRss>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ns = null;
	private Handler				handler;
	Context context;
	ObjPharmRss objRss;
	GetRss loading ;
	//RssFeedObject newObj;
	public String tag = "PharmaLoadRSS";
	
	
	
	public PharmaLoadRSS (Context context)
	{
		super ();
		this.context = context;	
	}
	
	public void setHandler (Handler handler)
	{
		this.handler = handler;
	}

	/**
	 * @return the handler
	 */
	public Handler getHandler ()
	{
		return handler;
	}
	
	public void refreshOnline ()
	{
		Log.v(tag, "refreshOnline" );
		if(PharmaLoadRSS.this.size() >= 0)
		{			
			PharmaLoadRSS.this.clear();
		}
		loading = new GetRss ();
		loading .execute ();
	}
	
	private class GetRss extends AsyncTask<String, Integer, String>
	{
		
		
		@Override
		protected String doInBackground(String... params) 
		{
			try
			{			
				
				HttpClient httpClient = new DefaultHttpClient ();
				HttpPost postRequest = new HttpPost ("http://fr.news.yahoo.com/rss/sante-publique");
				HttpResponse response = httpClient.execute (postRequest);
			
				BufferedReader reader = new BufferedReader (new InputStreamReader (response.getEntity ().getContent (),
						"UTF-8"));
				String sResponse;
				StringBuilder sb = new StringBuilder ();
				while ( (sResponse = reader.readLine ()) != null)
				{
					sb = sb.append (sResponse);
				}
				return sb.toString ();
			}
			catch (UnsupportedEncodingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			catch (ClientProtocolException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			return null;
		}

		
		@Override
		protected void onPostExecute (String result)
		{
			Message msg = new Message ();
			if (result != null)
			{
			//	Log.v(tag, "result = " + result);
				try 
				{
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setInput(new StringReader(result));
					
										
					 int eventType = xpp.getEventType();
					 ObjPharmRss newObject = null;
					 
					//ArrayList<RssFeedObject> objArrayRSS = null;
	
				    while (eventType != XmlPullParser.END_DOCUMENT)
				    {
				    	
			            String name = null;
			            switch (eventType)
			            {
			                case XmlPullParser.START_DOCUMENT:
			          //      	objArrayRSS = new ArrayList<RssFeedObject>();
			                    break;
			                case XmlPullParser.START_TAG:
			                    name = xpp.getName();		                    
			            		                    
			                    if (name.equals("item"))
			                    {			                    	
			                    	newObject = new ObjPharmRss();          				                    	
			                    } 
			                    else if (newObject != null)
			                    {
			                    	
			                        if (name.equals("title"))
			                        {
			                        	newObject.title= xpp.nextText();
			                        	
			                        } 
			                        else if (name.equals("description"))
			                        {
			                        	String needSplit = xpp.nextText();
			                        	String[] splitDescription = needSplit.trim().split("</a>");
			                        	for (String item : splitDescription)
			                            {
			                        		newObject.description = item;
			                            }
			                        	
			                        	
			                        } 
			                        else if (name.equals("link"))
			                        {
			                        	
			                        	newObject.link = xpp.nextText();
			                        	
			                        }
			                       /* else if (name.equals("enclosure"))
			                        {
			                        	newObject.setUrlimage(xpp.getAttributeValue(null, "url")); 
			                        	
			                        	Log.v(tag , "resultat enclosure" + newObject.getUrlimage());
			                        } 
			                        */
			                       
			                        else if (name.equalsIgnoreCase("content"))
			                        {
			                        	
			                        	newObject.setUrlimage(xpp.getAttributeValue(null, "url"));
			                        	
			                        } 
			                        
			                        
			                    }
			                    break;
			                	case XmlPullParser.END_TAG:
			                	
			                    name = xpp.getName();
			                    
			                    if (name.equalsIgnoreCase("item") && newObject != null)
			                    {
			                    	
			                    	PharmaLoadRSS.this.add(newObject);
			                    	
			                    } 
			                 
			            }
			            eventType = xpp.next();
			        }
				    msg.arg1 = 1;
				}
				 catch (XmlPullParserException e)
				 {
						e.printStackTrace();
				 }
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				Log.e (tag, "Une erreur est survenue");
				msg.arg1 = 0;
			}		
			getHandler ().sendMessage (msg);
		}
	}	
}	
