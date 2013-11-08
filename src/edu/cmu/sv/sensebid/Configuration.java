package edu.cmu.sv.sensebid;

public class Configuration {

	private String sensorAuth;
	private String endPoint;
	private String prefTemp;
	private String publishInterval;
	private final static String device_id = "android_test";
	
	public static String get_device_id()
	{
		return device_id;
	}
	
	private static Configuration mInstance = null;

   protected Configuration() {
      // Exists only to defeat instantiation.
   }
   public static Configuration getInstance() {
      if(mInstance == null) {
    	  mInstance = new Configuration();
      }
      return mInstance;
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
