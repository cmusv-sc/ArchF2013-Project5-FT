package edu.cmu.sv.sensebid;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class SensorDataPublisherService extends Activity implements SensorEventListener {
	
    private  SensorManager mSensorManager;
    //private  Sensor mAccelerometer;
    private List<Sensor> mSensorList;
    public Hashtable<Integer,float[]> ht;
    public Sensor[] availableSensors;
	

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		ht = new Hashtable<Integer, float[]>(mSensorList.size());
		
		registerSensor(mSensorList);
	}

	// Method to register sensors
	public void registerSensor(List<Sensor> sensorList)
	{		
		int numberOfSensors = sensorList.size();
		Log.d("Number of Sensors",numberOfSensors+"");

		availableSensors = new Sensor[numberOfSensors];

		for(int i=0;i< numberOfSensors ;i++)
		{
			availableSensors[i] = mSensorManager.getDefaultSensor(sensorList.get(i).getType());  

		}
	}
	
	
	//Method to convert sensor information to json
	public JsonObject[] convertToJson()
	{
		JsonObject sensorDataJson[];
		Enumeration e = null;
		if(!ht.isEmpty())
		{
			 e = ht.keys();
			 sensorDataJson = new JsonObject[ht.size()];
			
		}
			
		while(e.hasMoreElements())
		{
			int key = (Integer) e.nextElement();
			float arr[] = ht.get(key);
			for(int j=0; j<arr.length;j++)
			{
				// This case doesn't send the sensor data if the value is 0.0.
				if(arr[j] != 0.0)
				{
					
				//	sensorDataJson.addProperty(String.valueOf(key) + "_" + String.v , str(arr[j]));
				}
			}	
		}
		return sensorDataJson;
		
	}




	protected void onResume() {
		super.onResume();
		for (int i = 0; i < mSensorList.size(); i++)
		{
			//mSensorManager.registerListener(this,availableSensors[i], SensorManager.SENSOR_DELAY_NORMAL);

			mSensorManager.registerListener(this,availableSensors[i],SensorManager.SENSOR_DELAY_NORMAL);

		}
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public String str(Object f)
	{
		String type = f.getClass().getName();
		if (type.equals("Integer")  || type.equals("Float"))
		{
		return String.valueOf(f);
		}
		else return null;
		
	}

	public void onSensorChanged(SensorEvent event) {

		ht.put(event.sensor.getType(), event.values);

		
		//Log.d("Json Object",this.convertToJson().toString());
		
	}





}
