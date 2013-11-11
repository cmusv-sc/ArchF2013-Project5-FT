package edu.cmu.sv.sensebid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SenseDroidMainActivity extends Activity {

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent i = new Intent(this, SensorDataPublisherService.class);
		startActivity(i);

		Intent j = new Intent(this, BidForTemperatureActivity.class);
		startActivity(j);
	}

}
