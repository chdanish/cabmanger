package com.RestSecureOath.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.WebUtils;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ApplicationContext appContext;

	@Autowired
	AuthenticationManagerBuilder authenticationManager;
	
	AuthenticationEntryPoint authentry = (req,res,auth)->{res.sendRedirect("http://localhost:8080/login");return;};
	AccessDeniedHandler accessdeniedhdlr= (req,res,auth)->{res.sendRedirect("http://localhost:8080/login");return;};
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
		web.setApplicationContext(appContext);
		super.configure(web);
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.parentAuthenticationManager((authentication)->authenticationManager.getOrBuild().authenticate(authentication));
    }
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() 
      throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.exceptionHandling().accessDeniedHandler(accessdeniedhdlr).authenticationEntryPoint(authentry)
    	.and()
			.formLogin().loginPage("/login").permitAll()
			//.successHandler(savedredir)
			.failureUrl("http://localhost:8080/login")
		.and()
		.antMatcher("/**").authorizeRequests().antMatchers("/api/**","/login","/signup","/static","/static/**").permitAll()
			.anyRequest().authenticated()
    	.and()
			.csrf().csrfTokenRepository(csrfTokenRepository())
		.and()
			.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
    	super.configure(http);
    }
	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
						.getName());
				if (csrf != null) {
					Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
					String token = csrf.getToken();
					if (cookie == null || token != null
							&& !token.equals(cookie.getValue())) {
						cookie = new Cookie("XSRF-TOKEN", token);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
				filterChain.doFilter(request, response);
			}
		};
	}
    private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		LazyCsrfTokenRepository rep=new LazyCsrfTokenRepository(repository);

		return rep;
	}
}
