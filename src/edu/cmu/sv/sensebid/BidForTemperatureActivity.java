package edu.cmu.sv.sensebid;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

public class BidForTemperatureActivity extends Activity {

	int coins;
	Button buttonBid, buttonTime;
	TextView display;
	EditText room, day_1, month_1, hour_1, min_1, temp_1, coins_1;

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d("Test", "I am here");

		//

		buttonBid = (Button) findViewById(R.id.buttonBid);
		// display = (TextView) findViewById(R.id.tvDisplay);
		// room, day_1, month_1, hour_1, min_1, temp_1, coins_1
		room = (EditText) findViewById(R.id.etRooms);
		day_1 = (EditText) findViewById(R.id.etDay);
		month_1 = (EditText) findViewById(R.id.etMonth);
		hour_1 = (EditText) findViewById(R.id.etHours);
		min_1 = (EditText) findViewById(R.id.etMinutes);
		temp_1 = (EditText) findViewById(R.id.etTemp);
		coins_1 = (EditText) findViewById(R.id.etCoins);

		String time = hour_1 + ":" + min_1;

		buttonBid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				JsonObject bidData = new JsonObject();
				JsonObject bidDataBody = new JsonObject();
				java.util.Date date = new java.util.Date();
				String start_time, end_time = null;
				start_time = "2013" + "/" + month_1.getText().toString() + "/"
						+ day_1.getText().toString() + " "
						+ hour_1.getText().toString() + ":"
						+ min_1.getText().toString();
				end_time = "2013" + "/" + month_1.getText().toString() + "/"
						+ day_1.getText().toString() + " "
						+ hour_1.getText().toString() + ":"
						+ min_1.getText().toString();
				
				TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
				String m_deviceId = TelephonyMgr.getDeviceId();
				
				bidData.addProperty("user_id", m_deviceId);
				bidData.addProperty("room_no", room.getText().toString());
				//bidData.addProperty("day", day_1.getText().toString());
				bidData.addProperty("start_time", start_time);
				bidData.addProperty("end_time", end_time);
				bidData.addProperty("temperature_f", temp_1.getText().toString());
				bidData.addProperty("bid_amount", coins_1.getText().toString());
				bidData.addProperty("timestamp", date.getTime());
				bidDataBody.add("bidTemperature", bidData);

				Log.d("Test", bidDataBody.toString());

				new BidTemperatureController().execute(bidDataBody.toString());

			}
		});

	}
}
