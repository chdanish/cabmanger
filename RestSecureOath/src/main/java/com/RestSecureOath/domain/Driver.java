/**
 * 
 */
package com.RestSecureOath.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String NationalID;
	
	@Column(name="nationalid_expiry")
	private Date NationalID_expiry;
	
	@Column(name="nationalid_snap")
	private byte[] NationalID_snap;
	
	@Column(name="licenseid")
	private String LicenseID;
	
	@Column(name="licenseid_snap")
	private byte[] LicenseID_snap;
	
	@Column(name="licenseid_expiry")
	private Date LicenseID_expiry; 
	

	@OneToMany(mappedBy="activityID",targetEntity=Activity.class)
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
			String lastName, int enabled, Company company,String nationalID, Date nationalID_expiry, byte[] nationalID_snap, String licenseID,
			byte[] licenseID_snap, Date licenseID_expiry) {
		super( userName, password, email, firstName, lastName, Roles.DRIVER, enabled, company);
		NationalID = nationalID;
		NationalID_expiry = nationalID_expiry;
		NationalID_snap = nationalID_snap;
		LicenseID = licenseID;
		LicenseID_snap = licenseID_snap;
		LicenseID_expiry = licenseID_expiry;
	}
	
	
	

	/**
	 * @param nationalID
	 * @param nationalID_expiry
	 * @param nationalID_snap
	 * @param licenseID
	 * @param licenseID_snap
	 * @param licenseID_expiry
	 */


	public String getNationalID() {
		return NationalID;
	}

	public void setNationalID(String nationalID) {
		NationalID = nationalID;
	}

	public Date getNationalID_expiry() {
		return NationalID_expiry;
	}

	public void setNationalID_expiry(Date nationalID_expiry) {
		NationalID_expiry = nationalID_expiry;
	}

	public byte[] getNationalID_snap() {
		return NationalID_snap;
	}

	public void setNationalID_snap(byte[] nationalID_snap) {
		NationalID_snap = nationalID_snap;
	}

	public String getLicenseID() {
		return LicenseID;
	}

	public void setLicenseID(String licenseID) {
		LicenseID = licenseID;
	}

	public byte[] getLicenseID_snap() {
		return LicenseID_snap;
	}

	public void setLicenseID_snap(byte[] licenseID_snap) {
		LicenseID_snap = licenseID_snap;
	}

	public Date getLicenseID_expiry() {
		return LicenseID_expiry;
	}

	public void setLicenseID_expiry(Date licenseID_expiry) {
		LicenseID_expiry = licenseID_expiry;
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
	 * @param activity the activity to set
	 */
	public void setActivity(Set<Activity> activity) {
		this.activity = activity;
	}
	
	
}
