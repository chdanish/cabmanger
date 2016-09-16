package com.RestSecureOath.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.RestSecureOath.repo.UserRepositoryX;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	private UserRepositoryX userRepositoryx;
	
	@Bean
	protected UserDetailsService userDetailsService() {
		return s -> userRepositoryx.findByUserName(s)
				.map(user -> new org.springframework.security.core.userdetails.User(user.getUserName(),
							user.getPassword(),
							AuthorityUtils.createAuthorityList(user.getRoles().stream().map(g->"ROLE_"+g.toString()).toArray(size -> new String[size]))))
				.orElseThrow(() -> new UsernameNotFoundException("User with username: "+s) );
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

}