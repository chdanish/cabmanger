package com.RestSecureOath.controller.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.service.*;

import com.RestSecureOath.requestdto.UserRegDto;

@RestController
public class SignupCTRL {
	
	@Autowired
	private SignupOwner SignupOwner;
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public Map<String,String> signup(@RequestBody  UserRegDto owner){
		
		System.out.println(owner);
		/*map.entrySet().parallelStream().filter(e->e.getKey()!=null&&e.getValue()!=null)
		.filter(e->e.getKey()!=""&&e.getValue()!="").forEach(System.out::println);*/

		Map<String,String> model = new HashMap<String,String>();
		model.put("status", SignupOwner.save(owner));
		model.put("instruction", "**Please verify your email before signing in.");

		return model;		
	}

}
