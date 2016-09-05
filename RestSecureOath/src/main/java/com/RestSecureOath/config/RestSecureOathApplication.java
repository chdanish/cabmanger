package com.RestSecureOath.config;

import java.sql.SQLException;
import java.util.Optional;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Distance_Unit;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Fuel_Unit;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.domain.Refuel;
import com.RestSecureOath.domain.Ride;
import com.RestSecureOath.domain.Roles;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Vehicle;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.CompanyRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.repo.OwnerRepository;
import com.RestSecureOath.repo.RefuelRepository;
import com.RestSecureOath.repo.RideRepository;
import com.RestSecureOath.repo.UserRepository;
import com.RestSecureOath.repo.VehicleRepository;

import javassist.bytecode.ByteArray;



@SpringBootApplication
@EntityScan("com.RestSecureOath.domain")
@EnableJpaRepositories("com.RestSecureOath.repo")
@ComponentScan("com.RestSecureOath")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestSecureOathApplication extends GlobalMethodSecurityConfiguration {
 
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

	public static void main(String[] args) {
		//SpringApplication.run(RestSecureOathApplication.class, args);
		Object [] sources = {RestSecureOathApplication.class
				,SecurityConfig.class
				};
		SpringApplication.run(sources, args);

	}
	
	@Autowired
	Server h2WebServer;

	@Bean(destroyMethod = "stop", initMethod = "start")
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
	}
	@Bean
	public FreeMarkerConfigurationFactoryBean freemarkerConfiguration(){
		FreeMarkerConfigurationFactoryBean free = new FreeMarkerConfigurationFactoryBean();
		free.setTemplateLoaderPath("/WEB-INF/freemarker/");
		return free;
	}


	@Bean
	@Order(-100)
	public CommandLineRunner driverdemo(DriverRepository repository,CompanyRepository crepository,
			OwnerRepository orepository,VehicleRepository vrepository,ActivityRepository arepository,
			RideRepository rrepository,RefuelRepository rerepository) {
		return (args) -> {
			//Create Owner with default company
			Company comp = crepository.save(new Company());
			orepository.save(new Owner("owner", "password", "email", "firstName", "lastName", 1, comp));
			
			//Retirive Company from saved owner and set param 
			Owner owner = orepository.findByUserName("owner").get();
			Company compx =owner.getCompany();
			compx.setName("RANOP");
			compx.setRegisration("1924389-21109823");
			compx.setFuelunit(Fuel_Unit.LITRE);
			compx.setDistanceunit(Distance_Unit.KILOMETER);
			crepository.save(compx);
			
			//Add driver to company
			repository.save(new Driver("driverusername1", "password1", "email1", "firstName", "lastName", 1,owner.getCompany(), null, null, null, null, null, null));
			repository.save(new Driver("driverusername2", "password2", "email2", "firstName", "lastName", 1,owner.getCompany(), null, null, null, null, null, null));
			
			//Add vehicle to the company
			vrepository.save(new Vehicle("Toyota", 2016l, compx));
			vrepository.save(new Vehicle("Honda" , 2015l, compx));
			
			//Start new activity
			Driver driver= repository.findByUserName("driverusername1").get();
			driver.setRoles(Roles.ADMIN);
			repository.save(driver);
			Vehicle vehicle= vrepository.findOne(1l);
			Vehicle vehicle2= vrepository.findOne(2l);
			arepository.save(new Activity(driver, vehicle, 329848392l, new byte[10]));
			arepository.save(new Activity(driver, vehicle2, 56448392l, new byte[10]));
			Activity activity = arepository.findOne(1l);
			Activity activity2 = arepository.findOne(2l);
			activity.setEndReading(32432433l);
			activity.setEndReading_snap(new byte[10]);
			rrepository.save(new Ride(activity, 230l));
			rrepository.save(new Ride(activity, 530l));
			rrepository.save(new Ride(activity2, 340l));
			rrepository.save(new Ride(activity, 276l));
			rrepository.save(new Ride(activity2, 912l));
			rerepository.save(new Refuel(vehicle2, activity2, driver));
			rerepository.save(new Refuel(vehicle2, activity, driver));
		};
	}
	
	//http://blogs.sourceallies.com/2012/02/hibernate-date-vs-timestamp/
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration
	    extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId("RestSecureOath");
		}
		
	  @Override
	  public void configure(HttpSecurity http) throws Exception {
	    http
	      .antMatcher("/api/**")
	      .authorizeRequests().anyRequest().authenticated();
	  }
	}
	
}
