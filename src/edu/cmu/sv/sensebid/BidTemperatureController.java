<<<<<<< Updated upstream:src/edu/cmu/sv/sensebid/BidTemperatureController.java
package edu.cmu.sv.sensebid;
=======
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
>>>>>>> Stashed changes:src/main/java/edu/cmu/sv/sdsp/senseControllers/BidTemperatureController.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
<<<<<<< Updated upstream:src/edu/cmu/sv/sensebid/BidTemperatureController.java
=======

import main.java.edu.cmu.sv.sdsp.senseBid.GetBidResult;

import android.content.Context;
>>>>>>> Stashed changes:src/main/java/edu/cmu/sv/sdsp/senseControllers/BidTemperatureController.java
import android.os.AsyncTask;
import android.util.Log;

<<<<<<< Updated upstream:src/edu/cmu/sv/sensebid/BidTemperatureController.java
public class BidTemperatureController extends
		AsyncTask<String, String, String> {

	private static final String URL = "http://10.0.22.99:8080/sensor";
	//private static final String URL = "http://10.0.22.99:8080/sensor";

=======

/**
 * The Class BidTemperatureController.
 * Send a RESTful post to the server with the temperature preference of the User.
 * Format : {"bidTemperature":{"user_id":"355031041120219","room_no":"Rm 129A","start_time":"1386630000000",
 * "end_time":"1386633600000","temperature_f":"22","bid_amount":"22","timestamp":1386543121089}}
 * 
 * These values are used to determine the winning temperature and is chosen to control nest
 */
public class BidTemperatureController extends AsyncTask<String, String, String> {

	/** The ctx. */
	public Context ctx;

	/**
	 * Instantiates a new bid temperature controller.
	 *
	 * @param context the context
	 */
	public BidTemperatureController(Context context) {
		this.ctx = context;
	}

	 /** The Constant URL. */
 	private static final String URL = "http://cmu-app-server.herokuapp.com/sensor/smartSense/bid";
	//private static final String URL = "http://192.168.3.107:8080/sensor/smartSense/bid";
>>>>>>> Stashed changes:src/main/java/edu/cmu/sv/sdsp/senseControllers/BidTemperatureController.java

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(String... arguments) {

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
			// String data = (new Gson()).toJson(jsonString);
			writer.write(jsonString);
		} catch (Exception e) {
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

		try {
			if (conn.getResponseCode() != 200) {
				throw new IOException(conn.getResponseMessage());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		conn.disconnect();
<<<<<<< Updated upstream:src/edu/cmu/sv/sensebid/BidTemperatureController.java
		return sb.toString();
=======
		String str = sb.toString();

		return str;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		
//		new GetBidResult()
//		.execute();
	     
		Log.d("test_2", result);
		Toast.makeText(this.ctx, result, Toast.LENGTH_LONG).show();
		
		
>>>>>>> Stashed changes:src/main/java/edu/cmu/sv/sdsp/senseControllers/BidTemperatureController.java
	}

}
