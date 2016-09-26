package com.RestSecureOath.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.RestSecureOath.domain.Company;

@Repository
@Transactional
public interface CompanyRepository extends CrudRepository<Company, Long>{
	
	Optional<Company> findByName(String name);
	
	Optional<Company> findByCompanyId(Long id);
}
