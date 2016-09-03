package com.RestSecureOath.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.RestSecureOath.repo.UserRepositoryX;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserRepositoryX userRepositoryx;
	
	@Bean
	protected UserDetailsService userDetailsService2() {
		return s -> userRepositoryx.findByUserName(s)
				.map(user -> new org.springframework.security.core.userdetails.User(user.getUserName(),
							user.getPassword(),
							AuthorityUtils.createAuthorityList(user.getRoles().stream().map(g->g.toString()).toArray(size -> new String[size]))))
				.orElseThrow(() -> new UsernameNotFoundException("User with username: "+s) );
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.userDetailsService(userDetailsService2())
        /*.inMemoryAuthentication()
          .withUser("john").password("123").roles("ADMIN")*/
          ;
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() 
      throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/loginx","/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and().csrf().disable();
    }
}

