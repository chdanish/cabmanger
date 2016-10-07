package com.RestSecureOath.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Vehicle;

@Repository
@Transactional
public interface VehicleRepository extends CrudRepository<Vehicle, Long>,JpaRepository<Vehicle, Long>
				,QueryDslPredicateExecutor<User>{
}
