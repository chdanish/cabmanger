package com.RestSecureOath.controller.api;

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
	
	String username,password;

    @RequestMapping(value = "/loginx", method = RequestMethod.POST)
    public Map<String, String> getResource(@RequestBody Map<String,String> authenticationRequest) {
    	

			authenticationRequest.entrySet().parallelStream()
			.filter(e-> e.getKey()!=null&&e.getKey()!="")
			.filter(e-> e.getValue()!=null&&e.getValue()!="")
			.reduce((t,u)->{
				if(t.getKey()=="username" && username ==null) username=t.getValue();
				if(u.getKey()=="password" && password ==null) password=u.getValue();
				return t;});

            Map<String, String> resource = new HashMap<String, String>();
            if(username !=null&& password!=null){
            resource.put("Authorization", remoteLogin.restTemplate(username, password).getAccessToken().getValue());
            username =null; password=null;
            return resource;
            }else  resource.put("fail", "Bad Input");
            return resource;
     }

}
