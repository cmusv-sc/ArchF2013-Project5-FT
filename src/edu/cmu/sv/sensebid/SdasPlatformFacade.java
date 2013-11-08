package edu.cmu.sv.sensebid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import android.os.AsyncTask;
import android.util.Log;

public class SdasPlatformFacade extends AsyncTask<String, String, String> {
		
	private String baseUrl,format;
	
	SdasPlatformFacade()
	{
	}
	
	public void publishData(JSONObject[] sensorReading)
	{
		this.postRequest(sensorReading);
	}
		
	void setFormat(String format)
	{
		this.format = format;
	}
		
	void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}
		
	private void postRequest(JSONObject[] jsonArray)
	{
		int TIMEOUT_MILLISEC = 10000;  // = 10 seconds
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
		HttpClient client = new DefaultHttpClient(httpParams);
		HttpPost request = new HttpPost("URL");
		
		//We need to convert JSONObject array to ByteArrayEntity
		//request.setEntity(new ByteArrayEntity());
		    //postMessage.toString().getBytes("UTF8")));
		//HttpResponse response = client.execute(request);
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}


