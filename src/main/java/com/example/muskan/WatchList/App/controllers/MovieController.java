package com.example.muskan.WatchList.App.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.muskan.WatchList.App.entity.Movie;
import com.example.muskan.WatchList.App.services.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {
	
	@Autowired
	DatabaseService dbservice;

	@GetMapping("/submitmovie")
	public ModelAndView showsubmitMovieForm(@RequestParam(required=false) Integer id) {
		System.out.println(id);
		String viewName= "submitMovieForm";
		
		Map<String, Object> model=new HashMap<>();//mapping the form view and movie object
		
		if(id == null) {
			model.put("watchlistItem", new Movie());
		}
		else {
			model.put("watchlistItem", dbservice.getMoviebyId(id));
		}
		
		return new ModelAndView(viewName,model);
	}
	
	@PostMapping("/submitmovie")//writing the same path name because we will see the form and submit the movie from the same page then it will direct to another page
	public ModelAndView submitSubmitMovieForm(@Valid  @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			//if errors are there, redisplay the from and let the user enter
			return new ModelAndView("submitMovieForm");
		}
		/*
		 * if(id=null){create new movie}
		 * else{update the movie}
		 */
		
		Integer id=movie.getId();
		
		if(id== null) {
			dbservice.createMovie(movie);//This is our data/model
		}
		else {
			dbservice.update(movie, id);
		}

		
		
		RedirectView rd=new RedirectView();//this is view- we are using RedirectView class because we want to redirect user to the watchlist page and here we will provide the link of that page
		rd.setUrl("/watchlist");
		
		return new ModelAndView(rd);
		
		}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		String viewName= "watchlist";
		
		Map<String, Object> model=new HashMap<>();
		List<Movie> movieList= dbservice.getAllMoview();
		model.put("watchlistRows", movieList);
		//to get number of movies
		model.put("numofmovies", movieList.size());
		
		return new ModelAndView(viewName, model);
		
	}
	
	
	
}

