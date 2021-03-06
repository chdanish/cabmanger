/**
 * 
 */
package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.RestSecureOath.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author chdanish
 *
 */
@Entity
@Table(name="COMPANY")
public class Company implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4994825258696683737L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="companyid")
	Long companyId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at",updatable=false,nullable=false)
	@JsonSerialize(using=CustomDateSerializer.class)
	private Date createdAT;
	
	@Column(name="companyname")
	private String name;
	
	@Column(name="company_registration")
	private String registration;
	
	@Column(name = "distance_unit")
	@Enumerated(EnumType.STRING)
	private Distance_Unit distanceunit;
	
	@Column(name = "fuel_unit")
	@Enumerated(EnumType.STRING)
	private Fuel_Unit fuelunit;
	
	@OneToMany(mappedBy="company",orphanRemoval=true)
	@JsonManagedReference
	private Set<User> user = new HashSet<User>(0);
	
	@OneToMany(mappedBy="company",targetEntity=Vehicle.class,orphanRemoval=true)
	@JsonManagedReference
	private Set<Vehicle> vehicle = new HashSet<Vehicle>(0);

	@OneToMany(mappedBy="company",orphanRemoval=true,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Groups> groups = new HashSet<Groups>(0);

	/**
	 * @param createdAT
	 */
	public Company() {
		super();
		this.createdAT = new Date();
	}


	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the regisration
	 */
	public String getRegisration() {
		return registration;
	}

	/**
	 * @param regisration the regisration to set
	 */
	public void setRegisration(String registration) {
		this.registration = registration;
	}

	/**
	 * @return the user
	 */
	public Set<User> getUser() {
		return user;
	}

	/**
	 * @return the vehicle
	 */
	public Set<Vehicle> getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle.add(vehicle);
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user.add(user);
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
	 * @return the distanceunit
	 */
	public Distance_Unit getDistanceunit() {
		return distanceunit;
	}

	/**
	 * @param distanceunit the distanceunit to set
	 */
	public void setDistanceunit(Distance_Unit distanceunit) {
		this.distanceunit = distanceunit;
	}

	/**
	 * @return the fuelunit
	 */
	public Fuel_Unit getFuelunit() {
		return fuelunit;
	}

	/**
	 * @param fuelunit the fuelunit to set
	 */
	public void setFuelunit(Fuel_Unit fuelunit) {
		this.fuelunit = fuelunit;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Set<User> user) {
		this.user = user;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the registration
	 */
	public String getRegistration() {
		return registration;
	}


	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(String registration) {
		this.registration = registration;
	}


	/**
	 * @return the groups
	 */
	public Set<Groups> getGroups() {
		return groups;
	}


	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Set<Groups> groups) {
		this.groups = groups;
	}


	@Override
	public String toString() {
		return "Company [" + (companyId != null ? "CompanyId=" + companyId + ", " : "")
				+ (createdAT != null ? "createdAT=" + createdAT + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (registration != null ? "Regisration=" + registration + ", " : "")
				+ (distanceunit != null ? "distanceunit=" + distanceunit + ", " : "")
				+ (fuelunit != null ? "fuelunit=" + fuelunit + ", " : "") 
				+ (vehicle != null ? "vehicle=" + vehicle : "") + "]";
	}

	


}
