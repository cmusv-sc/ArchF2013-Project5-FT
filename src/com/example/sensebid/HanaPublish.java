package com.example.sensebid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

class HanaPublish extends AsyncTask<String, String, Long> {

	private static final String MSG_TAG_5 = "tag_5";
	final String MSG_TAG_1 = "tag_1";
	final String MSG_TAG_2 = "tag_2";
	final String MSG_TAG_3 = "tag_3";
	final String MSG_TAG_4 = "tag_4";

	@Override
	protected Long doInBackground(String... arguments) {
		// TODO Auto-generated method stub

		// Extract Arguments:

		String urlStr = arguments[0];
		String jsonString = arguments[1];

		Log.d(MSG_TAG_5, jsonString);
		Log.d("check_url", urlStr);
		// URL url;

		try {
			JsonObject jo = new JsonObject();
			// Sample data
			java.util.Date date = new java.util.Date();
			jo.addProperty("timestamp", date.getTime()); // Long type
			System.out.println(date.getTime());
			jo.addProperty("id", "phone_n"); // String type
			jo.addProperty("temp", jsonString); // Double type

			GetSensorReading.writer.write(jo.toString());

			Log.d("check_url_inside", jo.toString());

			GetSensorReading.writer.close();
			GetSensorReading.out.close();

			if (GetSensorReading.conn.getResponseCode() != 200) {
				throw new IOException(
						GetSensorReading.conn.getResponseMessage());
			}

			// Buffer the result into a string
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					GetSensorReading.conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			GetSensorReading.conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

		// Buffer the result into a string

		// return sb.toString();

	}

}
