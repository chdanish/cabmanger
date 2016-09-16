package com.RestSecureOath.config;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.util.Base64Utils;

import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Admin;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Distance_Unit;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Fuel_Unit;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.domain.Refuel;
import com.RestSecureOath.domain.Ride;
import com.RestSecureOath.domain.Roles;
import com.RestSecureOath.domain.Vehicle;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.AdminRepository;
import com.RestSecureOath.repo.CompanyRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.repo.OwnerRepository;
import com.RestSecureOath.repo.RefuelRepository;
import com.RestSecureOath.repo.RideRepository;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.util.StorageProperties;



@SpringBootApplication
@EntityScan("com.RestSecureOath.domain")
@EnableJpaRepositories("com.RestSecureOath.repo")
@ComponentScan("com.RestSecureOath")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(StorageProperties.class)
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
	 @Autowired
	    public DataSource dataSource;

	@Bean
	CommandLineRunner init(StorageService storageService) {
			return (args) -> {
	            storageService.deleteAll();
	            storageService.init();
			};
	}


	@Bean
	@Order(-100)
	public CommandLineRunner driverdemo(DriverRepository repository,CompanyRepository crepository,
			OwnerRepository orepository,VehicleRepository vrepository,ActivityRepository arepository,
			RideRepository rrepository,RefuelRepository rerepository,AdminRepository adrepository,ApplicationContext ctx) {
		return (args) -> {
			File file = new File("C:\\download.png");
			byte[] bFile = new byte[(int) file.length()];
			
	        try {
		     FileInputStream fileInputStream = new FileInputStream(file);
		     //convert file into array of bytes
		     fileInputStream.read(bFile);
		     fileInputStream.close();
	        } catch (Exception e) {
		     e.printStackTrace();
	        }
			String snap = Base64Utils.encodeToString(bFile);
			//Create Owner with default company
			Company comp = crepository.save(new Company());
			orepository.save(new Owner("owner", "password", "email", "firstName", "lastName", 1, comp,snap));
			Company comp2 = crepository.save(new Company());
			orepository.save(new Owner("danish", "danish", "danish", "danish", "danish", 1, comp2,snap));
			//Retirive Company from saved owner and set param 
			Owner owner = orepository.findByUserName("owner").get();
			Company compx =crepository.save(owner.getCompany());
			compx.setName("RANOP");
			compx.setRegisration("1924389-21109823");
			compx.setFuelunit(Fuel_Unit.LITRE);
			compx.setDistanceunit(Distance_Unit.KILOMETER);
			compx =crepository.save(compx);
			
			//Add driver to company
			repository.save(new Driver("driverusername1", "password1", "email1", "firstName", "lastName", 1,compx,null, null, null, null, null, null, null));
			repository.save(new Driver("driverusername2", "password2", "email2", "firstName", "lastName", 1,compx,null, null, null, null, null, null, null));
			
			//Add Admin officer to company
			adrepository.save(new Admin("adminUSER", "password", "email", "firstName", "lastName", 1,compx,snap, null, null, null));
			adrepository.save(new Admin("adminUSER2", "password2", "email2", "firstName2", "lastName2", 1,compx,snap, null, null, null));
			
			//Add vehicle to the company
			vrepository.save(new Vehicle("Toyota", 2016l, compx));
			vrepository.save(new Vehicle("Honda" , 2015l, compx));
			
			//Start new activity
			Driver driver= repository.findByUserName("driverusername1").get();
			driver.setRoles(Roles.ADMIN);
			repository.save(driver);
			Vehicle vehicle= vrepository.findOne(1l);
			Vehicle vehicle2= vrepository.findOne(2l);
			arepository.save(new Activity(driver, vehicle, 329848392l, bFile));
			arepository.save(new Activity(driver, vehicle2, 56448392l, bFile));
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
