package edu.cmu.sv.sensebid;

import java.util.ArrayList;
import java.util.Date;

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
	String room;
	EditText day_1, month_1, hour_1, min_1, temp_1, coins_1;

	String ar[] = new String[100];
	public String location;
	private Object startTime;
	private Object endTime;

		public Object getClickedStartTime() {
		return startTime;
	}

	public Object getClickedEndTime() {
		return endTime;
	}

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// How to get values from intent call
		Bundle b = getIntent().getExtras();

		final Reservation obj = (Reservation) b.getSerializable("SelectedList");
		buttonBid = (Button) findViewById(R.id.buttonBid);
		display = (TextView) findViewById(R.id.tvDisplay);
		display.setText(obj.toString());
		temp_1 = (EditText) findViewById(R.id.etTemp);
		coins_1 = (EditText) findViewById(R.id.etCoins);

		String time = hour_1 + ":" + min_1;

		buttonBid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				JsonObject bidData = new JsonObject();
				JsonObject bidDataBody = new JsonObject();
				java.util.Date date = new java.util.Date();

				String start_time, end_time = null;
				TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
				String m_deviceId = TelephonyMgr.getDeviceId();

				bidData.addProperty("user_id", m_deviceId);
				bidData.addProperty("room_no", obj.getLocation());
				bidData.addProperty("start_time",
						String.valueOf(obj.getStartDate().getTime()));
				bidData.addProperty("end_time",
						String.valueOf(obj.getEndDate().getTime()));
				bidData.addProperty("temperature_f", temp_1.getText()
						.toString());
				bidData.addProperty("bid_amount", coins_1.getText().toString());
				bidData.addProperty("timestamp", date.getTime());
				bidDataBody.add("bidTemperature", bidData);

				Log.d("test_2", bidDataBody.toString());

				new BidTemperatureController().execute(bidDataBody.toString());

			}
		});

	}

}
