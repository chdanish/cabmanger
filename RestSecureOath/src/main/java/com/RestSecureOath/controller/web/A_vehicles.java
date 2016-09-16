package com.RestSecureOath.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.domain.QDriver;
import com.RestSecureOath.domain.QVehicle;
import com.RestSecureOath.domain.User;
import com.querydsl.core.types.Predicate;

@RestController
public class A_vehicles {
	
	@Autowired
	private VehicleRepository vrepository;
	
	@RequestMapping(value = "/a_vehicles/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> persons(Pageable pageable,
			PagedResourcesAssembler assembler) {
		QVehicle vehicle = QVehicle.vehicle;
		long x = 1;
		Predicate predicate = vehicle.company.CompanyId.eq(x); //driver.company.CompanyId.eq(x);
		Page<User> persons = vrepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@Controller
	class A_driversView{
		@RequestMapping(value = "/a_vehicles", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_vehicles";		
		}		
	}
}
