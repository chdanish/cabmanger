package com.RestSecureOath.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="REFUEL")
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
	@JsonBackReference
	private Vehicle vehicle;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonManagedReference
	private Activity activity;
	
	@ManyToOne
	@JsonManagedReference
	private Driver driver;

	public Refuel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param vehicle
	 * @param activity
	 * @param driver
	 */
	public Refuel( Activity activity) {
		super();
		this.vehicle = activity.getVehicle();
		this.activity = activity;
		this.driver = activity.getDriver();
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
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Refuel [refuelId=" + refuelId + ", driver=" + driver + "]";
	}
	
	

}
