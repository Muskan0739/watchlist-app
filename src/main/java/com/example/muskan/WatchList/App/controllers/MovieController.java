package com.example.muskan.WatchList.App.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Helper method to get current logged in user's email
    private String getLoggedInEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }

    @GetMapping("/submitmovie")
    public ModelAndView showsubmitMovieForm(@RequestParam(required=false) Integer id) {
        String viewName = "submitMovieForm";
        Map<String, Object> model = new HashMap<>();

        if (id == null) {
            model.put("watchlistItem", new Movie());
        } else {
            model.put("watchlistItem", dbservice.getMoviebyId(id));
        }

        return new ModelAndView(viewName, model);
    }

    @PostMapping("/submitmovie")
    public ModelAndView submitSubmitMovieForm(@Valid @ModelAttribute("watchlistItem") Movie movie,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("submitMovieForm");
        }

        Integer id = movie.getId();

        if (id == null) {
            dbservice.createMovie(movie, getLoggedInEmail());  // pass email
        } else {
            dbservice.update(movie, id);
        }

        RedirectView rd = new RedirectView();
        rd.setUrl("/watchlist");
        return new ModelAndView(rd);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<>();

        // Only fetch movies for the logged in user
        List<Movie> movieList = dbservice.getAllMovies(getLoggedInEmail());
        model.put("watchlistRows", movieList);
        model.put("numofmovies", movieList.size());

        return new ModelAndView(viewName, model);
    }
}