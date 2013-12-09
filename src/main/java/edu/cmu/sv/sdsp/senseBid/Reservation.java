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

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Reservation.
 * Provides with setter and getter methods for all the calendar parameters like
 * description, location, start and end dates.
 */
public class Reservation implements Serializable{
	
	/** The description. */
	private String description;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The location. */
	private String location;

	/**
	 * Instantiates a new reservation.
	 */
	public Reservation() {
	}

	/**
	 * Instantiates a new reservation.
	 *
	 * @param pDescription the description
	 * @param pStartDate the start date
	 * @param pEndDate the end date
	 * @param pLocation the location
	 */
	public Reservation(String pDescription, Date pStartDate, Date pEndDate,
			String pLocation) {
		setDescription(pDescription);
		setStartDate(pStartDate);
		setEndDate(pEndDate);
		setLocation(pLocation);
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
