package com.example.muskan.WatchList.App.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.muskan.WatchList.App.entity.UserInfo;
import com.example.muskan.WatchList.App.repository.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void createUser(UserInfo userinfo) {
	    userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
	    userRepo.save(userinfo);
	}


	@Override
	public UserDetails loadUserByUsername(String email){
	
		UserInfo user= userRepo.findByEmail(email);
		
		if (user==null) {
			System.out.println("User 404");
			throw new UsernameNotFoundException("User 404");
		}
		return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())   
                .password(user.getPassword())    
                .authorities("USER")
                .build();
		}

	public boolean emailExists(String email) {
	    return userRepo.findByEmail(email) != null;
	}
	
	}
	
