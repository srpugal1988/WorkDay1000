package com.niralcenter.jcmswebface.candidate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niralcenter.jcmswebface.common.Webfaceresponse;

@Controller
@RequestMapping("/candidatectrl")
public class CandidateController {
	
	@Autowired
	Webfaceresponse webfaceresponse;
	
	@Autowired
	CandidateRegisterService candiateRegisterService;
	
	@Autowired
	CandidateProcessService candidateProcessService;
	
	@Autowired
	CandidateRemoveService candidateRemoveService;
	
	
	
	@RequestMapping(value="/register/store",method=RequestMethod.POST)
	@ResponseBody
	public Webfaceresponse storeCandidateInformation() {
		
		//candiateRegisterService.
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Candidate register successfully");
		webfaceresponse.setPocket("Sample response");
		return webfaceresponse;
	}
	

}
