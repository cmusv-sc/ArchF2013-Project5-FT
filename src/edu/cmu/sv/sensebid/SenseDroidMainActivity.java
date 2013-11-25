package edu.cmu.sv.sensebid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SenseDroidMainActivity extends Activity {

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//	    CalendarProvider calendarProvider = new CalendarProvider();
//	    // fix warning
//        ArrayList<Reservation> events = calendarProvider.readCalendarEvents(this);

//		Intent i = new Intent(this, SensorDataPublisherService.class);
//		startActivity(i);
//
//		Intent j = new Intent(this, BidForTemperatureActivity.class);
//		startActivity(j);
		
		Intent k = new Intent(this, ShowCalendar.class);
		startActivity(k);
		
		
		
		
		
	}

}
