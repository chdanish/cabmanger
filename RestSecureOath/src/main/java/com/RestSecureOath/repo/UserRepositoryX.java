package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.User;

@Repository
@Transactional
public interface UserRepositoryX extends CrudRepository<User, Long>,
				QueryDslPredicateExecutor<User>				
				{
	Optional<User> findByUserName(String username);
	

}
