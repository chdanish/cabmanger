package com.RestSecureOath.controller.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.domain.Dashboard;
import com.RestSecureOath.domain.QDashboard;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Widgetdata;
import com.RestSecureOath.domain.Widgets;
import com.RestSecureOath.domain.Widgettype;
import com.RestSecureOath.repo.DashboardRepositoryX;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.WidgetdataRepositoryX;
import com.RestSecureOath.repo.WidgetsRepositoryX;
import com.RestSecureOath.util.SecurityUtils;
import com.querydsl.jpa.impl.JPAQuery;


@RestController
public class DashboardCtrl {
	
	private final DashboardRepositoryX dashboardRepository;
	
	private final UserRepositoryX userRepository;

	private EntityManager entityManager;
	
	private final WidgetsRepositoryX widgetsRepositoryX;
	
	private final WidgetdataRepositoryX widgetdataRepositoryX;
	
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
    public void setEntityManager(EntityManager entityManager) {
        this. entityManager = entityManager;
    }
	
	/**
	 * @param dashboardRepository
	 * @param userRepository
	 * @param dashboard_barRepositoryX
	 */
	@Autowired
	public DashboardCtrl(DashboardRepositoryX dashboardRepository, UserRepositoryX userRepository
			,WidgetsRepositoryX widgetsRepositoryX,WidgetdataRepositoryX widgetdataRepositoryX) {
		super();
		this.dashboardRepository = dashboardRepository;
		this.userRepository = userRepository;
		this.widgetsRepositoryX = widgetsRepositoryX;
		this.widgetdataRepositoryX= widgetdataRepositoryX;
	}
	
	/*Long maxorder = query.from(bar).select(bar.barorder.max())
			.where(bar.dashboard.dashboardid.eq(userm.get().getCompany().getCompanyId())).fetchFirst();
			 maxorder = maxorder!=null ? maxorder : null ;*/
	
	

	@RequestMapping(value = "/dashboard/addwidget/{type}", method = RequestMethod.POST)
	public Map<String,Object> getView(Principal principal,@PathVariable String type ){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		Map<String,Object> map = new HashMap<>();
		Dashboard dashboard  =dashboardRepository.findOne(userm.get().getDashboard().stream().findFirst().get().getDashboardid());
		Set<Widgets> widgets= dashboard.getWidgets();
		for(Widgettype wid : Widgettype.values()){
			if(type.equals(wid.toString())){
				Widgetdata widdata= widgetdataRepositoryX.findByType(wid);
				widgets.add(new Widgets(0l, 0l, 1l, 2l, widdata.getName(),widdata.getTag(), false, wid, dashboard));
			}
		}
		dashboard.setWidgets(widgets);
		Dashboard dashboard2 = dashboardRepository.save(dashboard);
		map.put("status",dashboardRepository.save(dashboard));
		return map;		
	}
	
	@RequestMapping(value = "/dashboard/deletewidget/{id}", method = RequestMethod.POST)
	public Map<String,Object> deletewidget(Principal principal,@PathVariable Long id ){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		Map<String,Object> map = new HashMap<>();
		widgetsRepositoryX.delete(id);
		map.put("status",dashboardRepository.findOne(userm.get().getDashboard().stream().findFirst().get().getDashboardid()));
		return map;		
	}
	
	@RequestMapping(value = "/dashboard/getdash", method = RequestMethod.GET)
	public Map<String,Object> getdash(Principal principal){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		Map<String,Object> map = new HashMap<>();
		if(userm.isPresent() && !dashboardRepository.findByNameAndUserUserId("Default", userm.get().getUserId()).isPresent()){
			Dashboard dashboard  = new Dashboard(userm.get(),"Default");
			Dashboard dashboard2 = dashboardRepository.save(dashboard);
		}
		map.put("status",dashboardRepository.findOne( userm.get().getDashboard()
				.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
		return map;		
	}
	
	@RequestMapping(value = "/dashboard/moves", method = RequestMethod.POST)
	public Map<String,Object> moves(Principal principal,@RequestBody ArrayList<Map<String,Object>> maps){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		maps.parallelStream().filter(e ->{return e !=null;})
		.forEach(mapr ->{
			Widgets wid = widgetsRepositoryX.findOne(Long.valueOf(mapr.get("widgetsid").toString()));		
			wid.setCol(Long.valueOf(mapr.get("col").toString()));
			wid.setRow(Long.valueOf(mapr.get("row").toString()));
			wid.setSizeX(Long.valueOf(mapr.get("sizeX").toString()));
			wid.setSizeY(Long.valueOf(mapr.get("sizeY").toString()));
			widgetsRepositoryX.save(wid);
		});
		
		Map<String,Object> map = new HashMap<>();
		map.put("status",dashboardRepository.findOne( userm.get().getDashboard()
				.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
		return map;		
	}
	
	@RequestMapping(value = "/dashboard/move", method = RequestMethod.POST)
	public Map<String,Object> move(Principal principal,@RequestBody Map<String,String> mapr){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		Widgets wid = widgetsRepositoryX.findOne(Long.valueOf(mapr.get("widgetsid").toString()));
		Map<String,Object> map = new HashMap<>();		
		wid.setCol(Long.valueOf(mapr.get("col").toString()));
		wid.setRow(Long.valueOf(mapr.get("row").toString()));
		wid.setSizeX(Long.valueOf(mapr.get("sizeX").toString()));
		wid.setSizeY(Long.valueOf(mapr.get("sizeY").toString()));
		widgetsRepositoryX.save(wid);
		map.put("status",dashboardRepository.findOne( userm.get().getDashboard()
				.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
		return map;		
	}
	
	@Controller
	class A_dashboardView{
		@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
		public String getView(Principal principal,Model model){
			Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
			//userm.getDashboard().isEmpty()
			if(userm.isPresent() && !dashboardRepository.findByNameAndUserUserId("Default", userm.get().getUserId()).isPresent()){
				Dashboard dashboard  = new Dashboard(userm.get(),"Default");
				Set<Widgets> widgets= new HashSet<Widgets>(0);
				Widgetdata emp,admin,vehicle,group;
				emp= widgetdataRepositoryX.findByType(Widgettype.widgetEmployee);
				admin=widgetdataRepositoryX.findByType(Widgettype.widgetAdministrator);
				vehicle=widgetdataRepositoryX.findByType(Widgettype.widgetVehicle);
				group=widgetdataRepositoryX.findByType(Widgettype.widgetGroup);
				widgets.add(new Widgets(0l, 0l, 1l, 2l, emp.getName(),emp.getTag(), false, emp.getType(), dashboard));
				widgets.add(new Widgets(2l, 0l, 1l, 2l, admin.getName(),admin.getTag(), false, admin.getType(), dashboard));
				widgets.add(new Widgets(4l, 0l, 1l, 2l, vehicle.getName(),vehicle.getTag(), false, vehicle.getType(), dashboard));
				widgets.add(new Widgets(6l, 0l, 1l, 2l, group.getName(),group.getTag(), false, group.getType(), dashboard));
				dashboard.setWidgets(widgets);
				Dashboard dashboard2 = dashboardRepository.save(dashboard);
				
				
				
				model.addAttribute("dashboardid", Long.toString(dashboard2.getDashboardid()));
			}else{
				model.addAttribute("dashboardid", Long.toString(userm.get().getDashboard()
						.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
			}
			
			return "dashboard";		
		}		
	}

}
