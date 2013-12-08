package main.java.edu.cmu.sv.sdsp.senseConstants;

import java.util.HashMap;

import android.hardware.Sensor;

public final class Constants {

	// This method maps the Sensor names of android to Sensor Names of the
	// sensor data cloud platform
	public final static HashMap<Integer, String> sensorNameMapping;

	static {
		sensorNameMapping = new HashMap<Integer, String>();
		sensorNameMapping.put(Sensor.TYPE_ACCELEROMETER, "Accelerometer_x");
		sensorNameMapping.put(Sensor.TYPE_AMBIENT_TEMPERATURE, "Temperature");
		sensorNameMapping.put(Sensor.TYPE_GRAVITY, "Gravity");
		sensorNameMapping.put(Sensor.TYPE_GYROSCOPE, "Gyroscope");
		sensorNameMapping.put(Sensor.TYPE_GYROSCOPE_UNCALIBRATED, "Gyroscope");
		sensorNameMapping.put(Sensor.TYPE_LIGHT, "Light");
		sensorNameMapping.put(Sensor.TYPE_LINEAR_ACCELERATION,
				"Linear Acceleration");
		sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD, "Magenetic Field");
		sensorNameMapping.put(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
				"Magenetic Field");
		sensorNameMapping.put(Sensor.TYPE_PROXIMITY, "Proximity");
		sensorNameMapping.put(Sensor.TYPE_PRESSURE, "Pressure");
		sensorNameMapping.put(Sensor.TYPE_RELATIVE_HUMIDITY, "Humidity");
		sensorNameMapping.put(Sensor.TYPE_ROTATION_VECTOR, "Rotation Vector");
		sensorNameMapping.put(Sensor.TYPE_GAME_ROTATION_VECTOR, "Game Rotation Vector");
		// Deprecated from API LEVEL 8. 
		// The way to do this is to provide a configuration page to configure
		// what sensor values need to be sent
		
		sensorNameMapping.put(Sensor.TYPE_ORIENTATION, "Orientation Vector");
		

	}

}
