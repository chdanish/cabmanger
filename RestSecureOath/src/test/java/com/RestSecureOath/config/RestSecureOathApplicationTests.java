package com.RestSecureOath.config;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestSecureOathApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	static final int PORT = 8080;
	
	@Test
	public void rawFluxResponse() throws Exception {

		RestTemplate restTemplate = new RestTemplate();

		URI url = new URI("http://localhost:" + PORT + "/");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		System.out.println(response);
		assertEquals("Hello!", response.getBody());
	}

}
