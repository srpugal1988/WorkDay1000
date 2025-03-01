package com.niralcenter.jcmswebface.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.niralcenter.jcmswebface.common.Webfaceresponse;
import com.niralcenter.jcmswebface.model.Role;
import com.niralcenter.jcmswebface.model.RoleRightsDetails;
import com.niralcenter.jcmswebface.model.User;
import com.niralcenter.jcmswebface.validatons.UserValidator;


@Controller
@RequestMapping("/settingsctrl")
public class SettingsController {

	
	@Autowired
    Webfaceresponse webfaceresponse;
	
	
	@Autowired
	UserService userservice;
	
	
	@Autowired
	RolesService roleservice;
	
	
	@Autowired
	UserValidator uservalidator;
	
	
	@RequestMapping(value="/user/store",method=RequestMethod.POST)
	@ResponseBody
	public Webfaceresponse storeUserInformation(@RequestBody User User) {
		System.out.println("user.getUsername"+User.getUsername());
		Map<String,Object> retMap=new HashMap<String,Object>();
		
		
		retMap=userservice.StoreUserInformation(User);
		
		
		if(retMap.get("validationstatus").equals("success")) {
			webfaceresponse.setCode("100");
			webfaceresponse.setMessage("User register successfully!");
			webfaceresponse.setPocket(User);
		}else {
			webfaceresponse.setCode("99");
			webfaceresponse.setMessage("User register failed");
			webfaceresponse.setPocket(retMap);
		}
		
		
		return webfaceresponse;
	}
	
	
	@RequestMapping(value="/user/fetchall",method=RequestMethod.GET)
	@ResponseBody
	public Webfaceresponse fetchAllUserInformation() {
		
		List<User> userlist=userservice.fetchAllUserInformation();
		
		Map<String,Object> response=new HashMap<String,Object>();
		response.put("userlist",userlist);
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Data retrived successfully");
		webfaceresponse.setPocket(response);
		return webfaceresponse;
	}
	

	
	@RequestMapping(value="/user/getnextuserreferencenumber",method=RequestMethod.GET)
	@ResponseBody
	public Webfaceresponse LoadNextUserReference() {
		
		String userrefnumber=userservice.LoadNextUserReference();
		
		
		Map<String,Object> response=new HashMap<String,Object>();
		response.put("userreferencenumber",userrefnumber);
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("User register successfully!");
		webfaceresponse.setPocket(response);
		return webfaceresponse;
	}
	
	
	@RequestMapping(value="/role/fetchall",method=RequestMethod.GET)
	@ResponseBody
	public Webfaceresponse fetchAllRolesInformation() {
		List<Role> roleslist=roleservice.fetchRolesInformation();
		
		Map<String,Object> response=new HashMap<String,Object>();
		response.put("rolesinformationlist",roleslist);
		
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Data retrived successfully");
		webfaceresponse.setPocket(response);
		return webfaceresponse;
	}
	
	
	
	@RequestMapping(value="/role/rights",method=RequestMethod.GET)
	@ResponseBody
	public Webfaceresponse fetchRolesRightsInformation(@RequestParam(name = "roleid") int roleid) {
		System.out.println("reached roles controller-------------------------------->");
		
		List<RoleRightsDetails> rolerightslist=new ArrayList<RoleRightsDetails>();
		
		
		rolerightslist=roleservice.fetchRolesAndRightsInformation(roleid);
		
		
		Map<String,Object> mapinfo=new HashMap<String,Object>();
		mapinfo.put("rolesrightlist",rolerightslist);

		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Data retrived successfully");
		webfaceresponse.setPocket(mapinfo);
		return webfaceresponse;
	}
	
	
	
	@RequestMapping(value="/role/rightschange",method=RequestMethod.POST)
	@ResponseBody
	public Webfaceresponse ChangeAccessForModule(@RequestBody RoleRightsDetails rolerights) {
		int updatecount=roleservice.ChangeAccessForModule(rolerights);

		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Data retrived successfully");
		webfaceresponse.setPocket(rolerights);
		return webfaceresponse;
	}
	
	
	
	@RequestMapping(value="/role/store",method=RequestMethod.POST)
	@ResponseBody
	public Webfaceresponse StoreRolesInformation(@RequestBody Role role) {
		System.out.println("Reached StoreRolesInformation------------------------->");
		
		
		int count=roleservice.StoreRolesInformation(role);
		
		
		webfaceresponse.setCode("100");
		webfaceresponse.setMessage("Role added successfully!");
		webfaceresponse.setPocket(role);
		
		return webfaceresponse;
	}
	
	
}
