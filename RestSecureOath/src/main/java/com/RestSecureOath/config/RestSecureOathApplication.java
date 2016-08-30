package com.RestSecureOath.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;


@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.RestSecureOath.domain")
@EnableJpaRepositories("com.RestSecureOath.repo")
@ComponentScan("com.RestSecureOath")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecureOathApplication extends GlobalMethodSecurityConfiguration {
 
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

	public static void main(String[] args) {
		SpringApplication.run(RestSecureOathApplication.class, args);
	}
	
	
	
}
