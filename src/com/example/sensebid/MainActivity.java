package com.example.sensebid;

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
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	TextView title;
	static TextView tv;
	TextView tv1;
	TextView tv2;
	TextView test;
	RelativeLayout layout;
	int i = 0;

	// For preparing file
	String[] paramName = { "device_id", "timestamp", "sensor_type",
			"sensor_value" };

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // refer layout file code below
		// get the sensor service
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// get the accelerometer sensor
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		// If sensor not available
		if (mAccelerometer == null) {
			System.out.println("no temperature sensor");

		}

		// get layout
		layout = (RelativeLayout) findViewById(R.id.relative);
		// get textviews
		title = (TextView) findViewById(R.id.name);
		tv = (TextView) findViewById(R.id.xval);
		tv1 = (TextView) findViewById(R.id.yval);
		tv2 = (TextView) findViewById(R.id.zval);
		test = (TextView) findViewById(R.id.testval);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public final void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

			
			
			GetSensorReading.getAccelerometer(event);

		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

}
