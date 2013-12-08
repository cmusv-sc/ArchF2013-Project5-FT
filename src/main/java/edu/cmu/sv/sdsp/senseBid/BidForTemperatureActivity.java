package main.java.edu.cmu.sv.sdsp.senseBid;

import main.java.edu.cmu.sv.sdsp.senseControllers.BidTemperatureController;

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

public class BidForTemperatureActivity extends Activity {

	int coins;
	Button buttonBid, bAmount, testButton;
	TextView display, myCoins;
	EditText temp_1, coins_1;
	// private static final int RECOGNIZER_EXAMPLE = 1001;

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
		setContentView(R.layout.bidding_main);

		// How to get values from intent call
		Bundle b = getIntent().getExtras();

		final Reservation obj = (Reservation) b.getSerializable("SelectedList");
		myCoins = (TextView) findViewById(R.id.tvTotCoins);
		buttonBid = (Button) findViewById(R.id.buttonBid);
		bAmount = (Button) findViewById(R.id.BarGraph);
		//testButton = (Button) findViewById(R.id.test);
		display = (TextView) findViewById(R.id.tvDisplay);
		// userEmail = (TextView) findViewById(R.id.)
		display.setText(obj.toString());
		temp_1 = (EditText) findViewById(R.id.etTemp);
		coins_1 = (EditText) findViewById(R.id.etCoins);

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

		// testButton.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		// intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
		// RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		// intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
		// "What temperature you want to set");
		// startActivityForResult(intent, RECOGNIZER_EXAMPLE);
		//
		// }
		// });
		//
		// }
		//
		// @Override
		// protected void onActivityResult(int requestCode, int resultCode,
		// Intent data){
		// if(requestCode == RECOGNIZER_EXAMPLE && resultCode == RESULT_OK){
		// ArrayList<String> result =
		// data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
		// myCoins.setText(result.toString());
		//
		// }
		//
		//
	}

	// This is where speech to text stops
	public void sendData(Reservation obj) {
		JsonObject bidData = new JsonObject();
		JsonObject bidDataBody = new JsonObject();
		java.util.Date date = new java.util.Date();

		TelephonyManager TelephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String m_deviceId = TelephonyMgr.getDeviceId();

		bidData.addProperty("user_id", m_deviceId);
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

		myCoins.setText("Test Value");

		Log.d("test_2", bidDataBody.toString());

		new BidTemperatureController(getApplicationContext())
				.execute(bidDataBody.toString());
	}

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
}
