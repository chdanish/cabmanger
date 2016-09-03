package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.Driver;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long>{
	Optional<Driver> findByUserName(String username);

}
