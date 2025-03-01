package com.niralcenter.jcmswebface.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niralcenter.jcmswebface.model.User;

@Service
public class HomepageService {

	
	@Autowired
	HomepageRepository homepageRepository;
	
	public List<User> fetchAllUserInformation() {
		List<User> users=new ArrayList<User>();
		users=homepageRepository.fetchAllUserInformationFromDb();
		return users;
	}
}
