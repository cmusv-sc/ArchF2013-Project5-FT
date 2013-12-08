package edu.cmu.sv.sensebid;


import java.util.Date;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.java.edu.cmu.sv.sdsp.senseBid.AsyncTaskCompleteListener;
import main.java.edu.cmu.sv.sdsp.senseBid.Reservation;
import android.os.AsyncTask;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.google.gson.JsonObject;



public class CalendarProvider extends AsyncTask<String, Void, ArrayList<Reservation>>{
	private AsyncTaskCompleteListener<ArrayList<Reservation>> mTaskCompletedCallback;
	private String user = "";
	private String pwd = "";
	private Calendar calendar;
	private CalendarQuery myQuery;
	private CalendarService mCalendarService;
	
	public CalendarProvider(){
		
	}
	
	public CalendarProvider(AsyncTaskCompleteListener<ArrayList<Reservation>> listener, String usr, String pwd) throws MalformedURLException{
		this.mTaskCompletedCallback = listener;
		this.user = usr;
		this.pwd = pwd;
		this.calendar = Calendar.getInstance();
		
		URL feedUrl = new URL("https://www.google.com/calendar/feeds/default/private/full");
		this.myQuery = new CalendarQuery(feedUrl);
		this.mCalendarService = new CalendarService("exampleCo-exampleApp-1");
	}
	
	public void setCalendar (Calendar c){
		this.calendar = c;
	}
	
	public void setCalendarQuery(CalendarQuery query){
		this.myQuery = query;
	}
	
	public void setCalendarService (CalendarService calendarService){
		this.mCalendarService = calendarService;
	}
	
	public CalendarEventFeed getEvents(String user, String pwd) throws IOException, ServiceException{
		this.mCalendarService.setUserCredentials(user, pwd);

		// Send the request and receive the response:
		CalendarEventFeed resultFeed = this.mCalendarService.query(myQuery, CalendarEventFeed.class);
		return resultFeed;
	}
	
	
	public ArrayList<Reservation> readCalendarEvents() throws IOException, ServiceException
	{
		ArrayList<Reservation> events = new ArrayList<Reservation>();
		
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 30);
		Date today = calendar.getTime();
		
		calendar.setTime(today);  
		calendar.add(Calendar.DAY_OF_YEAR, 1);  
		
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date tomorrow = calendar.getTime();
		
		myQuery.setMinimumStartTime(new DateTime(today));
		myQuery.setMaximumStartTime(new DateTime(tomorrow));
 
		
		Reservation reservation = null;
		CalendarEventFeed resultFeed = getEvents(this.user, this.pwd);
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
