package com.RestSecureOath.domain;

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
@Table(name="WIDGET",uniqueConstraints=@UniqueConstraint(columnNames={"dashboardbarid", "widget_order"}))
public class Widget {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="widgetid")
	private Long widgetid;
	
	@Column(name="widget_order")
	private Long widget_order;
	
	@Column(name="widget_type",nullable = false)
	@Enumerated(EnumType.STRING)
	private Widgettype widget_type;
	
	@ManyToOne
	@JoinColumn(name="dashboardbarid")
	@JsonBackReference
	private Dashboard_bar dashboard_bar;
	
	/**
	 * 
	 */
	public Widget() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param bar_order
	 * @param dashboard
	 */
	public Widget(Long widget_order, Dashboard_bar dashboard_bar,Widgettype widget_type) {
		super();
		this.widget_order = widget_order;
		this.dashboard_bar = dashboard_bar;
		this.widget_type = widget_type;
	}



	/**
	 * @return the widgetid
	 */
	public Long getWidgetid() {
		return widgetid;
	}



	/**
	 * @param widgetid the widgetid to set
	 */
	public void setWidgetid(Long widgetid) {
		this.widgetid = widgetid;
	}




	/**
	 * @return the widget_order
	 */
	public Long getWidget_order() {
		return widget_order;
	}



	/**
	 * @param widget_order the widget_order to set
	 */
	public void setWidget_order(Long widget_order) {
		this.widget_order = widget_order;
	}



	/**
	 * @return the dashboard_bar
	 */
	public Dashboard_bar getDashboard_bar() {
		return dashboard_bar;
	}



	/**
	 * @param dashboard_bar the dashboard_bar to set
	 */
	public void setDashboard_bar(Dashboard_bar dashboard_bar) {
		this.dashboard_bar = dashboard_bar;
	}



	/**
	 * @return the widget_type
	 */
	public Widgettype getWidget_type() {
		return widget_type;
	}



	/**
	 * @param widget_type the widget_type to set
	 */
	public void setWidget_type(Widgettype widget_type) {
		this.widget_type = widget_type;
	}


	

}
