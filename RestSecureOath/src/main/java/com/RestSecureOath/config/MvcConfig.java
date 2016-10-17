package com.RestSecureOath.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.gwt.core.client.GWT;


@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/oauth/confirm_access").setViewName("authorize");
		registry.addViewController("/dashboard").setViewName("dashboard");
		registry.addViewController("/driver_dash").setViewName("driver_dash");
		registry.addViewController("/admin_dash").setViewName("admin_dash");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/a_setup").setViewName("a_setup");
		registry.addViewController("/a_modalDialog").setViewName("a_modalDialog");
		super.addViewControllers(registry);

	}
	
	 @Override
     public void configureViewResolvers(ViewResolverRegistry registry) {
 	   registry.freeMarker();
 	   
     }
	 
	 
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	            "classpath:/static/", "classpath:/static/css/", "classpath:/static/staticjs/","classpath:/gwt/" };
	 private static final String[] GWT_RESOURCE_LOCATIONS = {
	            "classpath:/public/" };

	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	
	        if (!registry.hasMappingForPattern("/static/**")) {
	            registry.addResourceHandler("/static/**").addResourceLocations(
	                    CLASSPATH_RESOURCE_LOCATIONS);
	        } 
	         if (!registry.hasMappingForPattern("/gwt/**")) {
	            registry.addResourceHandler("/public/**").addResourceLocations(
	            		GWT_RESOURCE_LOCATIONS);
	        }
	    }

}
