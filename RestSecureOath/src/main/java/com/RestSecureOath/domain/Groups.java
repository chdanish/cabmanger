package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "GROUPS")
public class Groups implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7693488425022978016L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="groupid")
	private Long groupId;
	
	@Column(name="name",nullable=false,updatable=false)
	private String name;
	
	@Column(name ="enabled")
	private int enabled;
	
	@ManyToOne
	@JoinColumn(name="companyid", nullable = false)
	@JsonBackReference
	private Company company;
	
	@OneToMany(mappedBy="groups",orphanRemoval=true)
	@JsonManagedReference
	private Set<User> user = new HashSet<User>(0);
	
	@OneToMany(mappedBy="groups",targetEntity=Vehicle.class,orphanRemoval=true)
	@JsonManagedReference
	private Set<Vehicle> vehicle = new HashSet<Vehicle>(0);
	
	/**
	 * @param name
	 * @param company
	 */
	public Groups() {
		super();
	}

	/**
	 * @param name
	 * @param company
	 */
	public Groups(String name, Company company,int enabled) {
		super();
		this.name = name;
		this.company = company;
		this.enabled= enabled;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	 * @return the user
	 */
	public Set<User> getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Set<User> user) {
		this.user = user;
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
	public void setVehicle(Set<Vehicle> vehicle) {
		this.vehicle = vehicle;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Groups [groupId=" + groupId + ", name=" + name + ", company=" + company + ", user=" + user
				+ ", vehicle=" + vehicle + "]";
	}	
}
