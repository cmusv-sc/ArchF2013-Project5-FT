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
import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.os.AsyncTask;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;




// TODO: Auto-generated Javadoc
/**
 * The Class CalendarProvider.
 * Interacts with the GOOGLE API and retrieves the google calendar events for a particular user.
 */
public class CalendarProvider extends AsyncTask<String, Void, ArrayList<Reservation>>{
        
        /** The m task completed callback. */
        private AsyncTaskCompleteListener<ArrayList<Reservation>> mTaskCompletedCallback;
        
        /** The user. */
        private String user = "";
        
        /** The pwd. */
        private String pwd = "";
        
        /**
         * Instantiates a new calendar provider.
         *
         * @param listener the listener
         * @param usr the usr
         * @param pwd the pwd
         */
        public CalendarProvider(AsyncTaskCompleteListener<ArrayList<Reservation>> listener, String usr, String pwd){
                this.mTaskCompletedCallback = listener;
                this.user = usr;
                this.pwd = pwd;
        }
        
        /**
         * Read calendar events.
         *
         * @return the array list
         * @throws IOException Signals that an I/O exception has occurred.
         * @throws ServiceException the service exception
         */
        public ArrayList<Reservation> readCalendarEvents() throws IOException, ServiceException
        {
                ArrayList<Reservation> events = new ArrayList<Reservation>();
                
                URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/private/full");

                CalendarQuery myQuery = new CalendarQuery(feedUrl);
                
                Calendar tomorrowCalendar = Calendar.getInstance();
                tomorrowCalendar.add(Calendar.MINUTE, 30);
                Date today = tomorrowCalendar.getTime();
                
                tomorrowCalendar.setTime(today);
                tomorrowCalendar.add(Calendar.DAY_OF_YEAR, 1);
                
                tomorrowCalendar.set(Calendar.HOUR_OF_DAY, 23);
                tomorrowCalendar.set(Calendar.MINUTE, 59);
                tomorrowCalendar.set(Calendar.SECOND, 59);
                Date tomorrow = tomorrowCalendar.getTime();
                
                myQuery.setMinimumStartTime(new DateTime(today));
                myQuery.setMaximumStartTime(new DateTime(tomorrow));

                CalendarService myService = new CalendarService("exampleCo-exampleApp-1");
                myService.setUserCredentials(this.user, this.pwd);

                // Send the request and receive the response:
                CalendarEventFeed resultFeed = myService.query(myQuery, CalendarEventFeed.class);
                Reservation reservation = null;
                for (CalendarEventEntry partialEvent : resultFeed.getEntries()) {
                        reservation = new Reservation();
            reservation.setDescription(partialEvent.getTitle().getPlainText());
            
            List<When> times = partialEvent.getTimes();
            reservation.setStartDate(new Date(times.get(0).getStartTime().getValue()));
            reservation.setEndDate(new Date(times.get(0).getEndTime().getValue()));
            reservation.setLocation(partialEvent.getLocations().get(0).getValueString());
            events.add(reservation);
                }
                
                Collections.sort(events, new Comparator<Reservation>() {
                         @Override
						public int compare(Reservation o1, Reservation o2) {
                         if (o1.getStartDate() == null || o2.getStartDate() == null)
                         return 0;
                         return o1.getStartDate().compareTo(o2.getStartDate());
                         }
                        });
                
                return events;
        }

        /* (non-Javadoc)
         * @see android.os.AsyncTask#doInBackground(Params[])
         */
        @Override
        protected ArrayList<Reservation> doInBackground(String... params) {
                ArrayList<Reservation> events = new ArrayList<Reservation>();
                try {
                        events = readCalendarEvents();
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                } catch (ServiceException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                return events;
        }
        
        /* (non-Javadoc)
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(ArrayList<Reservation> events){
                super.onPostExecute(events);
                this.mTaskCompletedCallback.onTaskCompleted(events);
        }
}