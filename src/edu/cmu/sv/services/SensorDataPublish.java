package edu.cmu.sv.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SensorDataPublish extends Service {

	@Override
	public void onCreate()
	{
		Log.d(new Exception().getStackTrace()[0].getMethodName(), "Reached");
	}
	
	@Override
	public void onStart(Intent intent,int startId)
	{
		Log.d(new Exception().getStackTrace()[0].getMethodName(), "Reached");
		//TODO: actions to be performed on start
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		//TODO:Replace with service binding implementation
		Log.d(new Exception().getStackTrace()[0].getMethodName(), "Reached");
		return null;
	}
	


}
