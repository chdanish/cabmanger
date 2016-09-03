package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.Refuel;
import com.RestSecureOath.domain.Ride;

@Repository
public interface RefuelRepository extends CrudRepository<Refuel, Long>{
	Optional<Refuel> findByRefuelId(long id);
}
