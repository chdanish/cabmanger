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
	@JsonBackReference
	private Activity activity;
	
	@ManyToOne
	@JsonBackReference
	private Driver driver;
	
	@Column(name="volume")
	private float volume;
	
	@Column(name="cost")
	private float cost;
	
	@Column(name="rate")
	private float rate;
	

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
	
	public Refuel( Activity activity,Float volume,Float cost,Float rate) {
		super();
		this.activity = activity;
		this.vehicle = activity.getVehicle();		
		this.driver = activity.getDriver();
		
		if(volume==null&&cost!=null&&rate!=null){
			this.volume	= cost/rate;
			this.cost 	= cost;
			this.rate	= rate;
			
		}else if(volume!=null&&cost==null&&rate!=null){
			this.volume	= volume;
			this.cost 	= volume*rate;
			this.rate	= rate;
			
		}else if(volume!=null&&cost!=null&&rate==null){
			this.volume	= volume;
			this.cost 	= cost;
			this.rate	= cost/volume;
			
		}else if(volume!=null&&cost!=null&&rate!=null){
			this.volume	= volume;
			this.cost 	= cost;
			this.rate	= rate;
			
		}
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


}
