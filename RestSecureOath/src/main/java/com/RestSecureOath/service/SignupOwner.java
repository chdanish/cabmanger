package com.RestSecureOath.service;

import com.RestSecureOath.requestdto.UserRegDto;

@FunctionalInterface
public interface SignupOwner {
	
	public String save(UserRegDto owner);
	
}


