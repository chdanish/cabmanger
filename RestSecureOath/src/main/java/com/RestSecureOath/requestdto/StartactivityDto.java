package com.RestSecureOath.requestdto;

import java.io.Serializable;

public class StartactivityDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	long startReading;
	String startReading_snap;
	long vehicleID;
	
	
	/**
	 * @return the startReading
	 */
	public long getStartReading() {
		return startReading;
	}
	/**
	 * @param startReading the startReading to set
	 */
	public void setStartReading(long startReading) {
		this.startReading = startReading;
	}
	/**
	 * @return the startReading_snap
	 */
	public String getStartReading_snap() {
		return startReading_snap;
	}
	/**
	 * @param startReading_snap the startReading_snap to set
	 */
	public void setStartReading_snap(String startReading_snap) {
		this.startReading_snap = startReading_snap;
	}
	/**
	 * @return the vehicleID
	 */
	public long getVehicleID() {
		return vehicleID;
	}
	/**
	 * @param vehicleID the vehicleID to set
	 */
	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}
	

}
