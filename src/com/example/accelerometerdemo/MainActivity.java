package com.example.accelerometerdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
		TextView title,tv,tv1,tv2, test;
	RelativeLayout layout;
	
	String URLStr = "http://209.129.244.186/sensors";
	Writer writer;
	HttpURLConnection conn;
	OutputStream out;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
	

		// Get an instance of the sensor service, and use that to get an
		// instance of
		// a particular sensor.
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		if (mAccelerometer == null) {
			System.out.println("no acc sensor");
		}
		
		 layout = (RelativeLayout)findViewById(R.id.relative);
	    //get textviews
	   title=(TextView)findViewById(R.id.name);
	   tv=(TextView)findViewById(R.id.xval);
	   tv1=(TextView)findViewById(R.id.yval);
	   tv2=(TextView)findViewById(R.id.zval);
	   test = (TextView)findViewById(R.id.testval);
	}

	@Override
	public final void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
	}

	@Override
	public final void onSensorChanged(SensorEvent event) {
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
			tv.setText("X axis" +"\t\t"+x);
			SensorReadingPostExample(x);
			Log.d("check_url","Im here");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		// Register a listener for the sensor.
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// Be sure to unregister the sensor when the activity pauses.
		super.onPause();
		mSensorManager.unregisterListener(this);
		
	}

	public void SensorReadingPostExample(float value)  {
try{
		JsonObject jo = new JsonObject();
		// Sample data
		java.util.Date date = new java.util.Date();
		jo.addProperty("timestamp", date.getTime()); // Long type
		System.out.println(date.getTime());
		jo.addProperty("id", "phone_n"); // String type	
		jo.addProperty("temp",value); // Double type

		writer.write(jo.toString());
		
		Log.d("check_url_inside",jo.toString());
		
		
			writer.close();
			out.close();

			if (conn.getResponseCode() != 200) {
				throw new IOException(conn.getResponseMessage());
			}

			// Buffer the result into a string
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
}

	
}
