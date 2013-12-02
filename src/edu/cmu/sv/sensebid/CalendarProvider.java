package edu.cmu.sv.sensebid;

import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Instances;
import android.text.format.DateUtils;

public class CalendarProvider {
	public ArrayList<Reservation> readCalendarEvents(String userName, String password) throws IOException, ServiceException
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
		myService.setUserCredentials(userName, password);

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
		return events;
	}
}
