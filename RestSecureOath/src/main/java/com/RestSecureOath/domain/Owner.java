/**
 * 
 */
package com.RestSecureOath.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author chdanish
 *
 */
@Entity
@Table(name = "owner")
public class Owner extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -104057775454508310L;
	
	@Column(name="compid")
	Long compID;

	/**
	 * 
	 */
	public Owner() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param user
	 */
	public Owner(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userName
	 * @param password
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param roles
	 * @param enabled
	 * @param company
	 */
	public Owner(String userName, String password, String email, String firstName, String lastName,
			int enabled, Company company,Groups groups) {
		super(userName, password, email, firstName, lastName, Roles.OWNER, enabled, company,groups);
		this.compID=company.getCompanyId();
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
	 * @see java.lang.Object#toString()this.
	 */
	@Override
	public String toString() {
		return "User [userId=" + this.getUserId() + ", createdAT=" + this.getCreatedAT() + ", userName=" + this.getUserName() + ", password="
				+ this.getPassword() + ", email=" + this.getEmail() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", roles="
				+ this.getRoles() + ", enabled=" + this.getEnabled() + ", company=" + getCompany() + "]"
				;
	}



}
