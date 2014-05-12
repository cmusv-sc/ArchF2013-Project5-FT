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
package main.java.edu.cmu.sv.sdsp.senseBid;

import main.java.edu.cmu.sv.sdsp.senseControllers.BidTemperatureController;
import main.java.edu.cmu.sv.sdsp.senseControllers.GetCreditController;
import main.java.edu.cmu.sv.sdsp.senseControllers.GetWinnerHistory;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import edu.cmu.sv.sensebid.R;

// TODO: Auto-generated Javadoc
/**
 * The Class BidForTemperatureActivity. This is the main screen which provides
 * the user with inputs of temperature values and bid for a particular event on
 * his calendar
 */
public class BidForTemperatureActivity extends Activity implements ValueProvider{

	/** The coins. */
	int coins;

	/** The test button. */
	Button buttonBid, bAmount, testButton;
	java.util.Date date = new java.util.Date();



	/** The my coins. */
	TextView display, myCoins;

	/** The current winner. */
	EditText temp_1, coins_1, currentWinner;


	

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bidding_main);
		

		// How to get values from intent call
		Bundle b = getIntent().getExtras();

		final Reservation obj = (Reservation) b.getSerializable("SelectedList");
		myCoins = (TextView) findViewById(R.id.tvTotCoins);
		buttonBid = (Button) findViewById(R.id.buttonBid);
		bAmount = (Button) findViewById(R.id.BarGraph);
		// testButton = (Button) findViewById(R.id.test);
		display = (TextView) findViewById(R.id.tvDisplay);
		// userEmail = (TextView) findViewById(R.id.)
		display.setText(obj.toString());
		temp_1 = (EditText) findViewById(R.id.etTemp);
		coins_1 = (EditText) findViewById(R.id.etCoins);
		currentWinner = (EditText) findViewById(R.id.etWinTempVal);
		

		showPrevWinner(obj);
		// Intent m = new Intent(this, UserPreferencesController.class);
		// startActivity(m);

		buttonBid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				sendData(obj);

			}
		});
		bAmount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				showGraph(v);
			}
		});

	}


	/**
	 * Send data.
	 * 
	 * @param obj
	 *            the obj
	 */
	public void sendData(Reservation obj) {
		JsonObject bidData = new JsonObject();
		
		JsonObject bidDataBody = new JsonObject();
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String m_deviceId = TelephonyMgr.getDeviceId();
		bidData.addProperty("user_id", "user4");
		bidData.addProperty("room_no", obj.getLocation());
		bidData.addProperty("start_time",
				String.valueOf(obj.getStartDate().getTime()));
		bidData.addProperty("end_time",
				String.valueOf(obj.getEndDate().getTime()));
		bidData.addProperty("temperature_f", temp_1.getText().toString());
		// bidData.addProperty("temperature_f", temp_1.getValue();
		bidData.addProperty("bid_amount", coins_1.getText().toString());
		bidData.addProperty("timestamp", date.getTime());
		bidDataBody.add("bidTemperature", bidData);



		

		// GetBidResult winTemp = new GetBidResult();

		// Log.d("test_2", winTemp.doInBackground());
		// currentWinner.setText(winTemp.doInBackground());

		Log.d("test_2", bidDataBody.toString());

		new BidTemperatureController(this)
				.execute(bidDataBody.toString());

		// Adding credits data
		

		// Log.d("test_2", creditDataBody.toString());
	}

	/**
	 * Show graph.
	 * 
	 * @param v
	 *            the v
	 */
	public void showGraph(View v) {
		switch (v.getId()) {

		case R.id.BarGraph:
			BarGraph bar = new BarGraph();
			Intent barIntent = bar.getIntent(getApplicationContext());
			// startActivity(bar.getIntent(getApplicationContext()));
			startActivity(barIntent);
			break;
		}
	}

	public void showPrevWinner(Reservation obj) {
		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String m_deviceId = TelephonyMgr.getDeviceId();
		JsonObject bidPrevData = new JsonObject();
		//ValueProvider obj1 = new ValueProvider();
		JsonObject bidPrevDataBody = new JsonObject();

		bidPrevData.addProperty("user_id", "user4");
		bidPrevData.addProperty("room_no", obj.getLocation());
		bidPrevData.addProperty("start_time",
				String.valueOf(obj.getStartDate().getTime()));
		bidPrevData.addProperty("end_time",
				String.valueOf(obj.getEndDate().getTime()));
		bidPrevData.addProperty("temperature_f", "0");
		// bidData.addProperty("temperature_f", temp_1.getValue();
		bidPrevData.addProperty("bid_amount", "0");
		bidPrevData.addProperty("timestamp", date.getTime());
		bidPrevDataBody.add("bidTemperature", bidPrevData);

		JsonObject creditData = new JsonObject();
		JsonObject creditDataBody = new JsonObject();
		
		creditData.addProperty("user_id", "user4");
		creditDataBody.add("UserCredit", creditData);
		
		//Gets winning temperature before with bidding is done
		new GetWinnerHistory(this).execute(bidPrevDataBody
				.toString());
//
//		
		//Gets user credit before the bid is done.
		new GetCreditController(this).execute(creditDataBody
				.toString());
		
		
//		currentWinner.setText(obj1.getPrevWinner());
//		
//		myCoins.setText(obj1.getCredit());

	}

	@Override
	public void onTask(String str) {
		currentWinner.setText(str);
		
	}

	@Override
	public void onCreditHist(String str) {
		myCoins.setText(str);
	}



	@Override
	public void onTaskHistory(String prevWinner) {
		currentWinner.setText(prevWinner);
		
	}
}
