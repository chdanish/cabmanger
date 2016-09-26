package com.RestSecureOath.config;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class MVCTest {
	
	static final int PORT = 8080;
	
	@Test
	public void rawFluxResponse() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		URI url = new URI("http://localhost:" + PORT + "/");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		System.out.println(response);
//		assertEquals("Hello!", response.getBody());
		assertThat( response.getBody(), containsString("<!doctype html>"));
	}
	
	@Test
	public void admins() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		URI url = new URI("http://localhost:" + PORT + "/a_admins");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		System.out.println(response);
//		assertEquals("Hello!", response.getBody());
		assertThat( response.getBody(), containsString("<!doctype html>"));
	}
	
	@Test
	public void vehicles() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		URI url = new URI("http://localhost:" + PORT + "/vehicles");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		System.out.println(response);
//		assertEquals("Hello!", response.getBody());
		assertThat( response.getBody(), containsString("<!doctype html>"));
	}
	
	@Test
	public void drivers() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		URI url = new URI("http://localhost:" + PORT + "/a_drivers");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		System.out.println(response);
//		assertEquals("Hello!", response.getBody());
		assertThat( response.getBody(), containsString("<!doctype html>"));
	}
	
	/*@Test
	public void groups() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		URI url = new URI("http://localhost:" + PORT + "/groups");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		System.out.println(response);
//		assertEquals("Hello!", response.getBody());
		assertThat( response.getBody(), containsString("<!doctype html>"));
	}*/

}
