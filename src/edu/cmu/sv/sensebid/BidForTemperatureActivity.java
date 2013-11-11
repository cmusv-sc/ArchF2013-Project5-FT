package edu.cmu.sv.sensebid;

import com.google.gson.JsonObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
		//room, day_1, month_1, hour_1, min_1, temp_1, coins_1
		room = (EditText) findViewById(R.id.etRooms);
		day_1 = (EditText) findViewById(R.id.etDay);
		month_1 = (EditText) findViewById(R.id.etMonth);
		hour_1 = (EditText) findViewById(R.id.etHours);
		min_1 = (EditText) findViewById(R.id.etMinutes);
		temp_1 = (EditText) findViewById(R.id.etTemp);
		coins_1 = (EditText) findViewById(R.id.etCoins);


		

		
		buttonBid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				JsonObject bidData = new JsonObject();

				bidData.addProperty("room", room.getText().toString());
				bidData.addProperty("day", day_1.getText().toString());
				bidData.addProperty("month", month_1.getText().toString());
				bidData.addProperty("hours", hour_1.getText().toString());
				bidData.addProperty("minutes", min_1.getText().toString());
				bidData.addProperty("temp", temp_1.getText().toString());
				bidData.addProperty("coins", coins_1.getText().toString());
				
				Log.d("Test", bidData.toString());
				
				new BidTemperatureController().execute(bidData.toString());

			}
		});
	

	}
}
