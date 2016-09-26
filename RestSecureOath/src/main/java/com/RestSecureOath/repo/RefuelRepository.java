package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Refuel;

@Repository
@Transactional
public interface RefuelRepository extends CrudRepository<Refuel, Long>{
	Optional<Refuel> findByRefuelId(long id);
}
