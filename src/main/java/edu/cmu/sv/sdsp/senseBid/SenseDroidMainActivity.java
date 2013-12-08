package main.java.edu.cmu.sv.sdsp.senseBid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SenseDroidMainActivity extends Activity {

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// CalendarProvider calendarProvider = new CalendarProvider();
		// // fix warning
		// ArrayList<Reservation> events =
		// calendarProvider.readCalendarEvents(this);

		// Intent i = new Intent(this, SensorDataPublisherService.class);
		// startActivity(i);
		//
		// Intent j = new Intent(this, BidForTemperatureActivity.class);
		// startActivity(j);
		//Intent l = new Intent(this, User.class);
		//startActivity(l);

		Intent k = new Intent(this, Login.class);
		startActivity(k);

//		Intent m = new Intent(this, UserPreferencesController.class);
//		startActivity(m);

	}
	
	

}
