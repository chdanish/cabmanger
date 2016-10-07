package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.RestSecureOath.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	@JsonSerialize(using=CustomDateSerializer.class)
	private Date createdAT;
	
	@Column(name="make")
	private String make;
	
	@Column(name="modelname")
	private String modelname;
	
	@Column(name="submodel")
	private String submodel;
	
	@Column(name="regnumber",nullable=false)
	private String regnumber;
	
	@Column(name="modelyear")
	private int modelyear;
	
	@Column(name="user_snap",length=8000)
	private String snap;
	
	@ManyToOne
	@JoinColumn(name="groupid", nullable = false)
	@JsonBackReference
	private Groups groups;
	
	@ManyToOne
	@JoinColumn(name="companyid", nullable = false)
	@JsonBackReference
	private Company company;
	
	@OneToMany(mappedBy="vehicle",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<Activity> activity= new HashSet<Activity>(0);
	
	@OneToMany(mappedBy="vehicle",targetEntity=Refuel.class,orphanRemoval=true)
	@JsonManagedReference
	private Set<Refuel> refuel = new HashSet<Refuel>(0);

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
	public Vehicle(String make, String modelname, Company company,
			Groups groups,String regnumber) {
		super();
		this.make = make;
		this.modelname = modelname;
		this.company = company;
		this.createdAT= new Date();
		this.groups=groups;
		this.regnumber=regnumber;
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
	 * @return the modelname
	 */
	public String getModelname() {
		return modelname;
	}

	/**
	 * @param modelname the modelname to set
	 */
	public void setModelname(String modelname) {
		this.modelname = modelname;
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
	 * @return the snap
	 */
	public String getSnap() {
		return snap;
	}

	/**
	 * @param snap the snap to set
	 */
	public void setSnap(String snap) {
		this.snap = snap;
	}
	
	

	/**
	 * @return the submodel
	 */
	public String getSubmodel() {
		return submodel;
	}

	/**
	 * @param submodel the submodel to set
	 */
	public void setSubmodel(String submodel) {
		this.submodel = submodel;
	}

	/**
	 * @return the regnumber
	 */
	public String getRegnumber() {
		return regnumber;
	}

	/**
	 * @param regnumber the regnumber to set
	 */
	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}

	/**
	 * @return the modelyear
	 */
	public int getModelyear() {
		return modelyear;
	}

	/**
	 * @param modelyear the modelyear to set
	 */
	public void setModelyear(int modelyear) {
		this.modelyear = modelyear;
	}

	
	/**
	 * @return the groups
	 */
	public Groups getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	/**
	 * @return the refuel
	 */
	public Set<Refuel> getRefuel() {
		return refuel;
	}

	/**
	 * @param refuel the refuel to set
	 */
	public void setRefuel(Set<Refuel> refuel) {
		this.refuel = refuel;
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
	public void setActivity(Set<Activity> activity) {
		this.activity = activity;
	}

	@Transient
	public boolean isNew() {
	    return (this.vehicleId == null);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", createdAT=" + createdAT + ", make=" + make + ", model=" + modelname
				+ ", activity=" + "" + ", ride=" + refuel + "]";
	}

	
	
	

}
