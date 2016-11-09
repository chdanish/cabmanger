package com.RestSecureOath.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.RestSecureOath.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="DASHBOARD")
public class Dashboard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dashboardid")
	private Long dashboardid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at",updatable=false,nullable=false)
	@JsonSerialize(using=CustomDateSerializer.class)
	private Date createdAT;
	
	@Column(name="name",updatable=false)
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name="userid", nullable = false)
	@JsonBackReference
	private User user;
	
	@OneToMany(mappedBy="dashboard",orphanRemoval=true)
	@JsonManagedReference
	private Set<Dashboard_bar> dashboard_bar= new HashSet<Dashboard_bar>(0);
	

	/**
	 * 
	 */
	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 */
	public Dashboard(User user,String name) {
		super();
		this.createdAT= new Date();
		this.user=user;
		this.name=name;
	}


	/**
	 * @return the dashboardid
	 */
	public Long getDashboardid() {
		return dashboardid;
	}


	/**
	 * @param dashboardid the dashboardid to set
	 */
	public void setDashboardid(Long dashboardid) {
		this.dashboardid = dashboardid;
	}


	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}


	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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


	/**
	 * @return the dashboard_bar
	 */
	public Set<Dashboard_bar> getDashboard_bar() {
		return dashboard_bar;
	}


	/**
	 * @param dashboard_bar the dashboard_bar to set
	 */
	public void setDashboard_bar(Set<Dashboard_bar> dashboard_bar) {
		this.dashboard_bar = dashboard_bar;
	}


	
	
	

}
