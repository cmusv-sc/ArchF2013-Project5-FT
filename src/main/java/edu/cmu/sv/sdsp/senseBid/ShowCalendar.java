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

import java.util.ArrayList;

import main.java.edu.cmu.sv.sdsp.senseAdapters.CalArrayAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import edu.cmu.sv.sensebid.R;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowCalendar.
 * This class provides the display for all the events selected by the user for the account chosen.
 */
public class ShowCalendar extends Activity implements AsyncTaskCompleteListener<ArrayList<Reservation>>{

        /** The location. */
        String location;

        // setContentView(R.layout.activity_main_screen);
        /* (non-Javadoc)
         * @see android.app.Activity#onCreate(android.os.Bundle)
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main_screen);
                //The below statement should have values of username and password in the missing fields.
		//As an e.g.
		//new CalendarProvider(this, 'cmuUserName@gmail.com', 'cmuPassword').execute();
                new CalendarProvider(this, "", "").execute();
        }
                
        /*
         * public void setLocation(String location){ //Log.d("test_2", location);
         * this.location = location; } public String getLocation() { Log.d("test_2",
         * location); return location; }
         */

        /* (non-Javadoc)
         * @see main.java.edu.cmu.sv.sdsp.senseBid.AsyncTaskCompleteListener#onTaskCompleted(java.lang.Object)
         */
        @Override
        public void onTaskCompleted(ArrayList<Reservation> result) {
                        
                final ArrayList<Reservation> reservationsList = result;

                final CalArrayAdapter adapter = new CalArrayAdapter(this,
                                android.R.layout.simple_list_item_1, reservationsList);

                /*
                 * int n = reservationsList.size();
                 *
                 * for (int i = 0; i < n; i++) Log.d("test_2",
                 * reservationsList.get(i).toString());
                 */

                ListView listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                                /*
                                 * Intent intent = null; intent = new
                                 * Intent(getApplicationContext(),
                                 * BidForTemperatureActivity.class); intent.putExtra("item",
                                 * adapter.getItem(position)); Log.d("test_2",
                                 * adapter.getItem(position).toString()); startActivity(intent);
                                 */
                                int i = 0;

                                Intent intent = new Intent(getApplicationContext(),
                                                BidForTemperatureActivity.class);
                                Bundle b = new Bundle();
                                Reservation obj = reservationsList.get(position);
                                b.putSerializable("SelectedList", obj);

                                /*Reservation obj = reservationsList.get(position);

                                // Log.d("test_2", obj.getStartDate().toString() +
                                // obj.getLocation().toString() +
                                // obj.getStartDate().toString());
                                BidForTemperatureActivity calD = new BidForTemperatureActivity(
                                                obj.getLocation(), obj.getStartDate(), obj.getEndDate());*/

                                intent.putExtras(b);

                                startActivity(intent);
                        }
                });
        }

}
