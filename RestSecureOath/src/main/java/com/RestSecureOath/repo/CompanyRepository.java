package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.RestSecureOath.domain.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{
	Optional<Company> findByName(String name);
}
