package edu.cmu.sv.sensebid;
import java.util.Date;


public class Reservation {
	private String description;
	private Date startDate;
	private Date endDate;
	private String location;
	
	public Reservation(){}
	
	public Reservation(String pDescription, Date pStartDate, Date pEndDate, String pLocation){
		setDescription(pDescription);
		setStartDate(pStartDate);
		setEndDate(pEndDate);
		setLocation(pLocation);
	}

	private String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
