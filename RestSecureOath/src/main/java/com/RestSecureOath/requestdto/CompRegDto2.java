package com.RestSecureOath.requestdto;

public class CompRegDto2 {
	

	private String name;

	private String registration;

	private String distanceunit;
	
	private String fuelunit;

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
	 * @return the registration
	 */
	public String getRegistration() {
		return registration;
	}

	/**
	 * @param registration the registration to set
	 */
	public void setRegistration(String registration) {
		this.registration = registration;
	}

	/**
	 * @return the distanceunit
	 */
	public String getDistanceunit() {
		return distanceunit;
	}

	/**
	 * @param distanceunit the distanceunit to set
	 */
	public void setDistanceunit(String distanceunit) {
		this.distanceunit = distanceunit;
	}

	/**
	 * @return the fuelunit
	 */
	public String getFuelunit() {
		return fuelunit;
	}

	/**
	 * @param fuelunit the fuelunit to set
	 */
	public void setFuelunit(String fuelunit) {
		this.fuelunit = fuelunit;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompRegDto2 [name=" + name + ", registration=" + registration + ", distanceunit=" + distanceunit
				+ ", fuelunit=" + fuelunit + "]";
	}
	

	

}
