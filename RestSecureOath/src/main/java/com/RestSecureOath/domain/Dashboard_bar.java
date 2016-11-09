package com.RestSecureOath.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="DASHBOARD_BAR",uniqueConstraints=@UniqueConstraint(columnNames={"dashboardid", "bar_order"}))
public class Dashboard_bar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="dashboardbarid")
	private Long dashboardbarid;
	
	@Column(name="bar_order")
	private Long bar_order;
	
	
	@ManyToOne
	@JoinColumn(name="dashboardid")
	@JsonBackReference
	private Dashboard dashboard;
	
	@OneToMany(mappedBy="dashboard_bar",orphanRemoval=true,cascade=CascadeType.ALL)
	private Set<Widget> widget= new HashSet<Widget>(0);
	

	/**
	 * 
	 */
	public Dashboard_bar() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param bar_order
	 * @param dashboard
	 */
	public Dashboard_bar(Long bar_order, Dashboard dashboard) {
		super();
		this.bar_order = bar_order;
		this.dashboard = dashboard;
	}


	

	/**
	 * @return the dashboardbarid
	 */
	public Long getDashboardbarid() {
		return dashboardbarid;
	}



	/**
	 * @param dashboardbarid the dashboardbarid to set
	 */
	public void setDashboardbarid(Long dashboardbarid) {
		this.dashboardbarid = dashboardbarid;
	}



	/**
	 * @return the bar_order
	 */
	public Long getBar_order() {
		return bar_order;
	}


	/**
	 * @param bar_order the bar_order to set
	 */
	public void setBar_order(Long bar_order) {
		this.bar_order = bar_order;
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
	 * @return the widget
	 */
	public Set<Widget> getWidget() {
		return widget;
	}



	/**
	 * @param widget the widget to set
	 */
	public void setWidget(Set<Widget> widget) {
		this.widget = widget;
	}
	
	

	

}
