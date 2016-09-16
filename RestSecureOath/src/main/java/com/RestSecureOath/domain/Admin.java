/**
 * 
 */
package com.RestSecureOath.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author chdanish
 *
 */
@Entity
@Table(name = "admin")
public class Admin extends User {
	
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
	
	@Column(name="compid")
	Long compID;
	
	/**
	 * 
	 */
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param user
	 */
	public Admin(User user) {
		super(user);
		this.compID=user.company.getCompanyId();
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
	 * Hard coded role
	 * @param snap 
	 */
	public Admin( String userName, String password, String email, String firstName,
			String lastName, int enabled, Company company, String snap,String nationalID, Date nationalID_expiry, byte[] nationalID_snap) {
		super( userName, password, email, firstName, lastName, Roles.ADMIN, enabled, company,snap);
		this.nationalID = nationalID;
		this.nationalID_expiry = nationalID_expiry;
		this.nationalID_snap = nationalID_snap;
		this.compID=company.getCompanyId();
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
	 * @return the compID
	 */
	public Long getCompID() {
		return compID;
	}

	/**
	 * @param compID the compID to set
	 */
	public void setCompID(Long compID) {
		this.compID = compID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin [nationalID=" + nationalID + ", nationalID_expiry=" + nationalID_expiry + ", nationalID_snap="
				+ Arrays.toString(nationalID_snap) + ", compID=" + compID + "]";
	}
	
}
