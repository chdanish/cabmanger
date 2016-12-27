package com.RestSecureOath.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="WIDGETDATA",uniqueConstraints=@UniqueConstraint(columnNames={"name","type"}))
public class Widgetdata implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="widgetdataid")
	private Long widgetdataid;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="tag",nullable = false)
	private String tag;
	
	@Column(name="type",nullable = false,unique=true)
	@Enumerated(EnumType.STRING)
	private Widgettype type;

	
	/**
	 * 
	 */
	public Widgetdata() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param name
	 * @param tag
	 * @param type
	 */
	public Widgetdata(String name, String tag, Widgettype type) {
		super();
		this.name = name;
		this.tag = tag;
		this.type = type;
	}


	/**
	 * @return the widgetdataid
	 */
	public Long getWidgetdataid() {
		return widgetdataid;
	}


	/**
	 * @param widgetdataid the widgetdataid to set
	 */
	public void setWidgetdataid(Long widgetdataid) {
		this.widgetdataid = widgetdataid;
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
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}


	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}


	/**
	 * @return the type
	 */
	public Widgettype getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(Widgettype type) {
		this.type = type;
	}

	
}
