package com.RestSecureOath.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Driver;

@Repository
@Transactional
public interface DriverRepository extends CrudRepository<Driver, Long>
	,QueryDslPredicateExecutor<Driver>
	//,QuerydslBinderCustomizer<QUser> 
{               

	/*	@Override
		default public void customize(QuerydslBindings bindings, QUser user) {
		
		bindings.bind(user.username).first((path, value) -> path.contains(value))    
		bindings.bind(String.class)
		.first((StringPath path, String value) -> path.containsIgnoreCase(value)); 
		bindings.excluding(user.password);                                           
		}*/
	
	Optional<Driver> findByUserName(String username);	
	
	@Query(value="SELECT * FROM USER WHERE COMPANYID =?1 ", nativeQuery = true)
	Optional<List<Driver>> findALLby(long companyID);
	
	/*@Query(value="SELECT USERID ,CREATED_AT ,EMAIL ,FIRSTNAME ,LASTNAME ,USERNAME ,"
			+ "PASSWORD ,LICENSEID ,LICENSEID_EXPIRY ,NATIONALID ,NATIONALID_EXPIRY ,"
			+ "COMPANYID ,ENABLED ,LICENSEID_SNAP ,NATIONALID_SNAP ,REFUEL "
			+ " FROM USER WHERE DTYPE ='Driver' AND COMPANYID =?1",	nativeQuery=true)*/
	Optional<List<Driver>> findByNationalID(String str);
	

}
