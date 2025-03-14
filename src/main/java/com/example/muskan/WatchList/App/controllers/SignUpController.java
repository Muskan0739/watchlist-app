package com.example.muskan.WatchList.App.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.muskan.WatchList.App.entity.UserInfo;
import com.example.muskan.WatchList.App.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignUpController {

	@Autowired
	UserService userService;
	
	@GetMapping("/signup")
	public ModelAndView showSignUpPage() {
		String viewName= "signupForm";
		
		Map<String, Object> model= new HashMap<>(); //mapping the sign up form and user object
		
		model.put("signUp", new UserInfo());
		
		return new ModelAndView(viewName, model);
		
		}
	
	@PostMapping("/signup")
	public ModelAndView submitSignupForm(@ModelAttribute("signUp") UserInfo userinfo, BindingResult result, HttpSession session) {
		
		 if (result.hasErrors()) {
		        return new ModelAndView("/signup"); // Stay on the signup page if there are validation errors
		    }
		 
		 
		userService.createUser(userinfo);
		
		session.setAttribute("loggedInUser", userinfo); // Store user in session
		
		RedirectView rd=new RedirectView();
		rd.setUrl("/");
		
		return new ModelAndView(rd);
		
		
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
	    session.invalidate(); // Clear session
	    // Redirect to home page
	    
	    RedirectView rd=new RedirectView();
		rd.setUrl("/");
		
		return new ModelAndView(rd);
	}

}
