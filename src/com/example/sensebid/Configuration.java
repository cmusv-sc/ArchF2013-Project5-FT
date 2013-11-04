package com.example.sensebid;

public class Configuration {

	private String instance;
	private String sensorAuth;
	private String endPoint;
	private String prefTemp;
	private String publishInterval;

	public Configuration() {

	}

	public void setInstance(String newInstance) {
		instance = newInstance;
	}

	public String getInstance() {
		return instance;
	}

	public void setSensorAuth(String newSensorAuth) {
		sensorAuth = newSensorAuth;
	}

	public String getSensorAuth() {
		return sensorAuth;
	}

	public void setEndPoint(String newEndPoint) {
		endPoint = newEndPoint;

	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setPrefTemp(String newPrefTemp) {
		prefTemp = newPrefTemp;
	}

	public String getPrefTemp() {
		return prefTemp;
	}

	public void setPublishInterval(String newPublishInterval) {
		publishInterval = newPublishInterval;
	}

	public String getPublishInterval() {
		return publishInterval;
	}
}
