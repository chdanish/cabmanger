package com.RestSecureOath.requestdto;

import javax.persistence.Column;

public class RefuelDto {
	
	private long activityID;	

	private float volume;
	
	private float cost;

	private float rate;
	
	
	

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

	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}

	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}

	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RefuelDto [activityID=" + activityID + ", volume=" + volume + ", cost=" + cost + ", rate=" + rate + "]";
	}
	
	

}
