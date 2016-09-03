package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long>{
	Optional<Owner> findByUserName(String username);
	
	
}
