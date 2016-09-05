package com.RestSecureOath.service.IMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.stereotype.Service;
import com.RestSecureOath.service.RemoteLogin;

@Service("remoteLogin")
public class RemoteLoginImpl implements RemoteLogin{
	
	
		
	
	@Value("${oauth.clientId:clientIdPassword}")
    private String clientId;
	@Value("${oauth.clientSecret:secret}")
    private String clientSecret;
	@Value("${oauth.grantType:password}")
    private String grantType;
    @Value("${oauth.token:http://localhost:8080/oauth/token}")
    private String tokenUrl;
	    
    @Override
    public OAuth2RestTemplate restTemplate(String username,String password) {
    	ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();        
        List <String> scopes = new ArrayList<String>();
        scopes.add("read");
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setGrantType(grantType);
        resource.setScope(scopes);
        resource.setUsername(username);
        resource.setPassword(password);
        resource.setAuthenticationScheme(AuthenticationScheme.form);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext());   
        return template;
    }
	    

}
