package com.RestSecureOath.controller.web;

import java.io.IOException;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.QGroups;
import com.RestSecureOath.domain.QUser;
import com.RestSecureOath.domain.Roles;
import com.RestSecureOath.domain.User;
import com.querydsl.core.types.Predicate;

@RestController
public class A_groups {
	
	
	private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	private final GroupsRepository grepository;
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_groups(VehicleRepository vrepository, ThumbnailService thumbnailService,
			StorageService storageService,UserRepositoryX userRepository,GroupsRepository grepository) {
		super();
		this.vrepository = vrepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
		this.userRepository = userRepository;
		this.grepository  = grepository;
	}

	@RequestMapping(value = "/a_groups/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<Groups>> persons(Pageable pageable,
			PagedResourcesAssembler assembler,Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		QGroups group = QGroups.groups;
		Predicate predicate = group.company.companyId.eq(comp.getCompanyId()); //driver.company.CompanyId.eq(x);
		Page<Groups> groups = grepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(groups), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/a_groups/add/{name}", method = RequestMethod.POST)
	public Map<String,Object> add(@PathVariable String name,Principal principal) throws IOException {
		Map<String,Object> map = new HashMap<>();
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Groups group = grepository.save(new Groups(name,comp,1));
		map.put("status", group);
		return map;
	    }
	
	@RequestMapping(value = "/a_groups/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable long id) throws IOException {
		Map<String,Object> map = new HashMap<>();
		if(grepository.exists(id)){
			grepository.delete(id);
			map.put("status","done");
	        return map;
		};		
        map.put("status","fail");
        return map;
	    }
	@RequestMapping(value = "/a_groups/pageable/userlist", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> users(Pageable pageable,
			PagedResourcesAssembler assembler,Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		QGroups group = QGroups.groups;
		QUser user = QUser.user;

		Predicate predriver = user.roles.contains(Roles.DRIVER).or(user.roles.contains(Roles.ADMIN));
		Predicate predcomp = group.company.companyId.eq(comp.getCompanyId()); //driver.company.CompanyId.eq(x);
		
		Predicate pred =user.isNotNull().and(predcomp).and(predriver);
		Page<User> users = userRepository.findAll(pred,pageable);
		return new ResponseEntity<>(assembler.toResource(users), HttpStatus.OK);
	}
	
	@Controller
	class A_groupsView{
		@RequestMapping(value = "/a_groups", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_groups";		
		}		
	}
}
