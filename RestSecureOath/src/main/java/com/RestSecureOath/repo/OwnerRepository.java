package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Owner;

@Repository
@Transactional
public interface OwnerRepository extends CrudRepository<Owner, Long>{
	Optional<Owner> findByUserName(String username);
	
	
}
