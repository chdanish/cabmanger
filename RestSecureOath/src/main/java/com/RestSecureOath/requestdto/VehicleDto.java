package com.RestSecureOath.requestdto;

import java.io.Serializable;

public class VehicleDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8299077875010076527L;

	private long vehicleId;
	
	private String make;
	
	private String modelname;
	
	private String submodel;
	
	private int modelyear;
	
	private String regnumber;

	/**
	 * @return the vehicleId
	 */
	public long getVehicleId() {
		return vehicleId;
	}

	/**
	 * @param vehicleId the vehicleId to set
	 */
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return the modelname
	 */
	public String getModelname() {
		return modelname;
	}

	/**
	 * @param modelname the modelname to set
	 */
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	/**
	 * @return the submodel
	 */
	public String getSubmodel() {
		return submodel;
	}

	/**
	 * @param submodel the submodel to set
	 */
	public void setSubmodel(String submodel) {
		this.submodel = submodel;
	}

	/**
	 * @return the modelyear
	 */
	public int getModelyear() {
		return modelyear;
	}

	/**
	 * @param modelyear the modelyear to set
	 */
	public void setModelyear(int modelyear) {
		this.modelyear = modelyear;
	}

	/**
	 * @return the regnumber
	 */
	public String getRegnumber() {
		return regnumber;
	}

	/**
	 * @param regnumber the regnumber to set
	 */
	public void setRegnumber(String regnumber) {
		this.regnumber = regnumber;
	}
	
	
}
