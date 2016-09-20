package com.RestSecureOath.controller.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Distance_Unit;
import com.RestSecureOath.domain.Fuel_Unit;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.CompanyRepository;
import com.RestSecureOath.repo.OwnerRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.requestdto.CompRegDto2;
import com.RestSecureOath.util.SecurityUtils;

@RestController
public class Setup {
	
	@Autowired
	private CompanyRepository crepository;
	@Autowired
	private UserRepositoryX urepository;
	
/*	@RequestMapping(value = "/setup", method = RequestMethod.GET)
	public String getView(Principal principal){
		return "setup";		
	}*/
	
	@RequestMapping(value = "/setup/info", method = RequestMethod.GET)
	public Map<String,Object> info(Principal principal){
		Map<String,Object> map = new HashMap<>();
		Company comp = urepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		map.put("status", comp);
		return map;
	}
	
	@RequestMapping(value = "/setup", method = RequestMethod.POST)
	public Map<String,String> save(@RequestBody CompRegDto2 company,Principal principal){
		String username= SecurityUtils.getLoggedInUserName(principal);
		User owner=urepository.findByUserName(username).orElse(new User());
		Company comp = owner.getCompany();
		comp.setName(company.getName());
		comp.setRegisration(company.getRegistration());
		System.out.println(comp.getRegisration());
		if(company.getFuelunit().equals(Fuel_Unit.LITRE.toString())){
			comp.setFuelunit(Fuel_Unit.LITRE);
		}else if(company.getFuelunit()==Fuel_Unit.GALON.toString()){
			comp.setFuelunit(Fuel_Unit.GALON);
		}
		if(company.getDistanceunit().equals(Distance_Unit.KILOMETER.toString())){
			comp.setDistanceunit(Distance_Unit.KILOMETER);
		}else if(company.getDistanceunit()==Distance_Unit.MILE.toString()){
			comp.setDistanceunit(Distance_Unit.MILE);
		}
		
		crepository.save(comp);
		Map<String,String> map = new HashMap<>();
		map.put("status", "done");
		System.out.println(company);
		return map;		
	}
	
	 @Controller
	 class SetupGET {
	 	@RequestMapping(value = "/setup", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "setup";		
		}		 
	 }

}
