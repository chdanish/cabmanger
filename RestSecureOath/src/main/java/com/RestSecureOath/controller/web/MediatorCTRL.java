package com.RestSecureOath.controller.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Roles;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.util.SecurityUtils;

@Controller
public class MediatorCTRL {
	
	@Autowired
	private UserRepositoryX urepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String localrouter(Principal principal){

		String username= SecurityUtils.getLoggedInUserName(principal);
		User owner=urepository.findByUserName(username).orElse(new User());
		Company comp = owner.getCompany();
		if(comp.getName()==null||comp.getName()==""||comp.getFuelunit()==null||comp.getDistanceunit()==null){
			return "redirect:/setup";
		}else if(owner.getRoles().contains(Roles.OWNER)){
			return "redirect:/dashboard";
		}else if(owner.getRoles().contains(Roles.ADMIN)){
			return "redirect:/admin_dash";
		}else if(owner.getRoles().contains(Roles.DRIVER)){
			return "redirect:/driver_dash";
		}
		

		return "redirect:/fail";	
	}

}
