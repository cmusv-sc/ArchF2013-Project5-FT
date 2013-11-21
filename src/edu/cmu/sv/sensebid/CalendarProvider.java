package edu.cmu.sv.sensebid;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
		
		// Get today date. Pending to add tomorrow
		Date today = new Date();
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(today);
        
        
	    Cursor cursor = context.getContentResolver().query(CALENDAR_URI, new String[] { "calendar_id", "title", "description",
	                            "dtstart", "dtend", "eventLocation" }, null, null, null);
	    cursor.moveToFirst();
	    
	    
	    
	    //String[] CalNames = new String[cursor.getCount()];
        //int[] CalIds = new int[cursor.getCount()];
        //for (int i = 0; i < CalNames.length; i++) {
	    Reservation reservation;
	    for (int i = 0; i < cursor.getCount(); i++) {
	    	
            Date mDate = new Date(cursor.getLong(3));
            String appDate = new SimpleDateFormat("yyyyMMdd").format(mDate);
//            long mTime = mDate.getTime();
//            long lTime = nDate.getTime();
            
            if(appDate.equals(todayDate) ) { 
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

	public static String getDate(long milliSeconds) {
	    SimpleDateFormat formatter = new SimpleDateFormat(
	            "dd/MM/yyyy hh:mm:ss a");
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(milliSeconds);
	    return formatter.format(calendar.getTime());
	}
}
