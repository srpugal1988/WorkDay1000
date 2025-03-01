package com.niralcenter.jcmswebface.auth;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.niralcenter.jcmswebface.common.LoginInfo;
import com.niralcenter.jcmswebface.common.ServerDefs;
import com.niralcenter.jcmswebface.common.Webfaceresponse;
import com.niralcenter.jcmswebface.menu.MenubarService;
import com.niralcenter.jcmswebface.model.User;





@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {

	private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

	@Autowired
	AuthenticationService loginservice;

	@Autowired
	MenubarService menubarService;

	@Autowired
	Webfaceresponse webfaceresponse;
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	//public ModelAndView proceedLoginCheck(HttpSession httpSession,@RequestParam(name="username") String username,@RequestParam(name="password") String password ) {
	public Webfaceresponse proceedLoginCheck(HttpSession httpSession,@RequestBody User user1) {
		//ModelAndView mav;

		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//String currentPrincipalName = authentication.getName();
		String username=user1.getUsername();
		String password=user1.getPassword();
		
		
		User user=loginservice.loginCheck(username,password);
		
		
		//User user = loginservice.FetchUserInformation(usercredin.getUsername());
		
		
		if (user.getOnline()==1) {
			logger.info(
					">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			logger.info("USER:(" + user.getUsername() + ") LOGGED IN SUCCESFULLY");
			logger.info("SESSION:(" + httpSession.getId() + ") CREATED.");
			logger.info(
					">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			loginservice.UserLogForLogin(user);

			LoginInfo.USERNAME=user.getUsername();
			LoginInfo.ROLENAME=user.getRolename();
			LoginInfo.user=user;
			
			httpSession.setAttribute(ServerDefs.SESSION_USER_LABEL, user);
			//mav = new ModelAndView("redirect:" + ServerDefs.SERVER_HOMEPAGE_URL);
			webfaceresponse.setCode("100");
			webfaceresponse.setMessage("LoggedIn Successfully");
			webfaceresponse.setPocket(user);

		} else {
			LoginInfo.USERNAME="";
			LoginInfo.ROLENAME="";
			LoginInfo.user=null;
			logger.info("USER:(" + user.getUsername() + ") LOGGED IN FAILURE");
			//mav = new ModelAndView("redirect:" + ServerDefs.SERVER_LOGINPAGE_URL);
			webfaceresponse.setCode("99");
			webfaceresponse.setMessage("Invalid Username or password");
			webfaceresponse.setPocket(user);
		}

		return webfaceresponse;
	}

	
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView proceedLogout(HttpSession httpSession) {
		// LOGGED-OUT

		Object user_session = httpSession.getAttribute(ServerDefs.SESSION_USER_LABEL);
		if (user_session != null) {
			User user = (User) user_session;
			logger.info(
					"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			logger.info("USER:(" + user.getUsername() + ") LOGGED-OUT SUCCESSFULLY");
			logger.info("SESSION:(" + httpSession.getId() + ") REMOVED.");
			logger.info(
					"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

			httpSession.invalidate();
			
			loginservice.UserLogForLogout(user);
			
			LoginInfo.USERNAME="";
			LoginInfo.ROLENAME="";
			LoginInfo.user=null;

		} else {
			logger.info("USER:() NOT LOGGED IN YET");

		}

		ModelAndView mav = new ModelAndView("redirect:" + ServerDefs.SERVER_URL);
		return mav;
	}
	
	

}
