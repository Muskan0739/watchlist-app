package com.example.muskan.WatchList.App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	 @GetMapping("/")
	    public String showHomePage() {
	        return "index"; // This will load index.html from templates
	    }
}
