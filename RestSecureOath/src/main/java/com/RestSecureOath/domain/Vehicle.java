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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VEHICLE")
public class Vehicle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4669765684568210601L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vehicleid")
	private Long vehicleId;	
	
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAT;
	
	@Column(name="make")
	private String make;
	
	@Column(name="model")
	private long model;
	
	@ManyToOne
	@JoinColumn(name="companyid", nullable = false)
	private Company company;
	
	@OneToMany(mappedBy="activityID",targetEntity=Activity.class)
	private Set<Activity> activity= new HashSet<Activity>(0);
	
	@OneToMany(mappedBy="refuelId",targetEntity=Refuel.class)
	private Set<Refuel> ride = new HashSet<Refuel>(0);

	/**
	 * Default
	 */
	public Vehicle() {
		super();
	}

	/**
	 * @param make
	 * @param model
	 * @param company
	 */
	public Vehicle(String make, Long model, Company company) {
		super();
		this.make = make;
		this.model = model;
		this.company = company;
		this.createdAT= new Date();
	}

	/**
	 * @return the vehicleId
	 */
	public Long getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the model
	 */
	public long getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(long model) {
		this.model = model;
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
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
	 * @return the activity
	 */
	public Set<Activity> getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity.add(activity);
	}
	
	

}
