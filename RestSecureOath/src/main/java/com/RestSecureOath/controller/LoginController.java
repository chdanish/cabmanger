package com.RestSecureOath.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.service.RemoteLogin;

@RestController
public class LoginController {

	@Autowired
	@Qualifier("remoteLogin")
	private RemoteLogin remoteLogin;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> getResource(@RequestBody Map<String,String> authenticationRequest) {
    	
    	Boolean usernamecheck = authenticationRequest.get("username") != null  & authenticationRequest.get("username") !=""  ? true : false ;
    	Boolean passwordcheck = authenticationRequest.get("password") != null  & authenticationRequest.get("password") !=""  ? true : false ;
    	if(usernamecheck & passwordcheck){
    		String username = authenticationRequest.get("username") ;
    		String password = authenticationRequest.get("password") ;
    		
        	
            Map<String, String> resource = new HashMap<String, String>();
            resource.put("Authorization", remoteLogin.restTemplate(username, password).getAccessToken().getValue());
            return resource;
    		
    	} else return null;
     }

}
