package com.RestSecureOath.controller.web;

import java.security.Principal;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.RestSecureOath.domain.Dashboard;
import com.RestSecureOath.domain.Dashboard_bar;
import com.RestSecureOath.domain.QDashboard;
import com.RestSecureOath.domain.QDashboard_bar;
import com.RestSecureOath.domain.QWidget;
import com.RestSecureOath.domain.User;
import com.RestSecureOath.domain.Widget;
import com.RestSecureOath.domain.Widgettype;
import com.RestSecureOath.repo.DashboardRepositoryX;
import com.RestSecureOath.repo.Dashboard_barRepositoryX;
import com.RestSecureOath.repo.UserRepositoryX;
import com.RestSecureOath.repo.WidgetRepositoryX;
import com.RestSecureOath.util.SecurityUtils;
import com.querydsl.jpa.impl.JPAQuery;


@RestController
public class DashboardCtrl {
	
	private final DashboardRepositoryX dashboardRepository;
	
	private final UserRepositoryX userRepository;
	
	private final Dashboard_barRepositoryX dashboard_barRepositoryX;
	
	private final WidgetRepositoryX widgetRepositoryX;

	private EntityManager entityManager;
	
	
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
	public DashboardCtrl(DashboardRepositoryX dashboardRepository, UserRepositoryX userRepository,
			Dashboard_barRepositoryX dashboard_barRepositoryX,WidgetRepositoryX widgetRepositoryX) {
		super();
		this.dashboardRepository = dashboardRepository;
		this.userRepository = userRepository;
		this.dashboard_barRepositoryX = dashboard_barRepositoryX;
		this.widgetRepositoryX  =  widgetRepositoryX;
	}
	
	@RequestMapping(value = "/dashboard/addbar", method = RequestMethod.GET)
	public Map<String,Object> addbar(Principal principal){
		Map<String,Object> map = new HashMap<>();
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		QDashboard_bar bar = QDashboard_bar.dashboard_bar;
		long x = 1;
		QDashboard dash = QDashboard.dashboard;
		final JPAQuery<Dashboard_bar> query = new JPAQuery<>(entityManager);
		if(userm.isPresent()&&!userm.get().getDashboard().isEmpty()){
			Long maxorder = query.from(bar).select(bar.bar_order.max())
			.where(bar.dashboard.dashboardid.eq(userm.get().getCompany().getCompanyId())).fetchFirst();
			 maxorder = maxorder!=null ? maxorder : null ;
			if(maxorder ==null){
				dashboard_barRepositoryX.save(new Dashboard_bar(1l,userm.get().getDashboard()
						.stream().filter(e -> e!=null).findFirst().get() ));
			}else dashboard_barRepositoryX.save(new Dashboard_bar(maxorder+1,userm.get().getDashboard()
							.stream().filter(e -> e!=null).findFirst().get() ));
		}
		map.put("status", dashboard_barRepositoryX.findByDashboardDashboardid(userm.get().getDashboard()
				.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
		return map;			
	}
	
	@RequestMapping(value = "/dashboard/addwidgettobar/{data}", method = RequestMethod.GET)
	public Map<String,Object> addwidgettobar(Principal principal,@PathVariable String data){
		String[] str = data.split("_");
		Map<String,Object> map = new HashMap<>();
		final JPAQuery<Dashboard_bar> query = new JPAQuery<>(entityManager);
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		QWidget widget = QWidget.widget;		
		Long maxorder = query.from(widget).select(widget.widget_order.max())
				.where(widget.dashboard_bar.dashboard.dashboardid.eq(userm.get().getCompany().getCompanyId())
						.and(widget.dashboard_bar.dashboardbarid.eq(Long.valueOf(str[1])))).fetchFirst();
		
		
		for(int i = 0;i< Widgettype.values().length;i++){
			if(str[0].equals(Widgettype.values()[i].toString())){
				Dashboard_bar bar  = dashboard_barRepositoryX.findOne(Long.valueOf(str[1]));
				Set<Widget> widgs = bar.getWidget();
				if(widgs.isEmpty()) {
					widgs.add(new Widget(1l, bar, Widgettype.values()[i]));
				} else if(!widgs.isEmpty() && maxorder!=null){
					widgs.add(new Widget(maxorder+1, bar, Widgettype.values()[i]));
				}
				bar.setWidget(widgs);
				dashboard_barRepositoryX.save(bar);
			}
		}		
		map.put("status", dashboard_barRepositoryX.findByDashboardDashboardid(userm.get().getDashboard()
				.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
		return map;			
	}
	
	@RequestMapping(value = "/dashboard/deletebar/{id}", method = RequestMethod.DELETE)
	public Map<String,Object> deletebar(Principal principal,@PathVariable long id){
		Map<String,Object> map = new HashMap<>();
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		if(dashboard_barRepositoryX.exists(id)){
			dashboard_barRepositoryX.delete(id);
			map.put("status","done");
	        return map;
		};		
        map.put("status","fail");
        return map;			
	}
	
	@RequestMapping(value = "/dashboard/getbar", method = RequestMethod.GET)
	public Map<String,Object> getbar(Principal principal){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		Map<String,Object> map = new HashMap<>();
		map.put("status", dashboard_barRepositoryX.findByDashboardDashboardid(userm.get().getDashboard()
						.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
		return map;		
	}
	
	

	@RequestMapping(value = "/dashboard/addwidget", method = RequestMethod.POST)
	public Map<String,Object> getView(Principal principal){
		Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
		//userm.getDashboard().isEmpty()
		if(userm.isPresent() && !dashboardRepository.findByNameAndUserUserId("Default", userm.get().getUserId()).isPresent()){
			Dashboard dashboard  = new Dashboard(userm.get(),"Default");
			Dashboard dashboard2 = dashboardRepository.save(dashboard);
		}
		return null;		
	}
	
	@Controller
	class A_dashboardView{
		@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
		public String getView(Principal principal,Model model){
			Optional<User> userm = userRepository.findByUserName(SecurityUtils.getLoggedInUserName(principal));
			//userm.getDashboard().isEmpty()
			if(userm.isPresent() && !dashboardRepository.findByNameAndUserUserId("Default", userm.get().getUserId()).isPresent()){
				Dashboard dashboard  = new Dashboard(userm.get(),"Default");
				Dashboard dashboard2 = dashboardRepository.save(dashboard);
				/*dashboard_barRepositoryX.save(new Dashboard_bar(1l, dashboard2));
				dashboard_barRepositoryX.save(new Dashboard_bar(2l, dashboard2));
				dashboard_barRepositoryX.save(new Dashboard_bar(3l, dashboard2));*/
				model.addAttribute("dashboardid", Long.toString(dashboard2.getDashboardid()));
			}else{
				model.addAttribute("dashboardid", Long.toString(userm.get().getDashboard()
						.stream().filter(e -> e!=null).findFirst().get().getDashboardid()));
			}
			
			return "dashboard";		
		}		
	}

}
