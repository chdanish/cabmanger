package com.RestSecureOath.controller.web;

import java.io.IOException;
import java.security.Principal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

import com.RestSecureOath.domain.Admin;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.QAdmin;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.AdminRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLTemplates;

@RestController
public class A_admins {
	
	
	private final AdminRepository adrepository;
	
	private final DriverRepository drepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
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
			StorageService storageService) {
		super();
		this.adrepository = adrepository;
		this.drepository = drepository;
		this.thumbnailService = thumbnailService;
		this.storageService = storageService;
	}
	

	@RequestMapping(value = "/a_admins/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> persons(Pageable pageable,
			PagedResourcesAssembler assembler) {
		QAdmin admin = QAdmin.admin;
		long x = 1;
		Predicate predicate = admin.company.CompanyId.eq(x);
		Page<Admin> persons = adrepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/a_admins/snapupload/{id}", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file
			 ,@PathVariable long id) throws IOException {
			byte[] bFile = new byte[(int) file.getSize()];
			Admin driver = adrepository.findOne(id);
			driver.setSnap(Base64Utils.encodeToString(thumbnailService.resize(file)));
			adrepository.save(driver);
	        storageService.store(file);
	        return "redirect:/";
	    }

	
	@Controller
	class A_driversView{
		@RequestMapping(value = "/a_admins", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_admins";		
		}		
	}
}
