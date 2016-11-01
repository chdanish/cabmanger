package com.RestSecureOath.controller.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.QVehicle;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.service.ThumbnailService;
import com.RestSecureOath.util.SecurityUtils;
import com.querydsl.core.types.Predicate;

@RestController
public class A_miniwidget {
	
private final VehicleRepository vrepository;
	
	private final ThumbnailService thumbnailService;
	
	private final StorageService storageService;
	
	private final UserRepositoryX userRepository;
	
	private final GroupsRepository grepository;
	
	private final ActivityRepository arepository;

	private EntityManager entityManager;
	
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
    public void setEntityManager(EntityManager entityManager) {
        this. entityManager = entityManager;
    }
	/**
	 * @param vrepository
	 * @param thumbnailService
	 * @param storageService
	 */
	@Autowired
	public A_miniwidget(VehicleRepository vrepository, ThumbnailService thumbnailService,
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
	
	@RequestMapping(value = "/a_miniwidget/employee", method = RequestMethod.GET)
	public Map<String, Object> employee(Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Session session = entityManager.unwrap(Session.class);
		Map<String,Object> result = new HashMap<String,Object>();
		Query query = session.createQuery("SELECT new map(COUNT(d.id) as employees) FROM User d where d.company.companyId = :cid");
		query.setParameter("cid", comp.getCompanyId());
		//List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		session.flush();
		Query query2 = session.createQuery("SELECT new map( COUNT(d.enabled) as active) FROM User d where d.company.companyId = :cid AND d.enabled = :isenable");
		query2.setParameter("cid", comp.getCompanyId());
		query2.setParameter("isenable", 1);
		
		List list1 = query.list();
		List list2 = query2.list();
		result.put("total", list1);
		result.put("active", list2);
		return result;
	}
	
	@RequestMapping(value = "/a_miniwidget/administrator", method = RequestMethod.GET)
	public Map<String, Object> administrator(Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Session session = entityManager.unwrap(Session.class);
		Map<String,Object> result = new HashMap<String,Object>();
		Query query = session.createQuery("SELECT new map(COUNT(d.id) as administrator) FROM Admin d where d.company.companyId = :cid");
		query.setParameter("cid", comp.getCompanyId());
		//List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		session.flush();
		Query query2 = session.createQuery("SELECT new map( COUNT(d.enabled) as active) FROM Admin d where d.company.companyId = :cid AND d.enabled = :isenable");
		query2.setParameter("cid", comp.getCompanyId());
		query2.setParameter("isenable", 1);
		
		List list1 = query.list();
		List list2 = query2.list();
		result.put("total", list1);
		result.put("active", list2);
		return result;
	}
	
	@RequestMapping(value = "/a_miniwidget/vehicle", method = RequestMethod.GET)
	public Map<String, Object> vehicle(Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Session session = entityManager.unwrap(Session.class);
		Map<String,Object> result = new HashMap<String,Object>();
		Query query = session.createQuery("SELECT new map(COUNT(d.vehicleId) as vehicle) FROM Vehicle d where d.company.companyId = :cid");
		query.setParameter("cid", comp.getCompanyId());
		//List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		session.flush();
		Query query2 = session.createQuery("SELECT new map( COUNT(d.enabled) as active) FROM Vehicle d where d.company.companyId = :cid AND d.enabled = :isenable");
		query2.setParameter("cid", comp.getCompanyId());
		query2.setParameter("isenable", 1);
		
		List list1 = query.list();
		List list2 = query2.list();
		result.put("total", list1);
		result.put("active", list2);
		return result;
	}
	
	@RequestMapping(value = "/a_miniwidget/group", method = RequestMethod.GET)
	public Map<String, Object> group(Principal principal) {
		Company comp = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal)).get().getCompany();
		Session session = entityManager.unwrap(Session.class);
		Map<String,Object> result = new HashMap<String,Object>();
		Query query = session.createQuery("SELECT new map(COUNT(d.groupId) as groups) FROM Groups d where d.company.companyId = :cid");
		query.setParameter("cid", comp.getCompanyId());
		//List<Map<String,Object>> list = (List<Map<String,Object>>)query.list();
		session.flush();
		Query query2 = session.createQuery("SELECT new map( COUNT(d.enabled) as active) FROM Groups d where d.company.companyId = :cid AND d.enabled = :isenable");
		query2.setParameter("cid", comp.getCompanyId());
		query2.setParameter("isenable", 1);
		
		List list1 = query.list();
		List list2 = query2.list();
		result.put("total", list1);
		result.put("active", list2);
		return result;
	}

}
