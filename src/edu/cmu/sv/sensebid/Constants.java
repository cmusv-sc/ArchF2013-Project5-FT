package edu.cmu.sv.sensebid;

import java.util.HashMap;

import android.hardware.Sensor;

	
	public final class Constants {
		
		// This method maps the Sensor names of android to Sensor Names of the sensor data cloud platform 
		public final static HashMap<Integer,String> sensorNameMapping;
		//public final static String device_identifier_string = "device_id";
		
		static
		{
			//TODO: sparseArray v HashMap v Hashtable ??? Ask Surya
			sensorNameMapping = new HashMap<Integer,String>();
			sensorNameMapping.put(Sensor.TYPE_ACCELEROMETER, "Accelerometer_x");
			sensorNameMapping.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "Temperature");
			sensorNameMapping.put(Sensor.TYPE_GRAVITY, "Gravity");
			sensorNameMapping.put(Sensor.TYPE_GYROSCOPE, "Gyroscope");
			sensorNameMapping.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, "Gyroscope");
			sensorNameMapping.put(Sensor.TYPE_LIGHT, "Light");
			sensorNameMapping.put(Sensor.TYPE_LINEAR_ACCELERATION, "Linear Acceleration");
			sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD, "Magenetic Field");
			sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED, "Magenetic Field");
			sensorNameMapping.put(Sensor.TYPE_PROXIMITY, "Proximity");
			sensorNameMapping.put(Sensor.TYPE_RELATIVE_HUMIDITY, "Humidity");
			sensorNameMapping.put(Sensor.TYPE_ROTATION_VECTOR, "Rotation Vector");
			
		}
	
	}
