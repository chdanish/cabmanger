package com.RestSecureOath.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;

import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
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
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import com.RestSecureOath.domain.Activity;
import com.RestSecureOath.domain.Admin;
import com.RestSecureOath.domain.Company;
import com.RestSecureOath.domain.Distance_Unit;
import com.RestSecureOath.domain.Driver;
import com.RestSecureOath.domain.Fuel_Unit;
import com.RestSecureOath.domain.Groups;
import com.RestSecureOath.domain.Owner;
import com.RestSecureOath.domain.QActivity;
import com.RestSecureOath.domain.QVehicle;
import com.RestSecureOath.domain.Refuel;
import com.RestSecureOath.domain.Ride;
import com.RestSecureOath.domain.Roles;
import com.RestSecureOath.domain.Vehicle;
import com.RestSecureOath.repo.ActivityRepository;
import com.RestSecureOath.repo.AdminRepository;
import com.RestSecureOath.repo.CompanyRepository;
import com.RestSecureOath.repo.DriverRepository;
import com.RestSecureOath.repo.GenericRepositoryFactory;
import com.RestSecureOath.repo.GroupsRepository;
import com.RestSecureOath.repo.OwnerRepository;
import com.RestSecureOath.repo.RefuelRepository;
import com.RestSecureOath.repo.RideRepository;
import com.RestSecureOath.repo.VehicleRepository;
import com.RestSecureOath.service.StorageService;
import com.RestSecureOath.util.StorageProperties;
import com.querydsl.core.types.Predicate;



