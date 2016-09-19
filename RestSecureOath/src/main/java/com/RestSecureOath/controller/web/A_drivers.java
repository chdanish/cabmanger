package com.RestSecureOath.controller.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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

import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.QCompany;
import com.RestSecureOath.domain.QDriver;
import com.RestSecureOath.domain.QUser;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.requestdto.DriverDto;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RestController
public class A_drivers {
	
	private final StorageService storageService;

	private final UserRepositoryX userRepository;
	
	private final DriverRepository drepository;
	
	private final ThumbnailService thumbnailService;

	/**
	 * @param storageService
	 * @param drepository
	 */
	@Autowired
	public A_drivers(StorageService storageService, DriverRepository drepository
			,ThumbnailService thumbnailService,UserRepositoryX userRepository) {
		super();
		this.storageService = storageService;
		this.drepository = drepository;
		this.thumbnailService= thumbnailService;
		this.userRepository = userRepository;
	}

	@PersistenceContext
	private EntityManager em;

	@RequestMapping(value = "/a_drivers/list", method = RequestMethod.GET)
	public Iterable<Driver> getView(Principal principal){
		QDriver driver = QDriver.driver;
		long x = 1;
		//User u = em.find(User.class, 2l);
		//System.out.println(u);
		Predicate predicate = driver.company.CompanyId.eq(x);

		return drepository.findAll(predicate);
		//return null;
	}

	@RequestMapping(value = "/a_drivers/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> persons(Pageable pageable,
			PagedResourcesAssembler assembler,Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		QDriver driver = QDriver.driver;
		Predicate predicate = driver.company.CompanyId.eq(comp.getCompanyId());
		Page<Driver> persons = drepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/a_drivers/snapupload/{id}", method = RequestMethod.POST)
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file
		 ,@PathVariable long id) throws IOException {
		Map<String,Object> map = new HashMap<>();
		byte[] bFile = new byte[(int) file.getSize()];
		Driver driver = drepository.findOne(id);
		String snap = Base64Utils.encodeToString(thumbnailService.resize(file));
		driver.setSnap(snap);
		drepository.save(driver);
        storageService.store(file);
        map.put("sattus",snap);
        return map;
	    }
	@RequestMapping(value = "/a_drivers/add", method = RequestMethod.POST)
	public Map<String,Object> add(@RequestBody DriverDto dto,Principal principal) throws IOException {
		Map<String,Object> map = new HashMap<>();
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Driver driver =drepository.save(new Driver(dto.getUserName(), dto.getPassword(), dto.getEmail(),
				dto.getFirstName(), dto.getLastName(), 1,comp,null, null, null, null, null, null, null));
		map.put("status", driver);
		return map;
	    }
	
	@RequestMapping(value = "/a_drivers/update", method = RequestMethod.POST)
	public Map<String,Object> update(@RequestBody DriverDto dto,Principal principal) throws IOException, ParseException {
		Map<String,Object> map = new HashMap<>();
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Driver driver = drepository.findOne(dto.getUserId());
		driver.setUserName(dto.getUserName());
		driver.setPassword(dto.getConfirmPassword());
		driver.setFirstName(dto.getFirstName());
		driver.setLastName(dto.getLastName());
		driver.setEmail(dto.getEmail());
		driver.setNationalID(dto.getNationalID());
		driver.setNationalID_expiry((Date)formatter.parse(dto.getNationalID_expiry()));
		driver.setLicenseID(dto.getLicenseID());
		driver.setLicenseID_expiry((Date)formatter.parse(dto.getLicenseID_expiry()));
		driver =drepository.save(driver);
		map.put("status", driver);
		return map;
	    }
	@RequestMapping(value = "/a_drivers/delete/{id}", method = RequestMethod.DELETE)
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
	class A_driversView{
		@RequestMapping(value = "/a_drivers", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_drivers";		
		}		
	}
}



