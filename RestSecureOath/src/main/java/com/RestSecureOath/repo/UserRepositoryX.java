package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.User;

@Repository
public interface UserRepositoryX extends CrudRepository<User, Long>{
	Optional<User> findByUserName(String username);

}
