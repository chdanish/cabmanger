package com.RestSecureOath.config;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class EntityTest {
	
	
	@Test
	public void entity_test_for_USER()
	  throws JsonProcessingException {
		
		Company comp = new Company();
		Groups dgroup = new Groups("Default", comp,1);
		Owner owner = new Owner("owner", "password", "email", "firstName", "lastName", 1, comp,dgroup);
		Driver driver1 =new Driver("driverusername1", "password1", "email1", "firstName", "lastName", 1,owner.getCompany(),dgroup);
	    Driver driver2 =new Driver("driverusername2", "password2", "email2", "firstName", "lastName", 1,owner.getCompany(),dgroup);
	    Set<User> user = new HashSet<>();
	    user.add(owner);user.add(driver1);user.add(driver2);
	    comp.setUser(user);
	    String result = new ObjectMapper().writeValueAsString(owner);
	 
	    assertThat(result, containsString("owner"));
	   // assertThat(result, containsString("John"));
	    //assertThat(result, containsString("driverusername1"));
	    assertThat(result, not(containsString("driverusername1")));
	}
	
	@Test
	public void entity_test_for_COMPANY()
	  throws JsonProcessingException {
		
		Company comp = new Company();
		Groups dgroup = new Groups("Default", comp,1);
		Owner owner = new Owner("owner", "password", "email", "firstName", "lastName", 1, comp,dgroup);
	    Driver driver1 =new Driver("driverusername1", "password1", "email1", "firstName", "lastName", 1,owner.getCompany(),dgroup);
	    Driver driver2 =new Driver("driverusername2", "password2", "email2", "firstName", "lastName", 1,owner.getCompany(),dgroup);
	    Set<User> user = new HashSet<>();
	    user.add(owner);user.add(driver1);user.add(driver2);
	    comp.setUser(user);
	    String result = new ObjectMapper().writeValueAsString(comp);
	 
	    assertThat(result, containsString("owner"));
	   // assertThat(result, containsString("John"));
	    assertThat(result, containsString("driverusername1"));
	    //assertThat(result, not(containsString("driverusername1")));
	}
	
	@Test
	public void entity_test_for_Vehicle()
	  throws JsonProcessingException {
		
		Company comp = new Company();
		Groups dgroup = new Groups("Default", comp,1);
		Owner owner = new Owner("owner", "password", "email", "firstName", "lastName", 1, comp,dgroup);
	    Driver driver1 =new Driver("driverusername1", "password1", "email1", "firstName", "lastName", 1,owner.getCompany(),dgroup);
		Vehicle v1 =new Vehicle("Toyota", "Altis", comp,dgroup,"rvu-2944",1);
		Vehicle v2 =new Vehicle("Honda", "Civic", comp,dgroup,"trw-8893",1);
		
		String result = new ObjectMapper().writeValueAsString(v1);
		assertThat(result, containsString("Toyota"));
		 assertThat(result, containsString("driverusername1"));
	}

}
