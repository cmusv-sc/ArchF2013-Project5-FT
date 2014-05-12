/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley.
All rights reserved.

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

Please contact http://www.cmu.edu/silicon-valley/ if you have any
questions.
*/
package main.java.edu.cmu.sv.sdsp.senseControllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import main.java.edu.cmu.sv.sdsp.senseBid.ValueProvider;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class BidTemperatureController.
 */
public class BidTemperatureController extends
		AsyncTask<String, String, String> {
	
	private ValueProvider callback;
	/** The ctx. */
	public Activity ctx;
	
	/**
	 * Instantiates a new bid temperature controller.
	 *
	 * @param context the context
	 */
	public BidTemperatureController(Activity context) {
		this.ctx = context;
		this.callback = (ValueProvider) context;
		
	}

	/** The Constant URL. */
	private static final String URL = "http://cmu-app-server.herokuapp.com/sensor/smartSense/bid";
	//private static final String URL = "http://localhost:8080/sensor/smartSense/bid";



	@Override
	protected String doInBackground(String... arguments)  {

		String urlStr = URL;
		String jsonString = arguments[0];
		URL url = null;
		HttpURLConnection conn = null;
		OutputStream out = null;
		Writer writer = null;
		
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			out = conn.getOutputStream();
			writer = new OutputStreamWriter(out, "UTF-8");
			//String data = (new Gson()).toJson(jsonString); 
			writer.write(jsonString);
		}catch(Exception e)
		{
			Log.e("Test", "Exception" + e.getMessage());
		}
		
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		try {
//			if (conn.getResponseCode() != 200) {
//				throw new IOException(conn.getResponseMessage());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		StringBuilder sb = null;
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rd.close();
			} catch (IOException e) {
				// TODO Auto-geneTorated catch block
				e.printStackTrace();
			}
		}

		conn.disconnect();
		String str = sb.toString();
		
		return str;
	}
	

	@Override
	protected void onPostExecute(String result)
	{
//		ValueProvider prov = new ValueProvider();
//		prov.setCurrentWinner(result);
		String str = result.substring(32);
		str = new StringBuffer(str).reverse().substring(2).toString();  
		str = new StringBuffer(str).reverse().toString();
		this.callback.onTask(str);
		Toast.makeText(this.ctx, "Current Winning temp: " + str, Toast.LENGTH_LONG).show();
		Log.d("test_2", result);
	}



}