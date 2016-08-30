package com.RestSecureOath.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserName(String username);

}
