package com.RestSecureOath.repo;

import java.util.List;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Widget;

@Repository
@Transactional
public interface WidgetRepositoryX extends CrudRepository<Widget, Long>,
				QueryDslPredicateExecutor<Widget>				
				{
					
		List<Widget> findByWidgetid(Long id);	
}
