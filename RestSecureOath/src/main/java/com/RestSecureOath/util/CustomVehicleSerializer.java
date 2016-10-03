package com.RestSecureOath.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Vehicle;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
 
public class CustomVehicleSerializer extends JsonSerializer<Vehicle>{
 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
     
    @Override
    public void serialize(Vehicle vehicle, JsonGenerator generator, SerializerProvider provider)
            throws IOException, JsonProcessingException {
    	Vehicle vcl = new Vehicle();
    	vcl.setVehicleId(vehicle.getVehicleId());
    	vcl.setMake(vehicle.getMake());
    	vcl.setModelname(vehicle.getModelname());
    	vcl.setModelyear(vehicle.getModelyear());
    	vcl.setCreatedAT(vehicle.getCreatedAT());
    	vcl.setRegnumber(vehicle.getRegnumber());
    	vcl.setSubmodel(vehicle.getSubmodel());
    	vcl.setSnap(vehicle.getSnap());
        String formattedvehicle = new ObjectMapper()
        		.writeValueAsString(vcl);
       // generator.writeString(formattedvehicle);
        generator.writeObject(vcl);
        
    }
     
    
     
}
