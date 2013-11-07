package edu.cmu.sv.sensebid;

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

public class SensorActivity extends Activity implements SensorEventListener {
	
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
		for(int i=1; i < mSensorList.size();i++)
		{
			 //  Log.d("SensorName", mSensorList.get(i).getName());
			  // Log.d("SensorType", Integer.valueOf(mSensorList.get(i).getType()+"" ,16)+"");
		}
     
    
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
		
	

    protected void onResume() {
        super.onResume();
       for (int i = 0; i < mSensorList.size(); i++)
       {
       mSensorManager.registerListener(this,availableSensors[i], SensorManager.SENSOR_DELAY_NORMAL);
       }
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	
    }
    
    public String str(float f)
    {
    	return String.valueOf(f);
    }

    public void onSensorChanged(SensorEvent event) {
    	

    	Log.d(event.sensor.getName(), str(event.values[0])  + str(event.values[1]) +  str(event.values[2]) );

    	
    	
    }



}
