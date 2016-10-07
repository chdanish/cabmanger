package com.RestSecureOath.controller.web;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.requestdto.StartactivityDto;
import com.RestSecureOath.requestdto.VehicleDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.QActivity;
import com.RestSecureOath.domain.QVehicle;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Vehicle;
import com.querydsl.core.types.Predicate;

@RestController
public class A_startactivity {
	
	
	private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	private final GroupsRepository grepository;
	
	private final ActivityRepository arepository;
	
	private final DriverRepository drepository;
	
	private EntityManager entityManager;
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
    public void setEntityManager(EntityManager entityManager) {
        this. entityManager = entityManager;
    }
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_startactivity(VehicleRepository vrepository, ThumbnailService thumbnailService,
			StorageService storageService,UserRepositoryX userRepository,GroupsRepository grepository,
			ActivityRepository arepository,DriverRepository drepository) {
		super();
		this.vrepository = vrepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
		this.userRepository = userRepository;
		this.grepository  = grepository;
		this.arepository = arepository ;
		this.drepository = drepository;
	}
	
	@RequestMapping(value = "/a_startactivity/vehicle/list", method = RequestMethod.GET)
	public Iterable<User> vehiclelist(Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		QVehicle vehicle = QVehicle.vehicle;
		Predicate predicate = vehicle.company.companyId.eq(comp.getCompanyId()); //driver.company.CompanyId.eq(x);
		return vrepository.findAll(predicate);
	}
	
	long l;
	@RequestMapping(value = "/a_startactivity/startactivity", method = RequestMethod.POST)
	@Transactional
	public Set<Activity> startactivity(@RequestBody StartactivityDto sactivity ,Principal principal) {
		Driver driver;Vehicle vehicle;Activity act;
		driver = drepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get();
		vehicle = vrepository.findOne(sactivity.getVehicleID()); //driver.company.CompanyId.eq(x);
		String [] str =sactivity.getStartReading_snap().split(",");
		String snap = Base64Utils.encodeToString(thumbnailService.resize(str[1], 300));
		Set<Activity> activities = vehicle.getActivity();
		activities.add( new Activity(driver, vehicle, sactivity.getStartReading(), snap));
		vehicle.setActivity(activities);
		vehicle = vrepository.save(vehicle);

		return vehicle.getActivity();
		
	}

	
	@Controller
	class A_vehiclesView{
		@RequestMapping(value = "/a_startactivity", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_startactivity";		
		}		
	}
}
