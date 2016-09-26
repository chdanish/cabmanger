package com.RestSecureOath.controller.web;

import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

import com.RestSecureOath.domain.Admin;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.QAdmin;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.AdminRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.requestdto.DriverDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.querydsl.core.types.Predicate;

@RestController
public class A_admins {
	
	
	private final AdminRepository adrepository;
	
	private final DriverRepository drepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * @param adrepository
	 * @param drepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_admins(AdminRepository adrepository, DriverRepository drepository, ThumbnailService thumbnailService,
			StorageService storageService,UserRepositoryX userRepository) {
		super();
		this.adrepository = adrepository;
		this.drepository = drepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
		this.userRepository= userRepository;
	}
	

	@RequestMapping(value = "/a_admins/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> persons(Pageable pageable,
			PagedResourcesAssembler assembler,Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		QAdmin admin = QAdmin.admin;
		Predicate predicate = admin.company.companyId.eq(comp.getCompanyId());
		Page<Admin> persons = adrepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/a_admins/snapupload/{id}", method = RequestMethod.POST)
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file
			 ,@PathVariable long id) throws IOException {
		Map<String,Object> map = new HashMap<>();	
			Admin driver = adrepository.findOne(id);
			String snap = Base64Utils.encodeToString(thumbnailService.resize(file));
			driver.setSnap(snap);
			adrepository.save(driver);
	        storageService.store(file);
	        map.put("status",snap);
	        return map;
	    }
	
	@RequestMapping(value = "/a_admins/add", method = RequestMethod.POST)
	public Map<String,Object> add(@RequestBody DriverDto dto,Principal principal) throws IOException {
		Map<String,Object> map = new HashMap<>();
		User user = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get();
		Company comp =user.getCompany();
		Groups groups= user.getGroups();
		Admin admin =adrepository.save(new Admin(dto.getUserName(), dto.getPassword(), dto.getEmail(),
				dto.getFirstName(), dto.getLastName(), 1,comp,groups));
		map.put("status", admin);
		return map;
	    }

	@RequestMapping(value = "/a_admins/update", method = RequestMethod.POST)
	public Map<String,Object> update(@RequestBody DriverDto dto,Principal principal) throws IOException, ParseException {
		Map<String,Object> map = new HashMap<>();
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Admin admin = adrepository.findOne(dto.getUserId());
		admin.setUserName(dto.getUserName());
		admin.setPassword(dto.getConfirmPassword());
		admin.setFirstName(dto.getFirstName());
		admin.setLastName(dto.getLastName());
		admin.setEmail(dto.getEmail());
		admin.setNationalID(dto.getNationalID());
		admin.setNationalID_expiry((Date)formatter.parse(dto.getNationalID_expiry()));
		admin =adrepository.save(admin);
		map.put("status", admin);
		return map;
	    }
	
	@RequestMapping(value = "/a_admins/delete/{id}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable long id) throws IOException {
		Map<String,Object> map = new HashMap<>();
		if(drepository.exists(id)){
			drepository.delete(id);
			map.put("status","done");
	        return map;
		};		
        map.put("status","fail");
        return map;
	    }
	
	@Controller
	class A_adminsView{
		@RequestMapping(value = "/a_admins", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_admins";		
		}		
	}
}
