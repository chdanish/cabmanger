package com.RestSecureOath.requestdto;

import java.io.Serializable;

public class DriverDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5836945566435069195L;
	long userId;
	String password;
	String confirmPassword;
	String firstName;
	String lastName;
	String userName;
	String email;
	String nationalID;
	String nationalID_expiry;
	String licenseID;
	String licenseID_expiry;
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
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
	public String getNationalID_expiry() {
		return nationalID_expiry;
	}
	/**
	 * @param nationalID_expiry the nationalID_expiry to set
	 */
	public void setNationalID_expiry(String nationalID_expiry) {
		this.nationalID_expiry = nationalID_expiry;
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
	 * @return the licenseID_expiry
	 */
	public String getLicenseID_expiry() {
		return licenseID_expiry;
	}
	/**
	 * @param licenseID_expiry the licenseID_expiry to set
	 */
	public void setLicenseID_expiry(String licenseID_expiry) {
		this.licenseID_expiry = licenseID_expiry;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DriverDto [userId=" + userId + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", email=" + email
				+ ", nationalID=" + nationalID + ", nationalID_expiry=" + nationalID_expiry + ", licenseID=" + licenseID
				+ ", licenseID_expiry=" + licenseID_expiry + "]";
	}
}
