package com.RestSecureOath.controller.web;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.requestdto.VehicleDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.QActivity;
import com.RestSecureOath.domain.Vehicle;
import com.querydsl.core.types.Predicate;

@RestController
public class A_activity {
	
	
	private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	private final GroupsRepository grepository;
	
	private final ActivityRepository arepository;
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_activity(VehicleRepository vrepository, ThumbnailService thumbnailService,
			StorageService storageService,UserRepositoryX userRepository,GroupsRepository grepository,
			ActivityRepository arepository) {
		super();
		this.vrepository = vrepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
		this.userRepository = userRepository;
		this.grepository  = grepository;
		this.arepository = arepository ;
	}

	@RequestMapping(value = "/a_activity/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<Activity>> activities(Pageable pageable,
			PagedResourcesAssembler assembler,Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		QActivity activity = QActivity.activity;
		Predicate predicate = activity.driver.company.companyId.eq(comp.getCompanyId())
				.and(activity.vehicle.company.companyId.eq(comp.getCompanyId())); //driver.company.CompanyId.eq(x);
		Page<Activity> persons = arepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value = "/a_activity/add", method = RequestMethod.POST)
	public Map<String,Object> add(@RequestBody VehicleDto dto,Principal principal) throws IOException {
		Map<String,Object> map = new HashMap<>();
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Groups group = grepository.findByNameAndCompanyCompanyId("Default", comp.getCompanyId()).get();
		Vehicle vehicle = new Vehicle(dto.getMake(),dto.getModelname(), comp,group);
		vehicle.setSubmodel(dto.getSubmodel());
		vehicle.setRegnumber(dto.getRegnumber());
		vehicle.setModelyear(dto.getModelyear());
		vehicle=vrepository.save(vehicle);
		map.put("status", vehicle);
		return map;
	    }*/
	
	@RequestMapping(value = "/a_activity/update", method = RequestMethod.POST)
	public Map<String,Object> update(@RequestBody VehicleDto dto,Principal principal) throws IOException, ParseException {
		Map<String,Object> map = new HashMap<>();
		Vehicle vehicle = vrepository.findOne(dto.getVehicleId());
		vehicle.setMake(dto.getMake());
		vehicle.setModelname(dto.getModelname());
		vehicle.setSubmodel(dto.getSubmodel());
		vehicle.setRegnumber(dto.getRegnumber());
		vehicle.setModelyear(dto.getModelyear());
		vehicle=vrepository.save(vehicle);
		map.put("status", vehicle);
		return map;
	    }
	
	@Controller
	class A_vehiclesView{
		@RequestMapping(value = "/a_activity", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_activity";		
		}		
	}
}
