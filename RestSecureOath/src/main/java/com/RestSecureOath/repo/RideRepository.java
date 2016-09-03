package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Long>{
	Optional<Ride> findByRideID(long id);
}
