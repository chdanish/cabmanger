/*package com.RestSecureOath.config;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.RestSecureOath.server.StockPriceServiceImpl;

@Configuration
public class GwtConfig {
	
	@Bean
	public FilterRegistrationBean myFilter() throws ServletException {
		
		FilterRegistrationBean registration = new FilterRegistrationBean(filter,new ServletRegistrationBean(new DispatcherServlet(), "/gwt/*"));
		//registration.addServletRegistrationBeans(new ServletRegistrationBean(dispatcherServlet(), "/gwt/*"));
		
		registration.setFilter(filter);
		registration.addUrlPatterns("/gwt/*");
	    registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	    registration.setAsyncSupported(true);
	    registration.setEnabled(true);
	   
	    return registration;
	}
	@Autowired
	ApplicationContext ctx;
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration() {
		DispatcherServlet dispatcherServlet = new DispatcherServlet();
		WebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		StockPriceServiceImpl spi = new StockPriceServiceImpl();
		spi.setServletContext(applicationContext.getServletContext());
		
	    ServletRegistrationBean registration = new ServletRegistrationBean(spi);
	    //registration.setServlet(dispatcherServlet());
	    registration.addUrlMappings("/gwt/*");
	    registration.setName("GWT");
	    registration.setEnabled(true);
	    registration.setLoadOnStartup(1);
	    registration.getClass();
	    return registration;
	}
	
	//http://stackoverflow.com/questions/8943147/gwt-the-response-could-not-be-deserialized
	 	
	    public StockPriceServiceImpl dispatcherServlet() {
	    	StockPriceServiceImpl spi = new StockPriceServiceImpl();
	    	
	        return spi;
	    }

	 	Filter filter = new OncePerRequestFilter() {
			
			@Override
			public void destroy() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
					FilterChain filterChain) throws ServletException, IOException {
				HttpServletRequest servletRequest = (HttpServletRequest) request;
				HttpServletResponse servletResponse = (HttpServletResponse) response;
				String servletPath = servletRequest.getServletPath().toLowerCase();
				servletResponse.addHeader("Access-Control-Allow-Origin", "CHECK");
				filterChain.doFilter(request, response);
				return;
				
			}
		};
}
*/