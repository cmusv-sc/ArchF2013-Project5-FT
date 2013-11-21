package edu.cmu.sv.sensebid;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SenseDroidMainActivity extends Activity {

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Authenticate user
		
		CalendarProvider calendarProvider = new CalendarProvider();
		ArrayList<Reservation> events = calendarProvider.readCalendarEvents(this);
	
		Intent i = new Intent(this, SensorDataPublisherService.class);
		startActivity(i);

		Intent j = new Intent(this, BidForTemperatureActivity.class);
		startActivity(j);
	}

}
