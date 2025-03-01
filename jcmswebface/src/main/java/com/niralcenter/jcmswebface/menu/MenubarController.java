package com.niralcenter.jcmswebface.menu;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.niralcenter.jcmswebface.common.LoginInfo;
import com.niralcenter.jcmswebface.common.ModuleDefs;
import com.niralcenter.jcmswebface.common.ServerDefs;
import com.niralcenter.jcmswebface.model.Menu;
import com.niralcenter.jcmswebface.model.User;




@Controller
public class MenubarController {

	private static final Logger logger = LogManager.getLogger(MenubarController.class);
	
	@Autowired
	MenubarService menubarService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView gotoIndexPage(HttpSession httpSession) {

		System.out.println(">>>>>>>>>>>");

		ModelAndView mav = new ModelAndView(ModuleDefs.INDEX);
		mav.addObject("pageindex", 0);
		return mav;
	}

	@RequestMapping(value = "/{pageindex}", method = RequestMethod.GET)
	public ModelAndView gotoOtherPage(@PathVariable int pageindex, HttpSession httpSession) {
		logger.info("#######################");

		String displayname = "";
		String rolename = "";
		ModelAndView mav;
		boolean loginflag=false;

		
		/*
		Object user_session = httpSession.getAttribute(ServerDefs.SESSION_USER_LABEL);
		if (user_session != null) {
			loginflag=true;
			User user = (User) user_session;
			displayname = user.getFirstname() + " " + user.getLastname();
			rolename = user.getRolename();
			
		} else {
			System.out.println("kindly login");
			logger.info("KINDLY LOGIN TO PROCEEED");
			pageindex = 1;
		}
		*/
		
		
		Object user_session = LoginInfo.user;
		if (user_session != null) {
			loginflag=true;
			User user = (User) user_session;
			displayname = user.getFirstname() + " " + user.getLastname();
			rolename = user.getRolename();
			
		} else {
			System.out.println("kindly login");
			logger.info("KINDLY LOGIN TO PROCEEED");
			pageindex = 1;
		}
		
		
		
		logger.info("USER OPENING THE PAGE: " + pageindex);

		switch (pageindex) {
		// 0
		case 0:
			mav = new ModelAndView(ModuleDefs.INDEX);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",0);
			break;

		// 1
		case 1:
			mav = new ModelAndView(ModuleDefs.LOGIN);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",pageindex);
			break;

		// 1000
		case 1000:
			mav = new ModelAndView(ModuleDefs.HOME);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",pageindex);
			break;

			
			
		// 2000
		case 2100:
			mav = new ModelAndView(ModuleDefs.CANDIDATE_REGISTER);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",2000);
			break;
		case 2200:
			mav = new ModelAndView(ModuleDefs.CANDIDATE_PROCESS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",2000);
			break;
		case 2300:
			mav = new ModelAndView(ModuleDefs.CANDIDATE_REMOVE);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",2000);
			break;

			
		// 3000
		case 3100:
			mav = new ModelAndView(ModuleDefs.CLIENTS_REGISTER);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",3000);
			break;
		case 3200:
			mav = new ModelAndView(ModuleDefs.CLIENTS_PROCESS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",3000);
			break;
		case 3300:
			mav = new ModelAndView(ModuleDefs.CLIENTS_REMOVE);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",3000);
			break;

			
		// 4000
		case 4100:
			mav = new ModelAndView(ModuleDefs.JOBS_REGISTER);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",4000);
			break;
		case 4200:
			mav = new ModelAndView(ModuleDefs.JOBS_PROCESS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",4000);
			break;
		case 4300:
			mav = new ModelAndView(ModuleDefs.JOBS_REMOVE);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",4000);
			break;

			
			
		// 5000
		case 5100:
			mav = new ModelAndView(ModuleDefs.DASHBOARD_APPLY);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",5000);
			break;
		case 5200:
			mav = new ModelAndView(ModuleDefs.DASHBOARD_PROCESS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",5000);
			break;
		case 5300:
			mav = new ModelAndView(ModuleDefs.DASHBOARD_REMOVE);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",5000);
			break;

			
		// 6000
		case 6100:
			mav = new ModelAndView(ModuleDefs.REPORTS_CANDIDATE);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",6000);
			break;
		case 6200:
			mav = new ModelAndView(ModuleDefs.REPORTS_CLIENTS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",6000);
			break;
		case 6300:
			mav = new ModelAndView(ModuleDefs.REPORTS_JOBS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",6000);
			break;
		case 6400:
			mav = new ModelAndView(ModuleDefs.REPORTS_WORKORDER);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",6000);
			break;
			

			
		// 7000
		case 7000:
			mav = new ModelAndView(ModuleDefs.SETTINGS);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",7000);
			break;
		case 7100:
			mav = new ModelAndView(ModuleDefs.SETTINGS_USER);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",7000);
			break;
		case 7200:
			mav = new ModelAndView(ModuleDefs.SETTINGS_ROLE);
			mav.addObject("pageindex", pageindex);
			mav.addObject("moduleindex",7000);
			break;

			
		default:
			mav = new ModelAndView(ModuleDefs.DEFAULT);
			mav.addObject("pageindex", pageindex);
			break;

		}
		
		
		if(loginflag) {
		mav.addObject("displayname", displayname);
		mav.addObject("rolename", "(" + rolename + ")");
		mav.addObject("version", ServerDefs.VERSION);
		mav.addObject("client", ServerDefs.CLIENT);
		}

		return mav;
	}
	
	
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> loadMenu(HttpSession httpSession) {
		List<Menu> menulist=null;
		int roleid=0;
		
		/*
		Object user_session = httpSession.getAttribute(ServerDefs.SESSION_USER_LABEL);
		if (user_session != null) {
			User user = (User) user_session;
			roleid=user.getRoleid();
		}
		*/
		
		Object user_session = LoginInfo.user;
		if (user_session != null) {
			User user = (User) user_session;
			roleid=user.getRoleid();
		}
		
		menulist=menubarService.loadMenuByrole(roleid);
		return menulist;
	}
	
}
