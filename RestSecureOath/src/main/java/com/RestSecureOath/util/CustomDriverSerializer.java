package com.RestSecureOath.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Vehicle;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
 
public class CustomDriverSerializer extends JsonSerializer<Driver>{
 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
     
    @Override
    public void serialize(Driver driver, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
    	Driver drv = new Driver();
    	drv.setUserId(driver.getUserId());
    	drv.setUserName(driver.getUserName());
    	drv.setEmail(driver.getEmail());
    	drv.setCreatedAT(driver.getCreatedAT());
    	drv.setEnabled(driver.getEnabled());
    	drv.setGroups(driver.getGroups());
    	drv.setFirstName(driver.getFirstName());
    	drv.setLastName(driver.getLastName());
    	drv.setLicenseID(driver.getLicenseID());
    	drv.setLicenseID_expiry(driver.getLicenseID_expiry());
    	drv.setLicenseID_snap(driver.getLicenseID_snap());
    	drv.setNationalID(driver.getNationalID());
    	drv.setNationalID_expiry(driver.getNationalID_expiry());
    	drv.setNationalID_snap(driver.getNationalID_snap());
        
        generator.writeObject(drv);
        
    }
     
    
     
}
