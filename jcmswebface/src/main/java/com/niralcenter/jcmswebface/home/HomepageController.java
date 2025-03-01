package com.niralcenter.jcmswebface.home;

import java.util.List;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niralcenter.jcmswebface.common.Webfaceresponse;
import com.niralcenter.jcmswebface.model.CandidatePersonalData;
import com.niralcenter.jcmswebface.model.User;


@Controller
@RequestMapping("/homectrl")
public class HomepageController {

	
	@Autowired
	HomepageService homepageservice;
	
	
	@Autowired
	Webfaceresponse webfaceresponse;
	
	
	
	@RequestMapping(value="/fetchallusers",method=RequestMethod.GET)
	@ResponseBody
	public Webfaceresponse fetchAllUserInformation() {
		
		List<User> userlist=homepageservice.fetchAllUserInformation();
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Data retrived successfully");
		webfaceresponse.setPocket(userlist);
		return webfaceresponse;
	}
	
	
	
	@RequestMapping(value="/registercandidateinfo", method = RequestMethod.POST)
	@ResponseBody 
	public Webfaceresponse registerCandidateInformations(@RequestBody CandidatePersonalData cpdata){
		System.out.println("Register Users----------------------------..>");
		System.out.println("Userdata--------------------------->"+cpdata.getFirstname());
		System.out.println("Userdata--------------------------->"+cpdata.getCountry());
		
		
		
		//dblogic
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Data register successfully");
		webfaceresponse.setPocket(cpdata);
		return webfaceresponse;
	}
	
	
}
