
package com.RestSecureOath.client.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author chdanish
 *
 */
public class LDriver extends JavaScriptObject { 
	
	private Long userId;
	
	private Date createdAT;
	
	private String userName;

	private String password;

	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String snap;
	
	private Set<Object> roles;

	private int enabled;
	
	private Object groups;
	
	private Object company;

	private String nationalID;
	
	private Date nationalID_expiry;
	
	private byte[] nationalID_snap;
	
	private String licenseID;
	
	private byte[] licenseID_snap;
	
	private Date licenseID_expiry; 
		
	private Set<Object> activity;
	
	private Set<Object> refuel ;
	
	/**
	 * 
	 */
	public LDriver() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the userId
	 */
	public final native Long getUserId() /*-{
		return this.userId;
	}-*/;

	/**
	 * @return the createdAT
	 */
	public final native Date getCreatedAT() /*-{
		return this.createdAT;
	}-*/;

	/**
	 * @return the userName
	 */
	public final native String getUserName() /*-{
		return this.userName;
	}-*/;

	/**
	 * @return the password
	 */
	public final native String getPassword() /*-{
		return this.password;
	}-*/;
	
	/**
	 * @return the email
	 */
	public final native String getEmail() /*-{
		return this.email;
	}-*/;

	/**
	 * @return the firstName
	 */
	public final native String getFirstName() /*-{
		return this.firstName;
	}-*/;

	/**
	 * @return the lastName
	 */
	public final native String getLastName() /*-{
		return this.lastName;
	}-*/;

	/**
	 * @return the snap
	 */
	public final native String getSnap() /*-{
		return this.snap;
	}-*/;

	/**
	 * @return the roles
	 */
	public final native Set<Object> getRoles() /*-{
		return this.roles;
	}-*/;

	/**
	 * @return the enabled
	 */
	public final native int getEnabled() /*-{
		return this.enabled;
	}-*/;

	/**
	 * @return the groups
	 */
	public final native Object getGroups() /*-{
		return this.groups;
	}-*/;

	/**
	 * @return the company
	 */
	public final native Object getCompany() /*-{
		return this.company;
	}-*/;

	/**
	 * @return the nationalID
	 */
	public final native String getNationalID() /*-{
		return this.nationalID;
	}-*/;

	/**
	 * @return the nationalID_expiry
	 */
	public final native Date getNationalID_expiry() /*-{
		return this.nationalID_expiry;
	}-*/;

	/**
	 * @return the nationalID_snap
	 */
	final native byte[] getNationalID_snap() /*-{
		return this.nationalID_snap;
	}-*/;

	/**
	 * @return the licenseID
	 */
	final native String getLicenseID() /*-{
		return this.licenseID;
	}-*/;

	/**
	 * @return the licenseID_snap
	 */
	final native byte[] getLicenseID_snap() /*-{
		return this.licenseID_snap;
	}-*/;

	/**
	 * @return the licenseID_expiry
	 */
	 final native Date getLicenseID_expiry() /*-{
		return this.licenseID_expiry;
	}-*/;

	/**
	 * @return the activity
	 */
	 final native Set<Object> getActivity() /*-{
		return this.activity;
	}-*/;

	/**
	 * @return the refuel5
	 */
	 final native Set<Object> getRefuel() /*-{
		return this.refuel;
	}-*/;
	
	

	
}
