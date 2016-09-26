package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Groups;

@Repository
@Transactional
public interface GroupsRepository extends CrudRepository<Groups, Long>
						,QueryDslPredicateExecutor<Groups>{
	
	Optional<Groups> findByName(String name);
	
	
	Optional<Groups> findByNameAndCompanyCompanyId(String name,Long id);

}
