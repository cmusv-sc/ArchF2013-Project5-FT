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

// TODO: Auto-generated Javadoc
/**
 * The Class GetCreditController.
 */
public class GetCreditController extends AsyncTask<String, String, String> {

	/** The ctx. */
	public Activity ctx;
	private ValueProvider callback;
	

	/**
	 * Instantiates a new gets the credit controller.
	 *
	 * @param context the context
	 */
	public GetCreditController(Activity context) {

		this.ctx = context;
		this.callback = (ValueProvider) context;
	}

	/** The Constant URL. */
	private static final String URL = "http://cmu-app-server.herokuapp.com/sensor/credit/getUserCredit";
	//private static final String URL = "http://localhost:8080/sensor/credit/getUserCredit";

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
			Log.e("Test", "Exception 1" + e.getMessage());
		}

		finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("Test", "Exception 2" + e.getMessage());
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("Test", "Exception 3" + e.getMessage());
			}
		}

//		try {
//			if (conn.getResponseCode() != 200) {
//				throw new IOException(conn.getResponseMessage());
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			Log.e("Test", "Exception" + e.getMessage());
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
			Log.e("Test", "Exception 4" + e.getMessage());
		} finally {
			try {
				rd.close();
			} catch (IOException e) {
				// TODO Auto-geneTorated catch block
				e.printStackTrace();
				Log.e("Test", "Exception 5" + e.getMessage());
			}
		}

		conn.disconnect();
		String str = sb.toString();

		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		String str = result.substring(25);
		str = new StringBuffer(str).reverse().substring(2).toString();  
		str = new StringBuffer(str).reverse().toString();
		Log.d("test_2", str);
		this.callback.onCreditHist(str);
		
		//ValueProvider getCredit = new ValueProvider();
		//getCredit.setCredit(str);

		//Toast.makeText(this.ctx, str + " Remaining", Toast.LENGTH_LONG).show();

	}

}
