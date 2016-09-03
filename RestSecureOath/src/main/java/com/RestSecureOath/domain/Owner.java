/**
 * 
 */
package com.RestSecureOath.domain;

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
			int enabled, Company company) {
		super(userName, password, email, firstName, lastName, Roles.OWNER, enabled, company);
	}



}
