package com.RestSecureOath.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.RestSecureOath.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userid")
	private Long userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at",updatable=false,nullable=false)
	@JsonSerialize(using=CustomDateSerializer.class)
	private Date createdAT;

	@Column(name = "username",unique = true)
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")//https://docs.oracle.com/cd/E19798-01/821-1841/gijst/index.html
	private String email;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name="user_snap",length=8000)
	private String snap;
	
	@ElementCollection(targetClass = Roles.class,fetch = FetchType.EAGER)
	@CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "userid"))
	@Column(name = "roles", nullable = false)
	@Enumerated(EnumType.STRING)
	private Set<Roles> roles= new HashSet<Roles>(0);

	@Column(name ="enabled")
	private int enabled;
	
	@ManyToOne
	@JoinColumn(name="groupid", nullable = false)
	@JsonBackReference
	private Groups groups;
	
	
	@ManyToOne
	@JoinColumn(name="companyid", nullable = false)
	@JsonBackReference
	private Company company;

	public User(){

	}

	/**
	 * @param user
	 */
	public User(User user){
		this.userId=user.getUserId();
		this.password=user.getPassword();
		this.email=user.getEmail();
		this.enabled=user.getEnabled();
		this.userName=user.getUserName();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.roles=user.getRoles();
		this.company=user.getCompany();
		this.createdAT=new Date();
		this.snap=user.getSnap();
	}
	

	/**
	 * @param userName
	 * @param password
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param roles
	 * @param enabled
	 * @param snap 
	 */
	public User( String userName, String password, String email, String firstName, String lastName,
			Roles roles, int enabled,Company company,Groups groups) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles.add(roles);
		this.enabled = enabled;
		this.company=company;
		this.createdAT=new Date();
		this.groups= groups;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles.add(roles); 
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", createdAT=" + createdAT + ", userName=" + userName + ", password="
				+ password + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", roles="
				+ roles + ", enabled=" + enabled + ", company=" + company + "]";
	}

	

	
}