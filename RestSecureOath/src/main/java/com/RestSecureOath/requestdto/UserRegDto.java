package com.RestSecureOath.requestdto;

public class UserRegDto {
	

	private String userName;

	private String password;

	private String email;
	
	private String firstName;
	
	private String lastName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserRegDto [userName=" + userName + ", password=" + password + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
	

}
