package com.RestSecureOath.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.repo.CompanyRepository;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.OwnerRepository;
import com.RestSecureOath.requestdto.UserRegDto;
import com.RestSecureOath.service.SignupOwner;

@Service
@Scope("prototype")
public class SignupOwnerIMPL implements SignupOwner{
	
	private final OwnerRepository orepository;
	private final CompanyRepository crepository;
	private final GroupsRepository grepository;
	
	@Autowired
	public SignupOwnerIMPL(OwnerRepository ownerRepository,CompanyRepository companyRepository
			,GroupsRepository grepository){
		this.orepository = ownerRepository;
		this.crepository = companyRepository;
		this.grepository = grepository;
	}

	@Override
	public String save(UserRegDto owner){
		
		if(orepository.findByUserName(owner.getUserName())
				.orElse(new Owner()).getUserId()!= null){

			return "fail";
		}
		Company comp = crepository.save(new Company());
		Groups dgroup = grepository.save(new Groups("Default", comp,1)); //Do not delete, Default group is for All newly added users/vehicles 
		Groups ogroup = grepository.save(new Groups("Owner", comp,1));   //Do not delete, Owner group is only for Company owners
		Owner newowner = orepository.save(
				new Owner(owner.getUserName(),
						owner.getPassword(),
						owner.getEmail(),
						owner.getFirstName(),
						owner.getLastName(),
						1, comp,ogroup));
		if (orepository.exists(newowner.getUserId())) {
			return "done";
		} else {
			return "fail";
		}
		
		
	};
	
	

}


