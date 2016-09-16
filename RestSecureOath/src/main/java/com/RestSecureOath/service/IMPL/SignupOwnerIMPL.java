package com.RestSecureOath.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.repo.CompanyRepository;
import com.RestSecureOath.repo.OwnerRepository;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.requestdto.UserRegDto;
import com.RestSecureOath.service.SignupOwner;

@Service
@Scope("prototype")
public class SignupOwnerIMPL implements SignupOwner{
	
	final OwnerRepository orepository;
	final CompanyRepository crepository;
	final UserRepositoryX userRepositoryX;
	
	@Autowired
	public SignupOwnerIMPL(OwnerRepository ownerRepository,CompanyRepository companyRepository,UserRepositoryX userRepositoryX){
		this.orepository = ownerRepository;
		this.crepository = companyRepository;
		this.userRepositoryX= userRepositoryX;
	}

	@Override
	public String signup(UserRegDto owner){
		
		if(orepository.findByUserName(owner.getUserName())
				.orElse(new Owner()).getUserId()!= null){

			return "fail";
		}
		Company comp = crepository.save(new Company());
		orepository.save(
				new Owner(owner.getUserName(),
						owner.getPassword(),
						owner.getEmail(),
						owner.getFirstName(),
						owner.getLastName(),
						1, comp,null));
		return "done";
	};
	
	

}


