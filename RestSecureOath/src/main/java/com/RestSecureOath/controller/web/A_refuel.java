package com.RestSecureOath.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Refuel;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.RefuelRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.requestdto.RefuelDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;

@RestController
public class A_refuel {
	
	private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	private final GroupsRepository grepository;
	
	private final ActivityRepository arepository;
	
	private final DriverRepository drepository;
	
	private final RefuelRepository rrepository;
	
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 * @param userRepository
	 * @param grepository
	 * @param arepository
	 * @param drepository
	 */
	@Autowired
	public A_refuel(VehicleRepository vrepository, ThumbnailService thumbnailService, StorageService storageService,
			UserRepositoryX userRepository, GroupsRepository grepository, ActivityRepository arepository,
			DriverRepository drepository,RefuelRepository rrepository) {
		super();
		this.vrepository = vrepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
		this.userRepository = userRepository;
		this.grepository = grepository;
		this.arepository = arepository;
		this.drepository = drepository;
		this.rrepository = rrepository;	
	}
	
	
	@RequestMapping(value = "/a_refuel/add", method = RequestMethod.POST)
	public List<Refuel> refuel(@RequestBody RefuelDto refuelDto ,Principal principal) {
		System.out.println(refuelDto);
		Activity act = arepository.findOne(refuelDto.getActivityID());
		Set<Refuel> refuels= act.getRefuel();
		Refuel ref = new Refuel(act, refuelDto.getVolume(), refuelDto.getCost(), refuelDto.getRate());
		refuels.add(ref);
		act.setRefuel(refuels);
		List<Refuel> list = new ArrayList<Refuel>();
		list.add(rrepository.save(ref));
		return list;		
	}


	@RequestMapping(value = "/a_ride/add", method = RequestMethod.POST)
	public List<Activity> ride(@RequestBody RefuelDto refuelDto ,Principal principal) {
		Activity act = arepository.findOne(refuelDto.getActivityID());
		Set<Refuel> refuels= act.getRefuel();
		Refuel ref = new Refuel(act, refuelDto.getVolume(), refuelDto.getCost(), refuelDto.getRate());
		refuels.add(ref);
		act.setRefuel(refuels);
		List<Activity> list = new ArrayList<Activity>();
		list.add(arepository.save(act));
		return list;		

	}

}
