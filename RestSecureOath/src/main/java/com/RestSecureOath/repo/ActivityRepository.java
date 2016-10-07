package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.RestSecureOath.domain.Activity;

@Repository
@Transactional
/*@Scope(value="session")*/
public interface ActivityRepository extends CrudRepository<Activity, Long>,JpaRepository<Activity, Long>
					,QueryDslPredicateExecutor<Activity>				
					{
	Optional<Activity> findByActivityID(Long activityID);
}
