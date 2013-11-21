package edu.cmu.sv.sensebid;
import java.util.Date;


public class Reservation {
	private String description;
	private Date startDate;
	private Date endDate;
	private String location;
	
	public Reservation(String pDescription, Date pStartDate, Date pEndDate, String pLocation){
		setDescription(pDescription);
		setStartDate(pStartDate);
		setEndDate(pEndDate);
		setLocation(pLocation);
	}

	private String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	private Date getStartDate() {
		return startDate;
	}

	private void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	private Date getEndDate() {
		return endDate;
	}

	private void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	private String getLocation() {
		return location;
	}

	private void setLocation(String location) {
		this.location = location;
	}
	
	
}
