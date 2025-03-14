package com.example.muskan.WatchList.App.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.muskan.WatchList.App.entity.UserInfo;
import com.example.muskan.WatchList.App.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public void createUser(UserInfo userinfo) {
		// TODO Auto-generated method stub
		userRepo.save(userinfo);
	}

	
}
