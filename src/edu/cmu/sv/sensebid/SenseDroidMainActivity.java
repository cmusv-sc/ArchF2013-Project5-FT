package edu.cmu.sv.sensebid;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SenseDroidMainActivity extends Activity {

	int coins;
	Button buttonBid, buttonTime;
	TextView display;
	EditText temperature;
	



	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent i = new Intent(this, SensorDataPublisherService.class);
		startActivity(i); 


		buttonBid = (Button) findViewById(R.id.buttonBid);
		// display = (TextView) findViewById(R.id.tvDisplay);
		temperature = (EditText) findViewById(R.id.etRooms);
		buttonBid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});

	}

}
