/**
 * 
 */
package com.RestSecureOath.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author chdanish
 *
 */
@Entity
@Table(name = "driver")
public class Driver extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6594919893586156345L;

	@Column(name="nationalid")
	private String nationalID;
	
	@Column(name="nationalid_expiry")
	private Date nationalID_expiry;
	
	@Column(name="nationalid_snap")
	private byte[] nationalID_snap;
	
	@Column(name="licenseid")
	private String licenseID;
	
	@Column(name="licenseid_snap")
	private byte[] licenseID_snap;
	
	@Column(name="licenseid_expiry")
	private Date licenseID_expiry; 
		

	@OneToMany(mappedBy="activityID",targetEntity=Activity.class,orphanRemoval=true)
	private Set<Activity> activity= new HashSet<Activity>(0);
	
	@Column(name="refuel")
	private Refuel refuel;
	
	/**
	 * 
	 */
	public Driver() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param user
	 */
	public Driver(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userName
	 * @param password
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param enabled
	 * 
	 * @param nationalID
	 * @param nationalID_expiry
	 * @param nationalID_snap
	 * @param licenseID
	 * @param licenseID_snap
	 * @param licenseID_expiry
	 * Hard coded role
	 */
	public Driver( String userName, String password, String email, String firstName,
			String lastName, int enabled, Company company,Groups groups) {
		super( userName, password, email, firstName, lastName, Roles.DRIVER, enabled, company,groups);
	}
	
	
	

	/**
	 * @param nationalID
	 * @param nationalID_expiry
	 * @param nationalID_snap
	 * @param licenseID
	 * @param licenseID_snap
	 * @param licenseID_expiry
	 */


	/**
	 * @return the nationalID
	 */
	public String getNationalID() {
		return nationalID;
	}

	/**
	 * @param nationalID the nationalID to set
	 */
	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	/**
	 * @return the nationalID_expiry
	 */
	public Date getNationalID_expiry() {
		return nationalID_expiry;
	}

	/**
	 * @param nationalID_expiry the nationalID_expiry to set
	 */
	public void setNationalID_expiry(Date nationalID_expiry) {
		this.nationalID_expiry = nationalID_expiry;
	}

	/**
	 * @return the nationalID_snap
	 */
	public byte[] getNationalID_snap() {
		return nationalID_snap;
	}

	/**
	 * @param nationalID_snap the nationalID_snap to set
	 */
	public void setNationalID_snap(byte[] nationalID_snap) {
		this.nationalID_snap = nationalID_snap;
	}

	/**
	 * @return the licenseID
	 */
	public String getLicenseID() {
		return licenseID;
	}

	/**
	 * @param licenseID the licenseID to set
	 */
	public void setLicenseID(String licenseID) {
		this.licenseID = licenseID;
	}

	/**
	 * @return the licenseID_snap
	 */
	public byte[] getLicenseID_snap() {
		return licenseID_snap;
	}

	/**
	 * @param licenseID_snap the licenseID_snap to set
	 */
	public void setLicenseID_snap(byte[] licenseID_snap) {
		this.licenseID_snap = licenseID_snap;
	}

	/**
	 * @return the licenseID_expiry
	 */
	public Date getLicenseID_expiry() {
		return licenseID_expiry;
	}

	/**
	 * @param licenseID_expiry the licenseID_expiry to set
	 */
	public void setLicenseID_expiry(Date licenseID_expiry) {
		this.licenseID_expiry = licenseID_expiry;
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


	


	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Driver [NationalID=" + nationalID + ", NationalID_expiry=" + nationalID_expiry + ", NationalID_snap="
				+ Arrays.toString(nationalID_snap) + ", LicenseID=" + licenseID + ", LicenseID_snap="
				+ Arrays.toString(licenseID_snap) + ", LicenseID_expiry=" + licenseID_expiry + ", activity=" + activity
				+ ", refuel=" + refuel + "]";
	}
}
