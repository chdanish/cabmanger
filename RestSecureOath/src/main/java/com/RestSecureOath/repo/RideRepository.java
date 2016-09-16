package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Ride;

@Repository
@Transactional
public interface RideRepository extends CrudRepository<Ride, Long>{
	Optional<Ride> findByRideID(long id);
}
