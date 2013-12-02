package edu.cmu.sv.sensebid;


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
import com.google.gson.JsonObject;



public class CalendarProvider extends AsyncTask<String, Void, ArrayList<Reservation>>{
	private AsyncTaskCompleteListener<ArrayList<Reservation>> mTaskCompletedCallback;
	private String user = "";
	private String pwd = "";
	
	public CalendarProvider(AsyncTaskCompleteListener<ArrayList<Reservation>> listener, String usr, String pwd){
		this.mTaskCompletedCallback = listener;
		this.user = usr;
		this.pwd = pwd;
	}
	
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
			  public int compare(Reservation o1, Reservation o2) {
			      if (o1.getStartDate() == null || o2.getStartDate() == null)
			        return 0;
			      return o1.getStartDate().compareTo(o2.getStartDate());
			  }
			});
		
		return events;
	}

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
	
	@Override
	protected void onPostExecute(ArrayList<Reservation> events){
		super.onPostExecute(events);
		this.mTaskCompletedCallback.onTaskCompleted(events);
	}
}
