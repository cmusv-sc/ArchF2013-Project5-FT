package com.example.sensebid;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import android.hardware.SensorEvent;
import android.util.Log;

public class GetSensorReading extends MainActivity {

	static HttpURLConnection conn;
	static OutputStream out;
	static Writer writer;

	public final static void getAccelerometer(SensorEvent event) {
		
		

		String URLStr = "http://209.129.244.7/sensors";

		try {

			URL url = new URL(URLStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);

			// Create the form content

			out = conn.getOutputStream();

			writer = new OutputStreamWriter(out, "UTF-8");
			float x = event.values[0];
			

			tv.setText("X axis" + "\t\t" + x);
			Log.d("Sensor", "I am on line");
			HanaPublish presult = new HanaPublish();
			presult.execute(URLStr, String.valueOf(x));

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Sensor_fail", e.toString());
		}

	}
}
