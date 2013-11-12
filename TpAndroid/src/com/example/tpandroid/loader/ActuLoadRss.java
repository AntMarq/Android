package com.example.tpandroid.loader;

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

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.tpandroid.GlobalApplication;
import com.example.tpandroid.model.ObjActuRss;

public class ActuLoadRss extends ArrayList<ObjActuRss>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ns = null;
	private Handler				handler;
	private Context context;
	private ObjActuRss objRss;
	private GetRss loading ;
	private String tag = "ActuLoadRSS";
	private GlobalApplication application;
	
	
	
	public ActuLoadRss (Context context)
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
		application = (GlobalApplication)context.getApplicationContext ();		
		if(application.isOnline(context))
		{
			this.clear();
			
			loading = new GetRss ();
			loading .execute ();
		}
		else
		{
			Toast.makeText (application.getBaseContext(), "Réseau non disponible", 3).show ();
		}		
	}
	
	private class GetRss extends AsyncTask<String, Integer, String>
	{
		
		@Override
	    protected void onPreExecute() 
		{
			super.onPreExecute();
		
	    }
		
		@Override
		protected String doInBackground(String... params) 
		{
			try
			{			
				
				HttpClient httpClient = new DefaultHttpClient ();
				HttpPost postRequest = new HttpPost ("http://www.lachainemeteo.com/meteo-rss/toute-l-actualite-meteo.xml?xtdate=20131026");
				HttpResponse response = httpClient.execute (postRequest);
			
				BufferedReader reader = new BufferedReader (new InputStreamReader (response.getEntity ().getContent (),
						"utf8"));
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
//Create new message for the handler
			Message msg = new Message ();
			if (result != null)
			{
			
				try 
				{
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setInput(new StringReader(result));
					
										
					 int eventType = xpp.getEventType();
					 ObjActuRss newObject = null;

				    while (eventType != XmlPullParser.END_DOCUMENT)
				    {
				    	
			            String name = null;
			            switch (eventType)
			            {
			                case XmlPullParser.START_DOCUMENT:
			                    break;
			                case XmlPullParser.START_TAG:
			                    name = xpp.getName();		                    
			            		                    
			                    if (name.equals("item"))
			                    {			                    	
			                    	newObject = new ObjActuRss();          				                    	
			                    } 
			                    else if (newObject != null)
			                    {
			                    	
			                    	if (name.equals("title"))
				                    {				  			                    						                    		
				                    		
			                    		newObject.title = xpp.nextText(); 
			                    					         		          
        
				                    }
				                    else if (name.equals("description"))
				                    {				                        	
				                		newObject.description = xpp.nextText(); 
				            
				                    }
				                    else if (name.equals("pubDate"))
				                    {		
				                    	newObject.pubDate = newObject.ActudateString(xpp.nextText());

				                		

				                    }

			                        else if (name.equals("link"))
			                        {
			                        	
			                        	newObject.link = xpp.nextText();
			                        	
			                        }
			                        else if (name.equals("enclosure"))
			                        {
			                        	newObject.setUrlimage(xpp.getAttributeValue(null, "url")); 
			                        	
			 
			                        }   
			                    }
			                    break;
			                	case XmlPullParser.END_TAG:
			                	
			                    name = xpp.getName();
			                    
			                    if (name.equalsIgnoreCase("item") && newObject != null)
			                    {
			                    	
			                    	ActuLoadRss.this.add(newObject);
			                    	
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
				Log.e (tag, "Une erreur est survenue pendant la recuperation du flux RSS");
				msg.arg1 = 0;
			}	
//Send the message to the handler
			getHandler ().sendMessage (msg);
		}
		
	}	
}
