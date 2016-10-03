package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.RestSecureOath.util.CustomVehicleSerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Entity
@Table(name="ACTIVITY")
public class Activity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1920421919050050153L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="activityid")
	private long activityID;
	
	@ManyToOne(optional=false)
	private Driver driver;
	
	@ManyToOne(targetEntity=Vehicle.class,optional=false)
	@JsonSerialize(using=CustomVehicleSerializer.class)
	private Vehicle vehicle;
	
	@OneToMany(mappedBy="activity",targetEntity=Ride.class,orphanRemoval=true)
	@JsonManagedReference
	private Set<Ride> ride = new HashSet<Ride>(0);
	
	@Column(name="validity")
	private Date validity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at",updatable=false,nullable=false)
	private Date createdAT;
	
	@Column(name="start_reading",nullable=false)
	private long startReading;
	
	@Column(name="start_reading_snap",length=20000,nullable=false)
	private String startReading_snap;
	
	@Column(name="ended_at")
	private Date endedAT;
	
	@Column(name="end_reading")
	private long endReading;
	
	@Column(name="endreading_snap",length=20000)
	private String endReading_snap;
	
	@Column(name="refuel_litre")
	private long refuel_litre;
	
	@Column(name="refuel")
	private Refuel refuel;
	
	public Activity() {
	}

	public Activity(Driver driver,Vehicle vehicle,long startReading,String startReading_snap) {
		this.createdAT= new Date();
		this.driver=driver;
		this.vehicle=vehicle;
		this.startReading=startReading;
		this.startReading_snap=startReading_snap;
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
	 * @return the ride
	 */
	public Set<Ride> getRide() {
		return ride;
	}

	/**
	 * @param ride the ride to set
	 */
	public void setRide(Ride ride) {
		this.ride.add(ride);
	}

	/**
	 * @return the validity
	 */
	public Date getValidity() {
		return validity;
	}

	/**
	 * @param validity the validity to set
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
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
	 * @return the endedAT
	 */
	public Date getEndedAT() {
		return endedAT;
	}

	/**
	 * @param endedAT the endedAT to set
	 */
	public void setEndedAT(Date endedAT) {
		this.endedAT = endedAT;
	}

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
	 * @return the refuel_litre
	 */
	public long getRefuel_litre() {
		return refuel_litre;
	}

	/**
	 * @param refuel_litre the refuel_litre to set
	 */
	public void setRefuel_litre(long refuel_litre) {
		this.refuel_litre = refuel_litre;
	}

	/**
	 * @return the refuel
	 */
	public Refuel getRefuel() {
		return refuel;
	}

	/**
	 * @param refuel the refuel to set
	 */
	public void setRefuel(Refuel refuel) {
		this.refuel = refuel;
	}

	/**
	 * @param ride the ride to set
	 */
	public void setRide(Set<Ride> ride) {
		this.ride = ride;
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
	
	

}
