package com.RestSecureOath.repo;

import java.util.List;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Widgets;

@Repository
@Transactional
public interface WidgetsRepositoryX extends CrudRepository<Widgets, Long>,
				QueryDslPredicateExecutor<Widgets>				
				{
					
		List<Widgets> findByWidgetsid(Long id);	
}
