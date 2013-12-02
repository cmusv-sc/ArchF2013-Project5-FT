package edu.cmu.sv.sensebid;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;

public class SensorDataPublisherService extends Activity implements
		SensorEventListener {

	private final int interval_seconds = 5 * 1000;
	private SensorManager mSensorManager;
	private List<Sensor> mSensorList;
	private Hashtable<Integer, float[]> ht;
	private Sensor[] availableSensors;

	HashMap<Integer, String> sensorName = Constants.sensorNameMapping;

	String dev_id = "android_test";

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

		ht = new Hashtable<Integer, float[]>();
		
		registerSensor(mSensorList);
	}

	// Method to register sensors
	public void registerSensor(List<Sensor> sensorList) {
		int numberOfSensors = sensorList.size();
		Log.d("Number of Sensors", numberOfSensors + "");

		availableSensors = new Sensor[numberOfSensors];

		for (int i = 0; i < numberOfSensors; i++) {
			availableSensors[i] = mSensorManager.getDefaultSensor(sensorList
					.get(i).getType());
			Log.d("Test", "I am here");
		}
	}

	// Method to convert sensor information to json
	public List<JsonObject> getSensorJsons() {
		// For initial conditions where ht might be NULL
		if (ht == null || ht.size() == 0) {
			return null;
		}

		Date date = new java.util.Date();
		long timestamp = date.getTime();

		List<JsonObject> sensorDataJsons = new ArrayList<JsonObject>();

		Iterator<Integer> itr = ht.keySet().iterator();
		while (itr.hasNext()) {
			Integer key = itr.next(); // Key in the sensor type map
			float[] value = ht.get(key); // Float array fetched from sensors

			JsonObject sensorDataJson = new JsonObject();

			sensorDataJson.addProperty("device_id", dev_id);
			sensorDataJson.addProperty("sensor_type", sensorName.get(key));
			sensorDataJson.addProperty("sensor_value", value[0]);
			sensorDataJson.addProperty("timestamp", timestamp);

			sensorDataJsons.add(sensorDataJson);
		}

		return sensorDataJsons;

	}

	@Override
	protected void onResume() {
		super.onResume();
		for (int i = 0; i < mSensorList.size(); i++) {

			mSensorManager.registerListener(this, availableSensors[i],
					SensorManager.SENSOR_DELAY_NORMAL);
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO: Adding a parameter for accuracy to the sensor reading being
		// published ?
	}

	public String str(Object f) {
		String type = f.getClass().getName();
		if (type.equals("Integer") || type.equals("Float")) {
			return String.valueOf(f);
		} else
			return null;

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		ht.put(event.sensor.getType(), event.values);

		// TODO: Fix this logic
		try {
			new SdasPlatformFacade().execute(getSensorJsons());
			Thread.sleep(interval_seconds);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
