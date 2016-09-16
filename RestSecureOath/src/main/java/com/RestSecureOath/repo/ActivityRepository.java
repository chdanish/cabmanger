package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Activity;

@Repository
@Transactional
public interface ActivityRepository extends CrudRepository<Activity, Long>{
	Optional<Activity> findByActivityID(long activityID);
}