@SpringBootApplication
@EntityScan("com.RestSecureOath.domain")
@EnableJpaRepositories(basePackages = {"com.RestSecureOath.repo"},entityManagerFactoryRef="entityManagerFactory")
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

	private EntityManager entityManager;

    @PersistenceContext(type=PersistenceContextType.TRANSACTION)
    public void setEntityManager(EntityManager entityManager) {
        this. entityManager = entityManager;
    }
    
	
	@Bean
	@Order(-100)
	public CommandLineRunner driverdemo(DriverRepository repository,CompanyRepository crepository,
			OwnerRepository orepository,VehicleRepository vrepository,ActivityRepository arepository,
			RideRepository rrepository,RefuelRepository rerepository,AdminRepository adrepository
			,ApplicationContext ctx,GroupsRepository grepository,LocalContainerEntityManagerFactoryBean entityManagerFactoryBean,
			//LocalSessionFactoryBean sessionFactoryx,HibernateTransactionManager htransactionManager,
			JpaContext context
			//,JpaTransactionManager transactionManager
			) {
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
	        /*EntityManager em_activity = context.getEntityManagerByManagedType(Activity.class);
	        EntityManager em_vehicle = context.getEntityManagerByManagedType(Vehicle.class);*/
	       //## EntityManager em = entityManagerFactoryBean.getObject().createEntityManager();
//			String snap = Base64Utils.encodeToString(bFile);
			//Create Owner with default company
			Company comp = crepository.save(new Company());
			comp =crepository.findOne(comp.getCompanyId());
			//Company comper= em.merge(comp);
			Groups dgroup = grepository.save(new Groups("Default", comp));
			Groups ogroup = grepository.save(new Groups("Owner", comp));
			
			orepository.save(new Owner("owner", "password", "email", "firstName", "lastName", 1, comp,ogroup));
			Company comp2 = crepository.save(new Company());
			Groups dgroup2 = grepository.save(new Groups("Default", comp2));
			orepository.save(new Owner("danish", "danish", "danish", "danish", "danish", 1, comp2,dgroup2));
			//Retirive Company from saved owner and set param 
			Owner owner = orepository.findByUserName("owner").get();
			Company compx =crepository.save(owner.getCompany());
			compx.setName("RANOP");
			compx.setRegisration("1924389-21109823");
			compx.setFuelunit(Fuel_Unit.LITRE);
			compx.setDistanceunit(Distance_Unit.KILOMETER);
			compx =crepository.save(compx);
			
			//Add driver to company
			repository.save(new Driver("driverusername1", "password1", "email1", "firstName", "lastName", 1,compx,dgroup));
			repository.save(new Driver("driverusername2", "password2", "email2", "firstName", "lastName", 1,compx,dgroup));
			repository.save(new Driver("driver1username1", "password1", "email1", "firstName", "lastName", 1,comp2,dgroup2));
			repository.save(new Driver("driver2username2", "password2", "email2", "firstName", "lastName", 1,comp2,dgroup2));
			
			//Add Admin officer to company
			adrepository.save(new Admin("adminUSER", "password", "email", "firstName", "lastName", 1,compx,dgroup));
			adrepository.save(new Admin("adminUSER2", "password2", "email2", "firstName2", "lastName2", 1,compx,dgroup));
			
			//Add vehicle to the company
		
			Vehicle v1=vrepository.save(new Vehicle("Toyota", "Altis", compx,dgroup,"max-1223"));
			Vehicle v2=vrepository.save(new Vehicle("Honda" , "Civic", compx,dgroup,"lmx-1597"));
			Vehicle v3=vrepository.save(new Vehicle("Toyota", "Altis", comp2,dgroup2,"rmx-193"));
			Vehicle v4=vrepository.save(new Vehicle("Honda" , "Civic", comp2,dgroup2,"lov-1245"));
			
			//Start new activity
			Driver driver= repository.findByUserName("driverusername1").get();
			Driver driver2= repository.findByUserName("driverusername2").get();
			driver.setRoles(Roles.ADMIN);
			repository.save(driver);
			v1= vrepository.findOne(v1.getVehicleId());
			v2= vrepository.findOne(v2.getVehicleId());
			/*Vehicle vehicle = em.find(Vehicle.class, 1l);
			Vehicle vehicle2= em.find(Vehicle.class, 2l);*/
			//em.persist(vehicle);em.persist(vehicle2);
			//v1.setVehicleId(null);
			
			//act1.setVehicle(vehicle);
			//arepository.save(act1);
			/*Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
			SessionFactory sessionFactory = htransactionManager.getSessionFactory();
			  Session session = sessionFactory.openSession();
			  session.beginTransaction();
			  Vehicle vh= new Vehicle("mata", "Altis", comp,dgroup,"max-1223");
			   session.save(vh);
			   Vehicle vh2= session.get(Vehicle.class, 5l);
			   session.saveOrUpdate(vh2);
			  Activity actx= new Activity(driver, vh2 , 329848392l, "");
			  session.save(new Vehiclex());
			  Vehiclex vxx =session.get(Vehiclex.class,1l);
			  actx.setVehiclex(vxx);
			  vxx.setActivity(actx);
			  session.save(actx);
			  if(!v1.isNew()){
					System.out.println("PASS");
					v1=em.merge(v1);
					em.merge(new Activity(driver2, vehicle, 56448392l, ""));
					//RepositoryFactorySupport factory = new GenericRepositoryFactory(context.getEntityManagerByManagedType(Activity.class));
					//ActivityRepository rep = factory.getRepository(ActivityRepository.class);
					entityManager.getEntityManagerFactory().createEntityManager().merge(v1);
					Activity act1= new Activity(driver, vh2 , 329848392l, "");
					
					//act1.setVehicle(v1);act2.setVehicle(v2);
					session.save(new Vehiclex());
					  Vehiclex vmm =session.get(Vehiclex.class,1l);
					  act1.setVehiclex(vmm);
					//act2.setVehiclex(vxx);
					session.save(act1);
					//session.save(null);
					Vehiclex vdd =session.get(Vehiclex.class,1l);
					//session.save(new Vehiclex());
					Activity act2= new Activity(driver, vh2 , 32948392l, "");
					session.merge(vdd);
					vdd.setActivity(act2);
					session.merge(vdd);
					act2.setVehiclex(vdd);
					session.merge(vdd);
					session.persist(vdd);
					session.merge(vdd);
					session.save(act2);
					arepository.save(new Activity(driver2, v1, 56448392l, ""));
					arepository.save(new Activity(driver2, v1, 56448392l, ""));
				} else System.out.println(v1.isNew());
			  session.getTransaction().commit();
			  session.close();*/
			Activity act1= new Activity(driver, v2 , 329848392l, "");
			Activity act2= new Activity(driver, v2 , 329848392l, "");
			Set<Activity> actset= new HashSet<Activity>(0);
			actset.add(act1);actset.add(act2);
			v2.setActivity(actset);
			v2=vrepository.save(v2);
			QActivity predact = QActivity.activity;
			Predicate predicate = predact.vehicle.vehicleId.eq(v2.getVehicleId());
			
			List<Activity> activity= new ArrayList<>(10);
			for (Activity act : v2.getActivity()) {
				activity.add(act);System.out.println(act);
			}
			activity.get(0).setEndReading(32432433l);
			activity.get(0).setEndReading_snap("");
			arepository.save(activity.get(0));
			rrepository.save(new Ride(activity.get(0), 230l));
			rrepository.save(new Ride(activity.get(0), 530l));
			rrepository.save(new Ride(activity.get(0), 340l));
			rrepository.save(new Ride(activity.get(0), 276l));
			rrepository.save(new Ride(activity.get(0), 912l));
			arepository.save(activity.get(0));
			Set<Refuel> refuels = new HashSet<Refuel>(0);
			refuels.add(new Refuel( activity.get(0)));
			refuels.add(new Refuel(activity.get(0)));
			activity.get(0).setRefuel(refuels);
			arepository.save(activity.get(0));
			EntityManager em =entityManagerFactoryBean.getObject().createEntityManager();
			
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
