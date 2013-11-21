package edu.cmu.sv.sensebid;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class SenseDroidMainActivity extends Activity {

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		Intent i = new Intent(this, SensorDataPublisherService.class);
		startActivity(i);

		Intent j = new Intent(this, BidForTemperatureActivity.class);
		startActivity(j);
		
		setContentView(R.layout.activity_main_screen);
		
		final ListView listview = (ListView) findViewById(R.id.listview);
		
	    //getting arraylist from Emi
	    }
		
		final ArrayAdapter adapter = new ArrayAdapter(this,
		        android.R.layout.simple_list_item_1, list);
		    listview.setAdapter(adapter);

	}

}
