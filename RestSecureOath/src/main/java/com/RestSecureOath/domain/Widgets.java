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
@Table(name="WIDGETS",uniqueConstraints=@UniqueConstraint(columnNames={"dashboardid", "widgetsid"}))
public class Widgets implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="widgetsid")
	private Long widgetsid;
	
	@Column(name="col")
	private Long col;
	
	@Column(name="row")
	private Long row;
	
	@Column(name="sizeX")
	private Long sizeX;
	
	@Column(name="sizeY")
	private Long sizeY;
	
	@Column(name="name")
	private String name;
	
	@Column(name="tag")
	private String tag;
	
	@Column(name="chart")
	private Boolean chart;
	
	@Column(name="type",nullable = false)
	@Enumerated(EnumType.STRING)
	private Widgettype type;
	
	@ManyToOne
	@JoinColumn(name="dashboardid")
	@JsonBackReference
	private Dashboard dashboard;
	
	/**
	 * 
	 */
	public Widgets() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param col
	 * @param row
	 * @param sizeX
	 * @param sizeY
	 * @param name
	 * @param chart
	 * @param type
	 * @param dashboard
	 */
	public Widgets(Long col, Long row,  Long sizeY,Long sizeX, String name,String tag, Boolean chart, Widgettype type,
			Dashboard dashboard) {
		super();
		this.col = col;
		this.row = row;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.name = name;
		this.tag =tag;
		this.chart = chart;
		this.type = type;
		this.dashboard = dashboard;		
	}


	/**
	 * @return the col
	 */
	public Long getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(Long col) {
		this.col = col;
	}

	/**
	 * @return the row
	 */
	public Long getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(Long row) {
		this.row = row;
	}

	/**
	 * @return the sizeX
	 */
	public Long getSizeX() {
		return sizeX;
	}

	/**
	 * @param sizeX the sizeX to set
	 */
	public void setSizeX(Long sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public Long getSizeY() {
		return sizeY;
	}

	/**
	 * @param sizeY the sizeY to set
	 */
	public void setSizeY(Long sizeY) {
		this.sizeY = sizeY;
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
	 * @return the chart
	 */
	public Boolean getChart() {
		return chart;
	}

	/**
	 * @param chart the chart to set
	 */
	public void setChart(Boolean chart) {
		this.chart = chart;
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

	/**
	 * @return the dashboard
	 */
	public Dashboard getDashboard() {
		return dashboard;
	}

	/**
	 * @param dashboard the dashboard to set
	 */
	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	/**
	 * @return the widgetsid
	 */
	public Long getWidgetsid() {
		return widgetsid;
	}

	/**
	 * @param widgetsid the widgetsid to set
	 */
	public void setWidgetsid(Long widgetsid) {
		this.widgetsid = widgetsid;
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

	
	
}
