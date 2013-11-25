package edu.cmu.sv.sensebid;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class Reservation implements Parcelable{
	private String description;
	private Date startDate;
	private Date endDate;
	private String location;

	public Reservation() {
	}

	public Reservation(String pDescription, Date pStartDate, Date pEndDate,
			String pLocation) {
		setDescription(pDescription);
		setStartDate(pStartDate);
		setEndDate(pEndDate);
		setLocation(pLocation);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return ("Description:" + "\n" + this.getDescription() + "\n"
				+ "Start Date:" + "\n" + this.getStartDate() + "\n"
				+ "End Date:" + "\n" + this.getEndDate() + "\n"
				+ "Location :" + "\n" + this.getLocation());
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

}
