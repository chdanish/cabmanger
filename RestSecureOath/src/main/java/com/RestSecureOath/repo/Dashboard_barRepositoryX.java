package com.RestSecureOath.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Dashboard;
import com.RestSecureOath.domain.Dashboard_bar;

@Repository
@Transactional
public interface Dashboard_barRepositoryX extends CrudRepository<Dashboard_bar, Long>,
				QueryDslPredicateExecutor<Dashboard_bar>				
				{
					
		List<Dashboard_bar> findByDashboardDashboardid(Long id);	
}
