package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Dashboard;

@Repository
@Transactional
public interface DashboardRepositoryX extends CrudRepository<Dashboard, Long>,
				QueryDslPredicateExecutor<Dashboard>				
				{
	Optional<Dashboard> findByName(String name);
	
	Optional<Dashboard> findByNameAndUserUserId(String name,Long id);
}
