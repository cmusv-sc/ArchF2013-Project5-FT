package main.java.edu.cmu.sv.sdsp.senseBid;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable{
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
		return ("Description:" + "\n" + this.getDescription() + ".\n"
				+ "Start Date:" + "\n" + this.getStartDate() + ".\n"
				+ "End Date:" + "\n" + this.getEndDate() + ".\n"
				+ "Location:" + "\n" + this.getLocation() + ".\n");
	}


	
	/*public static final Parcelable.Creator CREATOR = new Parcelable.Creator() 
	{ public Reservation createFromParcel(Parcel in) 
	{ return new Reservation(); }  
	public Reservation[] newArray(int size) { return new Reservation[size]; 
	} 
	}; */

}
