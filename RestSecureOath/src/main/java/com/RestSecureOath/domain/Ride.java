package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="RIDE")
public class Ride implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1873531694977143244L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rideid")
	private long rideID;
	
	@ManyToOne
	@JoinColumn(name="activityid", nullable = false)
	@JsonBackReference
	private Activity activity;
	
	private long fare;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at",updatable=false,nullable=false)
	private Date createdAT;	
	
	public Ride() {
	}

	public Ride(Activity activity,long fare) {
		this.activity=activity;
		this.fare=fare;
		this.createdAT=new Date();
	}

	/**
	 * @return the rideID
	 */
	public long getRideID() {
		return rideID;
	}

	/**
	 * @param rideID the rideID to set
	 */
	public void setRideID(long rideID) {
		this.rideID = rideID;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the fare
	 */
	public long getFare() {
		return fare;
	}

	/**
	 * @param fare the fare to set
	 */
	public void setFare(long fare) {
		this.fare = fare;
	}

	/**
	 * @return the createdAT
	 */
	public Date getCreatedAT() {
		return createdAT;
	}

	/**
	 * @param createdAT the createdAT to set
	 */
	public void setCreatedAT(Date createdAT) {
		this.createdAT = createdAT;
	}

}
