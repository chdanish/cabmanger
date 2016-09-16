package com.RestSecureOath.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="RIDE")
public class Ride implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1873531694977143244L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rideid")
	private long rideID;
	
	@ManyToOne
	@JoinColumn(name="activityid", nullable = false)
	@JsonBackReference
	private Activity activity;
	
	private long fare;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at",updatable=false,nullable=false)
	private Date createdAT;	
	
	public Ride() {
	}

	public Ride(Activity activity,long fare) {
		this.activity=activity;
		this.fare=fare;
		this.createdAT=new Date();
	}

}
