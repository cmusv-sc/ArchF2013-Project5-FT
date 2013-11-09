package edu.cmu.sv.sensebid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SdasPlatformFacade extends
		AsyncTask<List<JsonObject>, Integer, Integer> {

	private static final String URL = "http://einstein.sv.cmu.edu/sensors";

	// Thanks to Surya Kiran for this method
	public static String httpPostSensorReading(String urlStr,
			JsonObject jsonObject) throws Exception {

		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		OutputStream out = null;
		Writer writer = null;
		try {
			out = conn.getOutputStream();
			writer = new OutputStreamWriter(out, "UTF-8");

			writer.write((new Gson()).toJson(jsonObject));
		} finally {
			writer.close();
			out.close();
		}

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}
		StringBuilder sb;
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
		} finally {
			rd.close();
		}

		conn.disconnect();
		return sb.toString();
	}

	@Override
	protected Integer doInBackground(List<JsonObject>... req) {

		Iterator<JsonObject> itr = req[0].iterator();
		int count = 0;
		int total = req[0].size();

		while (itr.hasNext()) {
			JsonObject jsonObject = itr.next();
			try {
				System.out.println(httpPostSensorReading(URL, jsonObject));
				// TOD: Check for the response message and then update count.
				count++;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		Log.d("Sucess", String.valueOf(count));
		Log.d("Fail", String.valueOf(total - count));
		return count;

	}

}
