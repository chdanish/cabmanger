package com.RestSecureOath.controller.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.domain.Admin;
import com.RestSecureOath.domain.QAdmin;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.AdminRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLTemplates;

@RestController
public class A_admins {
	
	@Autowired
	private AdminRepository adrepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@RequestMapping(value = "/a_admins/pageable/list", method = RequestMethod.GET)
	HttpEntity<PagedResources<User>> persons(Pageable pageable,
			PagedResourcesAssembler assembler) {
		QAdmin admin = QAdmin.admin;
		long x = 1;
		Predicate predicate = admin.company.CompanyId.eq(x);
		Page<Admin> persons = adrepository.findAll(predicate,pageable);
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/a_admins/sanp", method = RequestMethod.GET)
	public String snap() {
		QAdmin admin = QAdmin.admin;
		return "";
	}
	
	@Controller
	class A_driversView{
		@RequestMapping(value = "/a_admins", method = RequestMethod.GET)
		public String getView(Principal principal){
			return "a_admins";		
		}		
	}
}
