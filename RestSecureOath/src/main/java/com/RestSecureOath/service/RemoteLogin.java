package com.RestSecureOath.service;

import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

public interface RemoteLogin {

	OAuth2RestTemplate restTemplate(String username, String password);



}
