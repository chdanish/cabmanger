package com.RestSecureOath.controller.web;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.requestdto.DriverDto;
import com.RestSecureOath.requestdto.VehicleDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.RestSecureOath.domain.Admin;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.QDriver;
import com.RestSecureOath.domain.QVehicle;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Vehicle;
import com.querydsl.core.types.Predicate;

@RestController
public class A_vehicles {
	
	
	private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_vehicles(VehicleRepository vrepository, ThumbnailService thumbnailService,
			StorageService storageService,UserRepositoryX userRepository) {
		super();
		this.vrepository = vrepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/a_vehicles/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> persons(Pageable pageable,
			PagedResourcesAssembler assembler) {
		QVehicle vehicle = QVehicle.vehicle;
		long x = 1;
		Predicate predicate = vehicle.company.CompanyId.eq(x); //driver.company.CompanyId.eq(x);
		Page<User> persons = vrepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/a_vehicles/snapupload/{id}", method = RequestMethod.POST)
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file
		 ,@PathVariable long id) throws IOException {
		Map<String,Object> map = new HashMap<>();
		byte[] bFile = new byte[(int) file.getSize()];
		Vehicle vehicle = vrepository.findOne(id);
		String snap = Base64Utils.encodeToString(thumbnailService.resize(file));
		vehicle.setSnap(snap);
		vrepository.save(vehicle);
        storageService.store(file);
        map.put("status",snap);
        return map;
	    }
	
	@RequestMapping(value = "/a_vehicles/add", method = RequestMethod.POST)
	public Map<String,Object> add(@RequestBody VehicleDto dto,Principal principal) throws IOException {
		Map<String,Object> map = new HashMap<>();
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Vehicle vehicle = new Vehicle(dto.getMake(),dto.getModelname(), comp);
		vehicle.setSubmodel(dto.getSubmodel());
		vehicle.setRegnumber(dto.getRegnumber());
		vehicle.setModelyear(dto.getModelyear());
		vehicle=vrepository.save(vehicle);
		map.put("status", vehicle);
		return map;
	    }
	
	@RequestMapping(value = "/a_vehicles/update", method = RequestMethod.POST)
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
	class A_driversView{
		@RequestMapping(value = "/a_vehicles", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_vehicles";		
		}		
	}
}
