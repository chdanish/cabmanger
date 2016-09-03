package com.RestSecureOath.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long>{
}
