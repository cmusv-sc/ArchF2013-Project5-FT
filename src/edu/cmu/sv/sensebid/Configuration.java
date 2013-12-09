<<<<<<< Updated upstream:src/edu/cmu/sv/sensebid/Configuration.java
package edu.cmu.sv.sensebid;
=======
/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley.
All rights reserved.

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

Please contact http://www.cmu.edu/silicon-valley/ if you have any
questions.
*/
package main.java.edu.cmu.sv.sdsp.senseConfig;
>>>>>>> Stashed changes:src/main/java/edu/cmu/sv/sdsp/senseConfig/Configuration.java

// TODO: Auto-generated Javadoc
/**
 * The Class Configuration.
 * Provides additional configuration for the application. 
 * Not providing any data in its present state.
 */
public class Configuration {

	/** The sensor auth. */
	private String sensorAuth;
	
	/** The end point. */
	private String endPoint;
	
	/** The pref temp. */
	private String prefTemp;
	
	/** The publish interval. */
	private String publishInterval;
	
	/** The Constant device_id. */
	private final static String device_id = "android_test";

	/**
	 * Gets the _device_id.
	 *
	 * @return the _device_id
	 */
	public static String get_device_id() {
		//TODO: Add logic over here for getting the device id
		return device_id;
	}

	/** The m instance. */
	private static Configuration mInstance = null;

	/**
	 * Instantiates a new configuration.
	 */
	protected Configuration() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Gets the single instance of Configuration.
	 *
	 * @return single instance of Configuration
	 */
	public static Configuration getInstance() {
		if (mInstance == null) {
			mInstance = new Configuration();
		}
		return mInstance;
	}

	/**
	 * Sets the sensor auth.
	 *
	 * @param newSensorAuth the new sensor auth
	 */
	public void setSensorAuth(String newSensorAuth) {
		sensorAuth = newSensorAuth;
	}

	/**
	 * Gets the sensor auth.
	 *
	 * @return the sensor auth
	 */
	public String getSensorAuth() {
		return sensorAuth;
	}

	/**
	 * Sets the end point.
	 *
	 * @param newEndPoint the new end point
	 */
	public void setEndPoint(String newEndPoint) {
		endPoint = newEndPoint;

	}

	/**
	 * Gets the end point.
	 *
	 * @return the end point
	 */
	public String getEndPoint() {
		return endPoint;
	}

	/**
	 * Sets the pref temp.
	 *
	 * @param newPrefTemp the new pref temp
	 */
	public void setPrefTemp(String newPrefTemp) {
		prefTemp = newPrefTemp;
	}

	/**
	 * Gets the pref temp.
	 *
	 * @return the pref temp
	 */
	public String getPrefTemp() {
		return prefTemp;
	}

	/**
	 * Sets the publish interval.
	 *
	 * @param newPublishInterval the new publish interval
	 */
	public void setPublishInterval(String newPublishInterval) {
		publishInterval = newPublishInterval;
	}

	/**
	 * Gets the publish interval.
	 *
	 * @return the publish interval
	 */
	public String getPublishInterval() {
		return publishInterval;
	}
}
