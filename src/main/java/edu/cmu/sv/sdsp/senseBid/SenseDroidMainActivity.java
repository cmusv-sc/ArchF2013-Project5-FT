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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

// TODO: Auto-generated Javadoc
/**
 * The Class SenseDroidMainActivity.
 * This is the controlling activity class for the project.
 */
public class SenseDroidMainActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
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
