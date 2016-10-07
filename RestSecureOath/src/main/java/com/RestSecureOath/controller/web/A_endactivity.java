package com.RestSecureOath.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.requestdto.EndactivityDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.QActivity;
import com.querydsl.core.types.Predicate;

@RestController
public class A_endactivity {
	
	
	private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	private final GroupsRepository grepository;
	
	private final ActivityRepository arepository;
	
	private final DriverRepository drepository;
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_endactivity(VehicleRepository vrepository, ThumbnailService thumbnailService,
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
	
	
	
	@RequestMapping(value = "/a_endactivity/checkunfinishactivity", method = RequestMethod.GET)
	public List<Activity> checkunfinishactivity(Principal principal) {
		Driver driver = drepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get();
		List<Activity> list = new ArrayList<Activity>();
		QActivity activity = QActivity.activity;
		Predicate check_driverid =activity.driver.userId.eq(driver.getUserId());
		Predicate pred =activity.endedAT.isNull().and(check_driverid);		
		arepository.findAll(pred).forEach(e->list.add(e));
		return list;		
	}
	
	@RequestMapping(value = "/a_endactivity/endactivity", method = RequestMethod.POST)
	public List<Activity> startactivity(@RequestBody EndactivityDto eactivity ,Principal principal) {
		System.out.println(eactivity.getEndReading_snap());
		String [] str =eactivity.getEndReading_snap().split(",");
		System.out.println(str[1]);
		String snap = Base64Utils.encodeToString(thumbnailService.resize(str[1], 300));

		List<Activity> list = new ArrayList<Activity>();
		Activity activity = arepository.findOne(eactivity.getActivityID());
		activity.setEndedAT(new Date());
		activity.setEndReading(eactivity.getEndReading());
		activity.setEndReading_snap(snap);
		list.add(arepository.save(activity));
		return list;
		
	}

	
	@Controller
	class A_vehiclesView{
		@RequestMapping(value = "/a_endactivity", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_endactivity";		
		}		
	}
}
