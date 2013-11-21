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
	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < values.length; ++i) {
	      list.add(values[i]);
	      
	      ExampleClass exampleObj = list.get(2)  
		
		final ArrayListAdapter adapter = new ArrayAdapter(this,
		        android.R.layout.simple_list_item_1, list);
	      
	      ada
		    listview.setAdapter(adapter);
		    
		    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		        @Override
		        public void onItemClick(AdapterView<?> parent, final View view,
		            int position, long id) {
		          final String item = (String) parent.getItemAtPosition(position);
		          view.animate().setDuration(2000).alpha(0)
		              .withEndAction(new Runnable() {
		                @Override
		                public void run() {
		                  list.remove(item);
		                  adapter.notifyDataSetChanged();
		                  view.setAlpha(1);
		                }
		              });
		        }    

	}

}
