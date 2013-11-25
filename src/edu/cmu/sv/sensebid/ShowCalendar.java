package edu.cmu.sv.sensebid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ShowCalendar extends Activity {

	// setContentView(R.layout.activity_main_screen);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);

		CalendarProvider calendarProvider = new CalendarProvider();

		final ArrayList<Reservation> reservationsList = calendarProvider
				.readCalendarEvents(this);

		final CalArrayAdapter adapter = new CalArrayAdapter(this,
				android.R.layout.simple_list_item_1, reservationsList);

		int n = reservationsList.size();
		/*
		 * for (int i = 0; i < n; i++) Log.d("test_2",
		 * reservationsList.get(i).toString());
		 */

		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new ListView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = null;
				intent = new Intent(getApplicationContext(),
						BidForTemperatureActivity.class);
				intent.putExtra("item", adapter.getItem(position));
				Log.d("test_2", adapter.getItem(position).toString());
				startActivity(intent);
			}
		});
	}

}
