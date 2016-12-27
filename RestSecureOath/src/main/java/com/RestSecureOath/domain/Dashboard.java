package com.RestSecureOath.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
	
	@OneToMany(mappedBy="dashboard",orphanRemoval=true,cascade=CascadeType.ALL)
	private Set<Widgets> widgets= new HashSet<Widgets>(0);
	

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
	 * @return the widgets
	 */
	public Set<Widgets> getWidgets() {
		return widgets;
	}


	/**
	 * @param widgets the widgets to set
	 */
	public void setWidgets(Set<Widgets> widgets) {
		this.widgets = widgets;
	}


	
	
	

}
