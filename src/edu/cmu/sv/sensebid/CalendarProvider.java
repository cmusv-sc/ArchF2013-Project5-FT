package edu.cmu.sv.sensebid;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;

public class CalendarProvider {
	public static ArrayList<String> nameOfEvent = new ArrayList<String>();
	public static ArrayList<String> startDates = new ArrayList<String>();
	public static ArrayList<String> endDates = new ArrayList<String>();
	public static ArrayList<String> descriptions = new ArrayList<String>();

	public static ArrayList<Reservation> readCalendarEvents(Context context) {
		String uri = "content://com.android.calendar/events"; // Check for updated APIs
		Uri CALENDAR_URI = Uri.parse(uri);
		
		ArrayList<Reservation> reservationsList = new ArrayList<Reservation>();
        
	    Cursor cursor = context.getContentResolver().query(CALENDAR_URI, new String[] { "calendar_id", "title", "description",
	                            "dtstart", "dtend", "eventLocation" }, null, null, null);
	    cursor.moveToFirst();

	    Reservation reservation;
	    for (int i = 0; i < cursor.getCount(); i++) {
	    	
            Date eventDate = new Date(cursor.getLong(3));
            String appDate = new SimpleDateFormat("yyyyMMdd").format(eventDate);
            
            Calendar eventCalendar = Calendar.getInstance();
            eventCalendar.setTime(eventDate);
            
            //if(appDate.equals(todayDate) || appDate.equals(tomorrowDate)) {
            if(isTodayEventAndHalfAnHourLaterThanNow(eventCalendar) == true || isTomorrowEvent(eventCalendar) == true) {
            	reservation = new Reservation();
                reservation.setDescription(cursor.getString(1));
                
                //Does this work with recurring apps?
                reservation.setStartDate(new Date(cursor.getLong(3)));
                reservation.setEndDate(new Date(cursor.getLong(4)));
                reservation.setLocation(cursor.getString(5));
                reservationsList.add(reservation);
            }
            cursor.moveToNext();
	}
        return reservationsList;
	}

	private static boolean isTodayEventAndHalfAnHourLaterThanNow(Calendar eventCalendar){
		// Get today date. 
		Calendar todayCalendar = Calendar.getInstance();
		Date today = todayCalendar.getTime();
		Date event = eventCalendar.getTime();
		
		if(today.getTime() > event.getTime())
		{
			return false;
		}
		else
		{
			long minutesBetweenEvents = (int) (((event.getTime() - today.getTime()) / 1000) / 60);
			
			//Adding 30 minutes to event so user is able to bid for temperature
			eventCalendar.add(Calendar.MINUTE, 30);
			
			
			if(eventCalendar.get(Calendar.YEAR) == todayCalendar.get(Calendar.YEAR) && 
					eventCalendar.get(Calendar.MONTH) == todayCalendar.get(Calendar.MONTH) &&
					eventCalendar.get(Calendar.DAY_OF_MONTH) == todayCalendar.get(Calendar.DAY_OF_MONTH) &&
					minutesBetweenEvents >= 30){
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	private static boolean isTomorrowEvent(Calendar eventCalendar){
		// Get today date. 
		Calendar todayCalendar = Calendar.getInstance();
		Date today = todayCalendar.getTime();
        
        //Get tomorrow
		Calendar tomorrowCalendar = Calendar.getInstance();
		tomorrowCalendar.setTime(today);  
		tomorrowCalendar.add(Calendar.DAY_OF_YEAR, 1);  
		
		if(eventCalendar.get(Calendar.YEAR) == tomorrowCalendar.get(Calendar.YEAR) && 
				eventCalendar.get(Calendar.MONTH) == tomorrowCalendar.get(Calendar.MONTH) &&
				eventCalendar.get(Calendar.DAY_OF_MONTH) == tomorrowCalendar.get(Calendar.DAY_OF_MONTH)){
			return true;
		}
		else
		{
			return false;
		}
	}
}
