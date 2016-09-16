package com.RestSecureOath.controller.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RestController
public class A_drivers {
	
	private final StorageService storageService;

	
	private final DriverRepository drepository;
	
	private final ThumbnailService thumbnailService;

	/**
	 * @param storageService
	 * @param drepository
	 */
	@Autowired
	public A_drivers(StorageService storageService, DriverRepository drepository
			,ThumbnailService thumbnailService) {
		super();
		this.storageService = storageService;
		this.drepository = drepository;
		this.thumbnailService= thumbnailService;
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
			PagedResourcesAssembler assembler) {
		QDriver driver = QDriver.driver;
		long x = 1;
		Predicate predicate = driver.company.CompanyId.eq(x);
		Page<Driver> persons = drepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/a_drivers/snapupload/{id}", method = RequestMethod.POST)
	 public String handleFileUpload(@RequestParam("file") MultipartFile file
			 ,@PathVariable long id) throws IOException {
			byte[] bFile = new byte[(int) file.getSize()];
			Driver driver = drepository.findOne(id);
			driver.setSnap(Base64Utils.encodeToString(thumbnailService.resize(file)));
			drepository.save(driver);
	        storageService.store(file);
	        return "redirect:/";
	    }

	@Controller
	class A_driversView{
		@RequestMapping(value = "/a_drivers", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_drivers";		
		}		
	}
}



