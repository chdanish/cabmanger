package com.RestSecureOath.requestdto;

import java.io.Serializable;

public class EndactivityDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long endReading;
	String endReading_snap;
	long activityID;
	/**
	 * @return the endReading
	 */
	public long getEndReading() {
		return endReading;
	}
	/**
	 * @param endReading the endReading to set
	 */
	public void setEndReading(long endReading) {
		this.endReading = endReading;
	}
	/**
	 * @return the endReading_snap
	 */
	public String getEndReading_snap() {
		return endReading_snap;
	}
	/**
	 * @param endReading_snap the endReading_snap to set
	 */
	public void setEndReading_snap(String endReading_snap) {
		this.endReading_snap = endReading_snap;
	}
	/**
	 * @return the activityID
	 */
	public long getActivityID() {
		return activityID;
	}
	/**
	 * @param activityID the activityID to set
	 */
	public void setActivityID(long activityID) {
		this.activityID = activityID;
	}
	
	
	
	

}
