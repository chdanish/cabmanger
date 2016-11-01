package com.RestSecureOath.controller.web;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;

@RestController
public class A_Home {
	
	
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
	public A_Home(VehicleRepository vrepository, ThumbnailService thumbnailService,
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

	
	
	@Controller
	class A_vehiclesView{
		@RequestMapping(value = "/a_home", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_home";		
		}		
	}
}
