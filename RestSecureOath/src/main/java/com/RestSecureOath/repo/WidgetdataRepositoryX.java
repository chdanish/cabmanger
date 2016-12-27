package com.RestSecureOath.repo;

import java.util.List;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Widgetdata;
import com.RestSecureOath.domain.Widgets;
import com.RestSecureOath.domain.Widgettype;

@Repository
@Transactional
public interface WidgetdataRepositoryX extends CrudRepository<Widgetdata, Long>,
				QueryDslPredicateExecutor<Widgetdata>				
				{
					
		List<Widgetdata> findByWidgetdataid(Long id);	
		
		Widgetdata findByType(Widgettype wid);
}
