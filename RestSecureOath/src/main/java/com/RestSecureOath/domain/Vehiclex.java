/*package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.RestSecureOath.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "VEHICLEX")
public class Vehiclex implements Serializable {
	

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vehiclexid")
	private Long vehiclexId;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using=CustomDateSerializer.class)
	private Date createdAT;
	
	
	@OneToMany(mappedBy="activityID",orphanRemoval=false)
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<Activity> activity;

	

	*//**
	 * @param make
	 * @param model
	 * @param company
	 *//*
	public Vehiclex() {
		super();
		this.createdAT= new Date();
	}



	*//**
	 * @return the vehiclexId
	 *//*
	public Long getVehiclexId() {
		return vehiclexId;
	}



	*//**
	 * @param vehiclexId the vehiclexId to set
	 *//*
	public void setVehiclexId(Long vehiclexId) {
		this.vehiclexId = vehiclexId;
	}



	*//**
	 * @return the createdAT
	 *//*
	public Date getCreatedAT() {
		return createdAT;
	}



	*//**
	 * @param createdAT the createdAT to set
	 *//*
	public void setCreatedAT(Date createdAT) {
		this.createdAT = createdAT;
	}



	*//**
	 * @return the activity
	 *//*
	public Set<Activity> getActivity() {
		return activity;
	}



	*//**
	 * @param activity the activity to set
	 *//*
	public void setActivity(Activity activity) {
		
			this.activity= new HashSet<Activity>(0);
		
		this.activity.add(activity);
	}

	
	

}
*/