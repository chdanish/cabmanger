package com.RestSecureOath.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="REFUEL",uniqueConstraints = @UniqueConstraint(
		columnNames = { "vehicleid", "activityid","userid" }))
public class Refuel implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5943559671165709506L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="refuelid")
	private long refuelId;
	
	@ManyToOne
	@JoinColumn(name="vehicleid", nullable = false)
	private Vehicle vehicle;
	
	@OneToOne
	@JoinColumn(name="activityid", nullable = false)
	private Activity activity;
	
	@OneToOne
	@JoinColumn(name="userid", nullable = false)
	private Driver driver;

	public Refuel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param vehicle
	 * @param activity
	 * @param driver
	 */
	public Refuel(Vehicle vehicle, Activity activity, Driver driver) {
		super();
		this.vehicle = vehicle;
		this.activity = activity;
		this.driver = driver;
	}




	/**
	 * @return the refuelId
	 */
	public long getRefuelId() {
		return refuelId;
	}

	/**
	 * @param refuelId the refuelId to set
	 */
	public void setRefuelId(long refuelId) {
		this.refuelId = refuelId;
	}

	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	

}
